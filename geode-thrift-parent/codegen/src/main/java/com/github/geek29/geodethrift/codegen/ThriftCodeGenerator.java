package com.github.geek29.geodethrift.codegen;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.distributed.DistributedSystem;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.ManagementService;
import com.gemstone.gemfire.management.internal.cli.CommandManager;
import com.gemstone.gemfire.management.internal.cli.parser.Argument;
import com.gemstone.gemfire.management.internal.cli.parser.CommandTarget;
import com.gemstone.gemfire.management.internal.cli.parser.Option;

/*
 * TODO: Replace manual string build by some sort of templating lang - velocity
 * 
 */
public class ThriftCodeGenerator {
	
	private static final String MGMT_SERVICE_PACKAGE = "com.github.geek29.geodethrift.management.service";
	private static final String MGMT_STRUCT_PACKAGE = "com.github.geek29.geodethrift.management.structs";
	private static final String MGMT_CMDRESULT_PACKAGE = "com.github.geek29.geodethrift.management.commandResult";
	
	private static final String MGMT_SERVICE_THRIFT_FILE = "service.thrift";
	private static final String MGMT_STRUCT_THRIFT_FILE = "structs.thrift";
	private static final String MGMT_CMDRESULT_THRIFT_FILE = "commandResult.thrift";

	private static Set<String> allKindsType = new HashSet<String>();
	private static AtomicInteger argsCounter = new AtomicInteger();

