package com.github.geek29.geodethrift.management;


import static org.junit.Assert.*;

import java.util.Map;
import java.util.Properties;

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
import com.gemstone.gemfire.management.internal.cli.result.CommandResult;
import com.github.geek29.geodethrift.management.structs.PutArgs;

public class CommandExecutorTest {
	
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
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	private PutArgs createPut() {
		PutArgs args = new PutArgs();
		args.key = "k1";
		args.value = "v1";
		args.region = "/region1";
		return args;
	}

	@Test
	public void testIntrospect() {
		CommandExecutor executor = new CommandExecutor();
		PutArgs args = 	createPut();
		Map<String,String> map = executor.introspect(args);		
		assertEquals(4,map.size());
		assertTrue(map.containsKey("key"));
		assertTrue(map.containsKey("value"));
		assertTrue(map.containsKey("region"));
		assertTrue(map.containsKey("skipIfExists"));
		
		//test for string option value containing space
		args.value = "Jon Snow is not dead";
		map = executor.introspect(args);
		assertEquals("\"Jon Snow is not dead\"",map.get("value"));
	}
	
	@Test
	public void testCommandString() {
		CommandExecutor executor = new CommandExecutor();
		PutArgs args = 	createPut();
		String command = executor.commandString("put", executor.introspect(args));		
		assertTrue(command.contains("put"));
		assertTrue(command.contains(" --key=k1"));
		assertTrue(command.contains(" --value=v1"));
		assertTrue(command.contains(" --region=/region1"));
		assertTrue(command.contains(" --skip-if-exists=false"));
	}
	
	@Test
	public void testCommandStringWithDefaults() {
		CommandExecutor executor = new CommandExecutor();
		PutArgs args = 	new PutArgs();
		String command = executor.commandString("put", executor.introspect(args));		
		assertTrue(command.contains("put"));
		assertFalse(command.contains(" --key=k1"));
		assertFalse(command.contains(" --value=v1"));
		assertFalse(command.contains(" --region=/region1"));
		assertTrue(command.contains(" --skip-if-exists=false"));
	}
	
	@Test
	public void testExecuteCommandString() {
		CommandExecutor executor = new CommandExecutor();
		PutArgs args = 	createPut();		
		String command = executor.commandString("put", executor.introspect(args));
		CommandResult result = (CommandResult) executor.executeCommandString(command);
		Status st = result.getStatus();
		assertEquals(Status.OK, st);
		System.out.println("Resul " + result);
		assertNotNull(result);
	}

}
