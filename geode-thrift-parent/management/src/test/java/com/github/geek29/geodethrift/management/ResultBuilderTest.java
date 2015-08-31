package com.github.geek29.geodethrift.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Properties;

import org.json.JSONException;
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
import com.gemstone.gemfire.management.internal.cli.CommandManager;
import com.gemstone.gemfire.management.internal.cli.result.CommandResult;
import com.gemstone.gemfire.management.internal.cli.result.CompositeResultData;
import com.gemstone.gemfire.management.internal.cli.result.CompositeResultData.SectionResultData;
import com.gemstone.gemfire.management.internal.cli.result.ErrorResultData;
import com.gemstone.gemfire.management.internal.cli.result.InfoResultData;
import com.gemstone.gemfire.management.internal.cli.result.ResultBuilder;
import com.gemstone.gemfire.management.internal.cli.result.TabularResultData;
import com.github.geek29.geodethrift.management.commandResult.CompositeResult;
import com.github.geek29.geodethrift.management.commandResult.ErrorResult;
import com.github.geek29.geodethrift.management.commandResult.InfoResult;
import com.github.geek29.geodethrift.management.commandResult.Section;
import com.github.geek29.geodethrift.management.commandResult.TableResult;

public class ResultBuilderTest {

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
		//GemFireCacheImpl.getExisting().close();	
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//startGemFire();
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

	@Test
	public void testInfoResult() throws JSONException {
		InfoResultData data = ResultBuilder.createInfoResultData().addLine("one1").addLine("Two");
		CommandResult result = (CommandResult) ResultBuilder.buildResult(data);
		com.github.geek29.geodethrift.management.commandResult.CommandResult thriftResult = 
				com.github.geek29.geodethrift.management.ResultBuilder.from(result);
		assertEquals("info", thriftResult.contentType);
		InfoResult infoResult = thriftResult.content.infoResult;
		assertNotNull(infoResult);
		assertEquals(2,infoResult.messages.size());
	}
	
	
	@Test
	public void testErrorResult() throws JSONException {
		int errorCode = 324;
		ErrorResultData data = ResultBuilder.createErrorResultData()
				.setErrorCode(errorCode).addLine("one").addLine("two");
		CommandResult result = (CommandResult) ResultBuilder.buildResult(data);
		com.github.geek29.geodethrift.management.commandResult.CommandResult thriftResult = com.github.geek29.geodethrift.management.ResultBuilder
				.from(result);
		assertEquals("error", thriftResult.contentType);
		ErrorResult errorResult = thriftResult.content.errorResult;
		assertNotNull(errorResult);
		assertEquals(2, errorResult.messages.size());
		assertEquals(errorCode, errorResult.errorCode);
	}
	
	/**
	 * TODO Add test for section-within-section
	 * @throws JSONException
	 */
	@Test
	public void testCompositeResult() throws JSONException {
		CompositeResultData data = ResultBuilder.createCompositeResultData();
		SectionResultData section = data.addSection();
		populateSection(section, true);		
		CommandResult result = (CommandResult) ResultBuilder.buildResult(data);
		com.github.geek29.geodethrift.management.commandResult.CommandResult thriftResult = com.github.geek29.geodethrift.management.ResultBuilder
				.from(result);
		assertEquals("composite", thriftResult.contentType);
		CompositeResult compositeResult = thriftResult.content.compositeResult;
		assertNotNull(compositeResult);		
		assertNotNull(compositeResult.sectionNames);
		assertNotNull(compositeResult.sections);
		assertEquals(1,compositeResult.sections.size());
		Section thriftSection = compositeResult.sections.get(0);
		validateSection(thriftSection, true);					
	}

	
	@Test
	public void testTableResult() throws JSONException {
		TabularResultData table1 = ResultBuilder.createTabularResultData();
		populateTable(table1);		
		CommandResult result = (CommandResult) ResultBuilder.buildResult(table1);
		com.github.geek29.geodethrift.management.commandResult.CommandResult thriftResult = com.github.geek29.geodethrift.management.ResultBuilder
				.from(result);
		assertEquals("table", thriftResult.contentType);
		TableResult tableResult = thriftResult.content.tableResult;
		assertNotNull(tableResult);
		validateTable(tableResult);
		
	}
	
	private void populateSection(SectionResultData section, boolean addTable){
		section.addData("k1", "v1");
		if(addTable) {
			TabularResultData table1 = section.addTable();
			populateTable(table1);
		}
	}
	
	private void validateSection(Section thriftSection, boolean hasTable) {
		assertNotNull(thriftSection.sectionMap);
		assertEquals(1,thriftSection.sectionMap.size());
		assertTrue(thriftSection.sectionMap.containsKey("k1"));
		assertEquals("v1",thriftSection.sectionMap.get("k1"));
		if(hasTable) {
			assertNotNull(thriftSection.tables);
			assertEquals(1,thriftSection.tables.size());
			validateTable(thriftSection.tables.get(0));	
		}
	}
	
	private void populateTable(TabularResultData table1) {
		table1.accumulate("col1", "r1c1");
		table1.accumulate("col2", "r1c2");
		table1.accumulate("col1", "r2c1");
		table1.accumulate("col2", "r2c2");
	}

	private void validateTable(TableResult table) {
		assertNotNull(table.data);
		assertEquals(2, table.cols);
		assertEquals(2, table.rows);
		assertEquals(2, table.colNames.size());
		assertTrue(table.colNames.contains("col1"));
		assertTrue(table.colNames.contains("col2"));
		assertEquals(2,table.data.size());
		List<String> r1 = table.data.get(0);
		List<String> r2 = table.data.get(1);
		
		assertTrue(r1.contains("r1c1"));
		assertTrue(r1.contains("r2c1"));
		assertTrue(r2.contains("r1c2"));
		assertTrue(r2.contains("r2c2"));		
	}

	

}