	public static void main(String[] args) throws IOException {
		CommandManager mgr = startGemFire();
		writeCommandServiceFiles(mgr);
		writeCacheServiceFiles();
		stopGemfire();
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
	
	
	private static void writeCacheServiceFiles() {
		
		
	}


	private static void writeCommandServiceFiles(CommandManager mgr) throws IOException {
		iterateCommands(mgr);		
	}


	private static void stopGemfire() {
		GemFireCacheImpl.getExisting().close();
		
	}


	private static void iterateCommands(CommandManager mgr) throws IOException {
		Map<String,CommandTarget> commandMap = mgr.getCommands();
		StringBuilder serviceString = new StringBuilder("include \"");
		serviceString.append(MGMT_STRUCT_THRIFT_FILE).append("\"\n");
		serviceString.append("include \"");
		serviceString.append(MGMT_CMDRESULT_THRIFT_FILE).append("\"\n");
		serviceString.append("namespace java ").append(MGMT_SERVICE_PACKAGE).append("\n");		
		serviceString.append("\nservice GeodeCommandService {\n");
		
		StringBuilder structString = new StringBuilder();
		structString.append("namespace java ").append(MGMT_STRUCT_PACKAGE).append("\n");
		
		int size = commandMap.size();
		for(String command : commandMap.keySet()) {
			CommandTarget tgr = commandMap.get(command);
			boolean argsStructRequired = argsStructRequired(tgr);
			boolean commandSupported = commandSupported(tgr);
			if(commandSupported) {
				if(argsStructRequired) {
					boolean commandProcessed = processCommand(tgr,structString);
					if(commandProcessed) {
						serviceString.append("\n\tcommandResult.CommandResult ").append(getProperName(tgr.getCommandName(), METHOD_NAME));
						serviceString.append("(").append("1:structs.").append(getProperName(tgr.getCommandName(), STRUCT_NAME));
						serviceString.append(" arguments)");
						//serviceString.append(" arguments) throws (1:ComandExeception cmdEx)");
						if(size>1)
							serviceString.append(",");
					}					
				} else {
					serviceString.append("\n\tcommandResult.CommandResult ").append(getProperName(tgr.getCommandName(), METHOD_NAME));
					//serviceString.append("() throws (1:CommandExeception cmdEx)");
					serviceString.append("()");
					if(size>1)
						serviceString.append(",");
				}				
			}
			size--;
		}
		serviceString.append("\n}");
		writeToFile(structString, MGMT_STRUCT_THRIFT_FILE);
		writeToFile(serviceString, MGMT_SERVICE_THRIFT_FILE);
	}


	/**
	 * Some commands can not be supported over wire but only
	 * work thorugh shell. Add them here.
	 * 
	 * @param tgr
	 * @return
	 */
	private static boolean commandSupported(CommandTarget tgr) {
		if (tgr.getCommandName().contains("connect") 
				|| tgr.getCommandName().startsWith("start")
				|| tgr.getCommandName().startsWith("exit")
				|| tgr.getCommandName().startsWith("disconnect")
				|| tgr.getCommandName().startsWith("debug")
				|| tgr.getCommandName().startsWith("deploy") //deploy need byte[] manual changes. will be supported later
				|| tgr.getCommandName().startsWith("describe client")
				|| tgr.getCommandName().startsWith("echo")
				|| tgr.getCommandName().startsWith("import") //import command have file structures again
				|| tgr.getCommandName().equals("sh")
				|| tgr.getCommandName().equals("sleep")
				)			
			return false;
		else
			return true;
	}


	private static boolean argsStructRequired(CommandTarget tgr) {
		if ((tgr.getOptionParser().getArguments().size() + tgr
				.getOptionParser().getOptions().size()) > 0)
			return true;
		else
			return false;
	}


	private static void writeToFile(StringBuilder serviceString, String fileName) throws IOException {
		System.out.println(serviceString);
		FileWriter writer = new FileWriter("/tmp/"+fileName);
		writer.append(serviceString.toString());
		writer.close();
		System.out.println("File " + fileName + " saved.") ;
	}


	private static boolean processCommand(CommandTarget commandTarget,
			StringBuilder structFile) {
		
		StringBuilder structString = new StringBuilder();
		try {
			int paramNumber = 1;
			String command = commandTarget.getCommandName();
			List<Argument> arguments = commandTarget.getOptionParser().getArguments();
			List<Option> options = commandTarget.getOptionParser().getOptions();


			structString.append("\nstruct " + getProperName(command, STRUCT_NAME) + " {\n");

			// This is to ensure at least one non-optional fields
			structString.append("\t").append(paramNumber++).append(":")
					.append("i16 argsId=").append(argsCounter.incrementAndGet())
					.append(",\n");

			for (int i = 0; i < arguments.size(); i++) {
				Argument argument = arguments.get(i);
				structString
						.append("\t")
						.append(paramNumber++)
						.append(":")
						.append(" string ")
						.append(getProperName(argument.getArgumentName(),
								STRUCT_FIELD_NAME));
				if (options.size() > 0)
					structString.append(",\n");
				else
					structString.append("\n");
			}

			for (int i = 0; i < options.size(); i++) {
				Option option = options.get(i);
				String thriftType = getThriftDataType(option.getDataType());
				String thriftName = getProperName(option.getLongOption(),
						STRUCT_FIELD_NAME);
				structString.append("\t").append(paramNumber++).append(":")
						.append((option.isRequired() ? "" : "optional "))
						.append(thriftType).append(" ").append(thriftName);
				if (i < options.size() - 1)
					structString.append(",\n");
				else
					structString.append("\n");

				allKindsType.add(option.getDataType().getSimpleName());
			}
			structString.append("\n}");
			structFile.append(structString.toString());
			return true;
		} catch (RuntimeException e) {
			System.out.println("Command " + commandTarget.getCommandName() + " not supported");
			return false;
		}
	}


	private static final int METHOD_NAME = 1;
	private static final int STRUCT_NAME = 2;
	private static final int STRUCT_FIELD_NAME = 3;
	
	/**
	 * If input is "show subscription-queue-size" then
	 * 	mode = STRUCT_NAME output is ShowSubscriptionQueueSizeArgs
	 *  mode = METHOD_NAME output is showSubscriptionQueueSize
	 *  mode = STRUCT_FIELD_NAME output is showSubscriptionQueueSize
	 *  
	 * @param command
	 * @param mode
	 * @return
	 */
	private static String getProperName(String command, int mode) {
		int start = 1;
		String[] array;
		StringBuilder sb = new StringBuilder();		
		
		if(mode==METHOD_NAME || mode==STRUCT_NAME) {
			array = command.split(" ");					
			//generate methodname
			if(mode==METHOD_NAME) {
				sb.append(deHyphenize(array[0]));
				start = 1;
			} else {
			//generate struct name
				start = 0;
			}
		} else {
			array = new String[1];
			array[0] = command;
			start = 0;
		}		
		
		for(int i=start;i<array.length;i++) {
			if(mode!=STRUCT_FIELD_NAME)
				sb.append(Character.toUpperCase(array[i].charAt(0)));
			else 
				sb.append(array[i].charAt(0));			
			String s = array[i].substring(1);
			sb.append(deHyphenize(s));			
		}
		
		if(mode==STRUCT_NAME)
			sb.append("Args");
		
		return deKeyWordify(sb.toString());
	}
	
	private static String[] keywords = {
			"new",
			"int",
			"integer",
			"for",
			"while",
			"class",
			"private",
			"public",			
	};
	
	private static String deKeyWordify(String string) {
		for(String s : keywords){
			if(s.equals(string))
				return "_" + string;
		}
		return string;
	}


	private static String deHyphenize(String s) {
		StringBuilder sb = new StringBuilder();
		String[] array2 = s.split("-");
		for(int j=0;j<array2.length;j++) {
			if (array2[j].length() > 0) {
				if (j != 0)
					sb.append(Character.toUpperCase(array2[j].charAt(0)));
				else
					sb.append(array2[j].charAt(0));
				sb.append(array2[j].substring(1));
			}
		}
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
	
	/**
	 * The first thing to know about are types. The available types in Thrift are:
	 *
	 *  bool        Boolean, one byte
	 *  byte        Signed byte
	 *  i16         Signed 16-bit integer
	 *  i32         Signed 32-bit integer
	 *  i64         Signed 64-bit integer
	 *  double      64-bit floating point value
	 *  string      String
	 *  binary      Blob (byte array)
	 *  map<t1,t2>  Map from one type to another
	 *  list<t1>    Ordered list of one type
	 *  set<t1>     Set of unique elements of one type
	 *
	 *  Float : double 
	 *  Long, : i64
	 *  int : i32
	 *  File, - Not supported
	 *  RegionShortcut : string
	 *  double : double 
	 *  Boolean : bool
	 *  long, : i64
	 *  float, : double
	 *  String[], : list<string>
	 *  String, : string
	 *  boolean, : bool
	 *  List, : list<string>
	 *  Integer : i32
	 */
	
	private static final String[][] fieldMapping = {
		{"Float","double"}, 
		{"Long","i64"},
		{"int","i32"},
		{"File","NA"},
		{"RegionShortcut","string"},
		{"double","double" },
		{"Boolean","bool"},
		{"long","i64"},
		{"float","double"},
		{"String[]","list<string>"},
		{"String","string"},
		{"boolean","bool"},
		{"List","NA" }, //"list<string>"
		{"Integer","i32"}
	};
	
	private static String getThriftDataType(Class klass) {
		//return "string " + klass.getSimpleName() +" ";
		for(int i=0;i<fieldMapping.length;i++){
			if(fieldMapping[i][0].equals(klass.getSimpleName())){
				String mappedValue = fieldMapping[i][1];
				if("NA".equals(mappedValue))
					throw new RuntimeException("Type " + klass + " not supported");
				return mappedValue;
			}
		}
		throw new RuntimeException("Type " + klass + " not supported");
	}

}