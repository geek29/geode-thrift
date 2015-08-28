package com.github.geek29.geodethrift.management;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;

import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.cli.CommandService;
import com.gemstone.gemfire.management.cli.Result;
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
	
	private GemFireCacheImpl cache;
	
	private CommandExecutor(){
		cache = GemFireCacheImpl.getExisting();
	}
	/**
	 * Here Objext -> JSON -> GfshResult -> Thrift 
	 * so three time serialize-deserialize is happending avoid it
	 * 
	 * @param command
	 * @param arguments
	 * @return
	 * @throws JSONException
	 */
	public GfshResult execute(String command, Object arguments) throws JSONException {
		Map<String,String> args = introspect(arguments);
		String cmdString = commandString(command,args);
		CommandResult gfresult = executeCommandString(cmdString);
		return new GfshResult(toJson(gfresult));
	}

	public String toJson(CommandResult gfresult) {
		StringBuilder sb = new StringBuilder();
		while(gfresult.hasNextLine()) {
			sb.append(gfresult.nextLine());
		}
		return sb.toString();
	}

	public CommandResult executeCommandString(String cmdString) {
		CommandService cmdservice = CommandService.getUsableLocalCommandService();
		Result result = cmdservice.processCommand(cmdString);
		return (CommandResult)result;
	}

	public String commandString(String command, Map<String, String> args) {
		StringBuilder sb = new StringBuilder();
		sb.append(command);
		for(Entry<String,String> e : args.entrySet()) {
			sb.append("--").append(e.getKey()).append("=").append(e.getValue());
		}
		return sb.toString();
	}

	public Map<String, String> introspect(Object arguments) {
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
