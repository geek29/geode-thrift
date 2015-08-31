package com.github.geek29.geodethrift.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gemstone.gemfire.management.internal.cli.result.CommandResult;
import com.gemstone.gemfire.management.internal.cli.result.ResultData;
import com.github.geek29.geodethrift.management.commandResult.CompositeResult;
import com.github.geek29.geodethrift.management.commandResult.ErrorResult;
import com.github.geek29.geodethrift.management.commandResult.InfoResult;
import com.github.geek29.geodethrift.management.commandResult.ResultContent;
import com.github.geek29.geodethrift.management.commandResult.Section;
import com.github.geek29.geodethrift.management.commandResult.TableResult;
import com.github.geek29.geodethrift.management.commandResult.commandResultConstants;

/**
 * ResultBuilder API : Converts Gfsh CommandResult Object to Thrift CommandResult
 * 
 * TODO :
 * 	1. Handle files
 *  2. Handle binary data sent by Netstat/Deploy and other commands
 * @author tushark
 *
 */
public class ResultBuilder {
	
	public static String pattern = "__sections__";
	public static String seppattern = "__separator__";
	public static String tabpattern = "__tables__";
	
	
	//TODO : Add other fields esp : debug info
	public static com.github.geek29.geodethrift.management.commandResult.CommandResult from(
			CommandResult result) throws JSONException {		
		com.github.geek29.geodethrift.management.commandResult.CommandResult thriftResult = new 
					com.github.geek29.geodethrift.management.commandResult.CommandResult();
		thriftResult.setContentType(result.getType());
		thriftResult.setStatus((short)result.getStatus().ordinal());		
		thriftResult.content = buildContent(result.getResultData(),result.getType());		
		return thriftResult;
	}

	private static ResultContent buildContent(ResultData data, String type) throws JSONException {
		com.github.geek29.geodethrift.management.commandResult.ResultContent content = new 
				com.github.geek29.geodethrift.management.commandResult.ResultContent();
		content.header = data.getHeader();
		content.footer = data.getFooter();		
		JSONObject josncontent = data.getGfJsonObject().getInternalJsonObject().getJSONObject("content");
		System.out.println("Outer content " + josncontent.toString(2));		
		if (commandResultConstants.INFO_RESULT.equals(type))
	      content.infoResult = buildInfoContent(josncontent);
	    else if (commandResultConstants.TABULAR_RESULT.equals(type))
	      content.tableResult = buildTableContent(josncontent);
	    else if (commandResultConstants.COMPOSITE_RESULT.equals(type))
	      content.compositeResult = buildCompositeContent(josncontent);
	    else if (commandResultConstants.ERROR_RESULT.equals(type))
	      content.errorResult = buildErrorContent(josncontent);			    
		return content;
	}

	private static ErrorResult buildErrorContent(JSONObject josncontent) throws JSONException {
		com.github.geek29.geodethrift.management.commandResult.ErrorResult result = new 
				com.github.geek29.geodethrift.management.commandResult.ErrorResult();		
		JSONArray infoMessageArray = josncontent.getJSONArray("message");
		result.messages = new ArrayList<String>();
	    for (int i = 0; i < infoMessageArray.length(); i++)
	      result.messages.add((String) infoMessageArray.get(i));
		result.errorCode = (short) josncontent.getInt("errorCode");
		return result;
	}

	private static CompositeResult buildCompositeContent(JSONObject josncontent) throws JSONException {
		com.github.geek29.geodethrift.management.commandResult.CompositeResult result = new 
				com.github.geek29.geodethrift.management.commandResult.CompositeResult();	
		result.sectionNames = new ArrayList<String>();
		result.sections = new ArrayList<Section>();
		for (Iterator localIterator = josncontent.keys(); localIterator.hasNext();) {
			Object k = localIterator.next();
			if (k.toString().contains(pattern)) {
				result.sectionNames.add(k.toString());
				result.sections.add(createSection(josncontent, k.toString()));
			} else if (k.toString().equals("header")) {
				result.header = josncontent.getString("header");
			}
		}
		return result;
	}

	private static Section createSection(JSONObject josncontent, String sectName) throws JSONException {
		JSONObject section = josncontent.getJSONObject(sectName);
		Section thriftSection = new Section();
		thriftSection.sectionMap = new HashMap<String,String>();
		thriftSection.tables = new ArrayList<TableResult>();
		//thriftSection.sections We dont support cycles yet so skip this
	    Iterator iterator = section.keys();
	    while (iterator.hasNext()) {
	      String key = (String)iterator.next();
	      if (key.contains(pattern)) {
	    	  //thriftSection.sections.add(GfshResult.createSection(this.jsonObject, key));
	    	  //thriftSection.sections We dont support cycles yet so skip this
	    	  System.out.println("Skipping sections-within-sections.");
	      } else if (key.contains(tabpattern)) {
	        JSONObject object = section.getJSONObject(key);
	        JSONObject content = object.getJSONObject("content");	        
	        thriftSection.tables.add(buildTableContent(content));
	      } else if (key.equals("header")) {
	    	  thriftSection.header = section.getString("header");
	      } else if (!key.contains(seppattern)) {
	        String value = getString(section, key);
	        System.out.println("adding key " + key);
	        thriftSection.sectionMap.put(key, value);
	      }
	    }	  
		return thriftSection;		
	}

	private static TableResult buildTableContent(JSONObject josncontent) throws JSONException {
		com.github.geek29.geodethrift.management.commandResult.TableResult result = new 
				com.github.geek29.geodethrift.management.commandResult.TableResult();
		result.colNames = new ArrayList<String>();
		result.data = new ArrayList<List<String>>();
		int maxRows = 0;
		JSONArray array;
		for (Iterator localIterator = josncontent.keys(); localIterator.hasNext();) {
			Object k = localIterator.next();
			result.colNames.add(k.toString());
			array = josncontent.getJSONArray(k.toString());
			if (maxRows < array.length())
				maxRows = array.length();
		}
	    int col = 0;	    
	    for (String column : result.colNames) {
	      JSONArray array1 = josncontent.getJSONArray(column);
	      List<String> rowData = new ArrayList<String>();
	      for (int row = 0; row < array1.length(); row++) {
	        Object object = array1.get(row);
	        rowData.add(stringify(object));
	      }
	      result.data.add(rowData);
	      col++;
	    }
	    result.cols = (short)col;
		result.rows = (short) maxRows;
		return result;
	}

	private static InfoResult buildInfoContent(JSONObject josncontent) throws JSONException {
		com.github.geek29.geodethrift.management.commandResult.InfoResult result = new 
				com.github.geek29.geodethrift.management.commandResult.InfoResult();		
		JSONArray infoMessageArray = josncontent.getJSONArray("message");
		result.messages = new ArrayList<String>();
	    for (int i = 0; i < infoMessageArray.length(); i++)
	      result.messages.add((String) infoMessageArray.get(i));
	    return result;
	}
	
	 public static String getString(JSONObject josncontent, String key) throws JSONException {
		    Object object = josncontent.get(key);
		    return stringify(object);
		  }

	public static String stringify(Object object) {
		if (object == null)
			return null;
		if (object.equals(JSONObject.NULL)) {
			return null;
		}
		if ((object instanceof String)) {
			return (String) object;
		}

		return object.toString();
	}

}
