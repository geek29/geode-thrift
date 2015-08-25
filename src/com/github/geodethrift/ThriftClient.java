package com.github.geodethrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.github.geodethrift.GemfireManagement.Client;

public class ThriftClient {

	public static void main(String[] args) {
		try {
			TTransport transport;
			transport = new TSocket("localhost", 9090);
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			GemfireManagement.Client mgmt = new GemfireManagement.Client(protocol);
			perform(mgmt);
			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	private static void perform(Client mgmt) throws CommandException, TException {
		Command cmd = new Command();
		cmd.command = "list members";
		System.out.println("List Members " + mgmt.execute(cmd));
	}
}
