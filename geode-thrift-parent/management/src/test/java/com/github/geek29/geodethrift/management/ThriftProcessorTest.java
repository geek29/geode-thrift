package com.github.geek29.geodethrift.management;

import java.util.Properties;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.RegionFactory;
import com.gemstone.gemfire.cache.RegionShortcut;
import com.gemstone.gemfire.distributed.DistributedSystem;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.ManagementService;
import com.gemstone.gemfire.management.internal.cli.CommandManager;
import com.github.geek29.geodethrift.management.service.GeodeCommandService;

public class ThriftProcessorTest extends GeodeManagementHandlerTest {

	private static TServer server = null;
	private static GeodeCommandService.Client client = null;
	
	private static CommandManager startGemFire() {
		Properties pr = new Properties();
		pr.put("jmx-manager", "true");
		pr.put("jmx-manager-start", "true");
		DistributedSystem ds = DistributedSystem.connect(pr);
		Cache cache = new CacheFactory().create();
		GemFireCacheImpl impl = (GemFireCacheImpl)cache;
		ManagementService service = ManagementService.getManagementService(cache);
		CommandManager manager = CommandManager.getExisting();
		createRegion("region1", impl);
		return manager;
	}
	
	private static void createRegion(String name, GemFireCacheImpl impl) {
		RegionFactory factory = impl.createRegionFactory(RegionShortcut.REPLICATE);
		factory.create(name);		
	}

	private static void stopGemfire() {
		GemFireCacheImpl.getExisting().close();	
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		startGemFire();
		startThriftServer();
	}

	private static void startThriftServer() throws TTransportException {
		GeodeCommandService.Processor processor = new GeodeCommandService.Processor(new GeodeManagementHandler());
		TServerTransport serverTransport = new TServerSocket(9090);
		server = new TSimpleServer(
				new Args(serverTransport).processor(processor));
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("Starting the test-thrift server...");
				server.serve();
			}
			
		}).start();
		
		TSocket transport = new TSocket("localhost", 9090);
		transport.open();
		TBinaryProtocol protocol = new TBinaryProtocol(transport);
		client = new GeodeCommandService.Client(protocol);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		stopGemfire();
		stopThriftServer();
	}

	private static void stopThriftServer() {
		client.getInputProtocol().getTransport().close();
		client.getOutputProtocol().getTransport().close();
		server.stop();
	}

	@Before
	public void setUp() throws Exception {
		this.handler = client;
	}
	
}
