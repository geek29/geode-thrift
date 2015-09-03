package com.github.geek29.geodethrift.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TBase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.pdx.PdxInstance;
import com.gemstone.gemfire.pdx.PdxInstanceFactory;
import com.github.geek29.geodethrift.cache.FieldConverters.FieldConverter;
import com.github.geek29.geodethrift.cache.FieldConverters.IntConverter;
import com.github.geek29.geodethrift.cache.FieldConverters.LongConverter;

public class ThriftPDXInstanceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GeodeTestHelper.startGemFire();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		GeodeTestHelper.stopGemfire();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIntrospectAndSimpleStruct() throws Exception {
		short shortField = 1;
		int inteField = 2;
		long longField = 3;
		String stringField = "4";
		boolean booleanField = true;
		List<String> listField = new ArrayList<String>();
		Map<String, String> mapField = new HashMap<String, String>();
		listField.add("one");
		listField.add("two");
		listField.add("three");
		mapField.put("one", "1");
		Test1 test1 = new Test1(shortField, inteField, longField, stringField,
				booleanField, listField, mapField);
		
		ThriftPDXInstance.introspectClass(test1.getClass());
		
		Map<String,FieldConverter> writerMap = ThriftPDXInstance.metadataMap.get(Test1.class);
		assertEquals(7,writerMap.size());
		assertEquals(IntConverter.class, writerMap.get("inteField").getClass());
		assertEquals(LongConverter.class, writerMap.get("longField").getClass());
		//TODO : add assert for all kinds of fields
		System.out.println("Map " + writerMap);
	}
	
	@Test
	public void testSimpleStruct() throws Exception {
		short shortField = 1;
		int inteField = 2;
		long longField = 3;
		String stringField = "4";
		boolean booleanField = true;
		List<String> listField = new ArrayList<String>();
		Map<String, String> mapField = new HashMap<String, String>();
		listField.add("one");
		listField.add("two");
		listField.add("three");
		mapField.put("one", "1");
		Test1 test1 = new Test1(shortField, inteField, longField, stringField,
				booleanField, listField, mapField);
		
		GemFireCacheImpl cache = GemFireCacheImpl.getExisting();
		PdxInstanceFactory factory = cache.createPdxInstanceFactory(Test1.class.getCanonicalName(),
				false);		
		ThriftPDXInstance instance = new ThriftPDXInstance(test1, factory);		
		PdxInstance pdx = instance.getPDXInstance();
		System.out.println("PDX="+pdx);
		assertNotNull(pdx);
		assertEquals(shortField, pdx.getField("shortField"));
		List list = (List)pdx.getField("listField");
		Map map = (Map)pdx.getField("mapField");
		System.out.println("List " + list);
		System.out.println("Map " + map);
		assertEquals(3,list.size());
		assertEquals(1,map.size());
		assertTrue(map.containsKey("one"));
		
		ThriftPDXInstance instance2 = new ThriftPDXInstance(pdx, Test1.class.getCanonicalName());
		TBase test2 = (TBase) instance2.getTbase();
		System.out.println("REonverted Tbase " + test2);
		assertNotNull(test2);
		assertEquals(test1,test2);
		
	}
	
	@Test
	public void testNestedStruct() {
		//fail("Not Impleemnted yet");
	}
	
	@Test
	public void testCyclicStruct() {
		//fail("Not Impleemnted yet");
	}
 
}
