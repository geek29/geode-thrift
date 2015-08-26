package com.github.geodethrift;

import java.util.Properties;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.distributed.DistributedSystem;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.ManagementService;

public class ThriftServer {

	public static void main(String[] args) {

		try {
			startGemFire();
			GemfireManagement.Processor processor = new GemfireManagement.Processor(new GeodeManagementServer());
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(
					new Args(serverTransport).processor(processor));
			System.out.println("Starting the simple server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void startGemFire() {
		Properties pr = new Properties();
		pr.put("jmx-manager", "true");
		pr.put("jmx-manager-start", "true");
		DistributedSystem ds = DistributedSystem.connect(pr);
		Cache cache = new CacheFactory().create();
		GemFireCacheImpl impl = (GemFireCacheImpl)cache;
		ManagementService service = ManagementService.getManagementService(cache);
		System.out.println("Manager started ??" +service.isManager());
	}
}


/** Multiplexing Thrift Services 


Sample Usage - Client
In this example, we've chosen to use TBinaryProtocol with two services: Calculator and WeatherReport.
	TSocket transport = new TSocket("localhost", 9090);
	transport.open();
	
	TBinaryProtocol protocol = new TBinaryProtocol(transport);
	
	TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol, "Calculator");
	Calculator.Client service = new Calculator.Client(mp);
	
	TMultiplexedProtocol mp2 = new TMultiplexedProtocol(protocol, "WeatherReport");
	WeatherReport.Client service2 = new WeatherReport.Client(mp2);
	
	System.out.println(service.add(2,2));
	System.out.println(service2.getTemperature());
	
Sample Usage - Server
	TMultiplexedProcessor processor = new TMultiplexedProcessor();
	
	processor.registerProcessor(
	    "Calculator",
	    new Calculator.Processor(new CalculatorHandler()));
	
	processor.registerProcessor(
	    "WeatherReport",
	    new WeatherReport.Processor(new WeatherReportHandler()));
	
	TServerTransport t = new TServerSocket(9090);
	TSimpleServer server = new TSimpleServer(processor, t);
	
	server.serve();

*/

