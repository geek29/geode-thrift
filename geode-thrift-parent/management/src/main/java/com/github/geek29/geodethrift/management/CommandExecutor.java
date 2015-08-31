package com.github.geek29.geodethrift.management;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;

import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.cli.CommandService;
import com.gemstone.gemfire.management.cli.Result;
import com.gemstone.gemfire.management.internal.cli.result.CommandResult;

/**
 * TODO : Cache all field introspection or do it at start-up
 * TODO : Declare CommandException
 * TODO : Add default values for fields in structs
 * TODO : Support binary data types used in Deploy and other commands
 * TODO : Add CommandStatus constants in Thrift
 * @author tushark
 *
 */
public class CommandExecutor {
	
	private GemFireCacheImpl cache;
	
	public CommandExecutor(){
		cache = GemFireCacheImpl.getExisting();
	}
	/**
	 * 
	 * @param command
	 * @param arguments
	 * @return
	 * @throws JSONException
	 */
	public com.github.geek29.geodethrift.management.commandResult.CommandResult execute(String command, Object arguments) 
			throws JSONException {		
		return ResultBuilder.from(executeCommandString(commandString(command,introspect(arguments))));
	}

	public CommandResult executeCommandString(String cmdString) {
		CommandService cmdservice = CommandService.getUsableLocalCommandService();
		Result result = cmdservice.processCommand(cmdString);
		return (CommandResult)result;
	}

	public String commandString(String command, Map<String, String> args) {
		if(args==null)
			return command;
		StringBuilder sb = new StringBuilder();
		sb.append(command);
		for(Entry<String,String> e : args.entrySet()) {
			sb.append(" --").append(hyphennize(e.getKey())).append("=").append(e.getValue());
		}
		return sb.toString();
	}

	private String hyphennize(String key) {
		List<String> strList = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<key.length();i++) {
			char c = key.charAt(i);
			if(Character.isUpperCase(c)){
				strList.add(sb.toString());
				sb = new StringBuilder();
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		if(sb.length()>0)
			strList.add(sb.toString());
		sb = new StringBuilder();
		for(int i=0;i<strList.size();i++) {
			sb.append(strList.get(i));
			if(i<strList.size()-1){
				sb.append("-");
			}
		}
		return sb.toString();
	}
	public Map<String, String> introspect(Object arguments) {
		if(arguments==null)
			return null;
		Map<String, String> map = new HashMap<String, String>();
		Class klass = arguments.getClass();
		for (Field field : klass.getFields()) {
			if (!field.getName().equals("argsId") && !field.getName().equals("metaDataMap")) {
				Object fieldValue;
				try {
					fieldValue = field.get(arguments);
					String value = null;
					if (fieldValue != null) {
						value = fieldValue.toString();
						map.put(field.getName(), value);
					}					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException("Error during field introspection "+ field.getName() + " of " + klass);
				}
			}
		}
		return map;
	}

}
