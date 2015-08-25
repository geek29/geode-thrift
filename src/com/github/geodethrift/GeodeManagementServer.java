package com.github.geodethrift;

import org.apache.thrift.TException;

import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.cli.CommandService;
import com.gemstone.gemfire.management.cli.Result;

public class GeodeManagementServer implements GemfireManagement.Iface {
	
	private GemFireCacheImpl impl;
	private CommandService service;
	
	public GeodeManagementServer(){
		impl = GemFireCacheImpl.getExisting();
		service = CommandService.getUsableLocalCommandService();
	}

	@Override
	public String execute(Command cmd) throws CommandException, TException {
		try{
		Result result = service.processCommand(cmd.command);
		StringBuilder sb = new StringBuilder();
		while(result.hasNextLine()) {
			sb.append(result.nextLine());
		}
		return sb.toString();
		}catch(Exception e){
			throw new CommandException(cmd.command, e.getMessage());
		}
	}
	
	
}
