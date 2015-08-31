package com.github.geek29.geodethrift.management;

import static org.junit.Assert.*;

import java.util.Properties;

import org.apache.thrift.TException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.RegionFactory;
import com.gemstone.gemfire.cache.RegionShortcut;
import com.gemstone.gemfire.distributed.DistributedSystem;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.ManagementService;
import com.gemstone.gemfire.management.cli.Result.Status;
import com.gemstone.gemfire.management.internal.cli.CommandManager;
import com.github.geek29.geodethrift.management.commandResult.CommandResult;
import com.github.geek29.geodethrift.management.service.GeodeCommandService;
import com.github.geek29.geodethrift.management.structs.GetArgs;
import com.github.geek29.geodethrift.management.structs.PutArgs;
import com.github.geek29.geodethrift.management.structs.QueryArgs;

public class GeodeManagementHandlerTest {
	
	protected GeodeCommandService.Iface handler = null;

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
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		stopGemfire();
	}

	@Before
	public void setUp() throws Exception {
		handler = new GeodeManagementHandler();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	/*
	@Test
	public void testGeodeManagementHandler() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAlterDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAlterRegion() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAlterRuntime() {
		//fail("Not yet implemented");
	}

	@Test
	public void testBackupDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testChangeLoglevel() {
		//fail("Not yet implemented");
	}

	@Test
	public void testClearDefinedIndexes() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCloseDurableClient() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCloseDurableCq() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCompactDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCompactOfflineDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testConfigurePdx() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateAsyncEventQueue() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateDefinedIndexes() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateGatewayReceiver() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateGatewaySender() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateHdfsStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateIndex() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateRegion() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDefineIndex() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDescribeConfig() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDescribeDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDescribeMember() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDescribeOfflineDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDescribeRegion() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDestroyDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDestroyFunction() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDestroyIndex() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDestroyRegion() {
		//fail("Not yet implemented");
	}

	@Test
	public void testEncryptPassword() {
		//fail("Not yet implemented");
	}

	@Test
	public void testExecuteFunction() {
		//fail("Not yet implemented");
	}

	@Test
	public void testExportClusterConfiguration() {
		//fail("Not yet implemented");
	}

	@Test
	public void testExportConfig() {
		//fail("Not yet implemented");
	}

	@Test
	public void testExportData() {
		//fail("Not yet implemented");
	}

	@Test
	public void testExportLogs() {
		//fail("Not yet implemented");
	}

	@Test
	public void testExportOfflineDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testExportStackTraces() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGc() {
		//fail("Not yet implemented");
	}*/

	@Test
	public void testGet() {
		try {
			PutArgs put = createPut();
			CommandResult result = handler.put(put);
			System.out.println("Result " + result);
			assertEquals(Status.OK.ordinal(),result.status);
			GetArgs get = new GetArgs();
			get.key = put.key;
			get.region = put.region;
			result = handler.get(get);
			System.out.println("Result " + result);
			assertEquals(Status.OK.ordinal(),result.status);
		} catch (TException e) {
			fail(e.getMessage());
		}
	}

	/*
	@Test
	public void testHelp() {
		//fail("Not yet implemented");
	}

	@Test
	public void testHint() {
		//fail("Not yet implemented");
	}

	@Test
	public void testHistory() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListAsyncEventQueues() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListClients() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListDeployed() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListDiskStores() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListDurableCqs() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListFunctions() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListGateways() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListIndexes() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListMembers() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListRegions() {
		//fail("Not yet implemented");
	}

	@Test
	public void testLoadBalanceGatewaySender() {
		//fail("Not yet implemented");
	}

	@Test
	public void testLocateEntry() {
		//fail("Not yet implemented");
	}

	@Test
	public void testNetstat() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPauseGatewaySender() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPdxRename() {
		//fail("Not yet implemented");
	}*/

	@Test
	public void testPut() {
		try {
			CommandResult result = handler.put(createPut());
			System.out.println("Result " + result);
			assertEquals(Status.OK.ordinal(),result.status);			
		} catch (TException e) {
			fail(e.getMessage());
		}
	}

	
	@Test
	public void testQuery() {
		try {
			QueryArgs query = new QueryArgs();
			query.query = "select * from /region1";
			CommandResult result = handler.query(query);
			System.out.println("Query Result " + result);
			assertEquals(Status.OK.ordinal(), result.status);
		} catch (TException e) {
			fail(e.getMessage());
		}
	}

	/*
	@Test
	public void testRebalance() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		//fail("Not yet implemented");
	}

	@Test
	public void testResumeGatewaySender() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRevokeMissingDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetVariable() {
		//fail("Not yet implemented");
	}

	@Test
	public void testShowDeadLocks() {
		//fail("Not yet implemented");
	}

	@Test
	public void testShowLog() {
		//fail("Not yet implemented");
	}

	@Test
	public void testShowMetrics() {
		//fail("Not yet implemented");
	}

	@Test
	public void testShowMissingDiskStores() {
		//fail("Not yet implemented");
	}

	@Test
	public void testShowSubscriptionQueueSize() {
		//fail("Not yet implemented");
	}

	@Test
	public void testShutdown() {
		//fail("Not yet implemented");
	}

	@Test
	public void testStatusClusterConfigService() {
		//fail("Not yet implemented");
	}

	@Test
	public void testStatusGatewayReceiver() {
		//fail("Not yet implemented");
	}

	@Test
	public void testStatusGatewaySender() {
		//fail("Not yet implemented");
	}

	@Test
	public void testStatusLocator() {
		//ail("Not yet implemented");
	}

	@Test
	public void testStatusServer() {
		//fail("Not yet implemented");
	}

	@Test
	public void testStopGatewayReceiver() {
		//fail("Not yet implemented");
	}

	@Test
	public void testStopGatewaySender() {
		//fail("Not yet implemented");
	}

	@Test
	public void testStopLocator() {
		//fail("Not yet implemented");
	}

	@Test
	public void testStopServer() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUndeploy() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUpgradeOfflineDiskStore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testValidateOfflineDiskStore() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testVersion() {
		handler.v
	}*/
	
	private PutArgs createPut() {
		PutArgs args = new PutArgs();
		args.key = "k1";
		args.value = "v1";
		args.region = "/region1";
		return args;
	}

}
