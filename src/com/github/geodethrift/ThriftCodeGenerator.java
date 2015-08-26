package com.github.geodethrift;

import java.util.Map;
import java.util.Properties;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.distributed.DistributedSystem;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.ManagementService;
import com.gemstone.gemfire.management.internal.cli.CommandManager;
import com.gemstone.gemfire.management.internal.cli.parser.Argument;
import com.gemstone.gemfire.management.internal.cli.parser.CommandTarget;
import com.gemstone.gemfire.management.internal.cli.parser.Option;

public class ThriftCodeGenerator {
	
	public static void main(String[] args) {
		CommandManager mgr = startGemFire();
		iterateCommands(mgr);
		stopGemfire();
		/*System.out.println("Method Name : start gateway-sender : " + getProperName("start gateway-sender", true) );
		System.out.println("Struct Name : start gateway-sender : " + getProperName("start gateway-sender", false) );*/
		
		/*
		 https://github.com/gemfire/node-gemfire/blob/master/doc/api.md
		 CacheService
		 	getVersion
		 	createRegion(name,options)
		 	executeFunction(name,options)
		 	executeQuery
		 	getRegionAttributes
		 	get/put/remove/query
		  
		  
		 */
		
	}
	
	
	private static void stopGemfire() {
		GemFireCacheImpl.getExisting().close();
		
	}


	private static void iterateCommands(CommandManager mgr) {
		Map<String,CommandTarget> commandMap = mgr.getCommands();
		StringBuilder sb = new StringBuilder("service GeodeCommandService {");
		for(String command : commandMap.keySet()) {
			CommandTarget tgr = commandMap.get(command);
			processCommand(tgr);
			sb.append("\n\tCommandResult ").append(getProperName(tgr.getCommandName(), true));
			sb.append("(").append("1:").append(getProperName(tgr.getCommandName(), false));
			sb.append(" args) throws (1:ComandExeception)");
		}
		sb.append("\n}");
		System.out.println("\n Service\n"+ sb.toString());
	}


	private static void processCommand(CommandTarget commandTarget) {
		String command = commandTarget.getCommandName();
		System.out.println("\n struct " + getProperName(command, false) + " {\n");
		//Arguments
		for (Argument argument : commandTarget.getOptionParser().getArguments()) {
			System.out.println("\tArgument : " + argument.getArgumentName() +"\n");
		}
		
		for(Option option  : commandTarget.getOptionParser().getOptions()){
			System.out.println("\tOption :" + option.getLongOption() 
					+ " optional : " + ( option.isRequired() ? "NO" : "YES")
					+ " type :"+ option.getDataType().getSimpleName()+"\n");
		}
		System.out.println("}");
	}


	private static String getProperName(String command, boolean isMethodName) {
		String[] array = command.split(" ");
		StringBuilder sb = new StringBuilder();
		int start = 1;
		//generate methodname
		if(isMethodName) {
			sb.append(array[0]);
			start = 1;
		} else {
		//generate struct name
			start = 0;
		}
		for(int i=start;i<array.length;i++) {
			sb.append(Character.toUpperCase(array[i].charAt(0)));
			String s = array[i].substring(1);			
			String[] array2 = s.split("-");
			for(int j=0;j<array2.length;j++) {
				if(j!=0)
					sb.append(Character.toUpperCase(array2[j].charAt(0)));
				else 
					sb.append(array2[j].charAt(0));
				sb.append(array2[j].substring(1));				
			}
		}
		if(!isMethodName)
			sb.append("Args");
		return sb.toString();
	}


	private static CommandManager startGemFire() {
		Properties pr = new Properties();
		pr.put("jmx-manager", "true");
		pr.put("jmx-manager-start", "true");
		DistributedSystem ds = DistributedSystem.connect(pr);
		Cache cache = new CacheFactory().create();
		GemFireCacheImpl impl = (GemFireCacheImpl)cache;
		ManagementService service = ManagementService.getManagementService(cache);
		CommandManager manager = CommandManager.getExisting();
		return manager;
	}

}
