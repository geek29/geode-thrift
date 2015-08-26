package com.github.geek29.geodethrift.management;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.gemstone.gemfire.management.internal.cli.result.CommandResult;
import com.github.geek29.geodethrift.management.result.GfshResult;

/**
 * TODO : Cache all field introspection or do it at start-up
 * TODO : Declare CommandException
 * TODO : Add default values for fields in structs
 * @author tushark
 *
 */
public class CommandExecutor {
	
	public GfshResult execute(Object arguments) throws JSONException {
		Map<String,String> args = introspect(arguments);
		String cmdString = commandString(args);
		CommandResult gfresult = executeCommandString(cmdString);
		return new GfshResult(toJson(gfresult));
	}

	private String toJson(CommandResult gfresult) {
		// TODO Auto-generated method stub
		return null;
	}

	private CommandResult executeCommandString(String cmdString) {
		// TODO Auto-generated method stub
		return null;
	}

	private String commandString(Map<String, String> args) {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, String> introspect(Object arguments) {
		Map<String,String> map = new HashMap<String,String>();
		Class klass = arguments.getClass();
		for(Field field : klass.getFields()) {
			Object fieldValue;
			try {
				fieldValue = field.get(arguments);
				//TODO : check for default values
				String value=null;
				if(fieldValue!=null)
					value = fieldValue.toString();
				map.put(field.getName(),value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException("Error during field introspection " + field.getName()
						+ " of " + klass);
			}
			
		}
		return map;
	}

}
