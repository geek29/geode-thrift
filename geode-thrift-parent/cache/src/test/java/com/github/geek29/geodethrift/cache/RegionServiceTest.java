package com.github.geek29.geodethrift.cache;

import java.nio.ByteBuffer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;

public class RegionServiceTest {

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
	public void test() {
		Region region = GeodeTestHelper.createRegion("region1",
				GemFireCacheImpl.getExisting());

		String key = "key";
		String value = "value";
		for (int i = 0; i < 5; i++) {
			byte[] keybytes = (key + i).getBytes();
			byte[] valuebytes = (value + i).getBytes();
			region.put(ByteBuffer.wrap(keybytes), ByteBuffer.wrap(valuebytes));
		}

		for (int i = 0; i < 5; i++) {
			byte[] keybytes = (key + i).getBytes();
			ByteBuffer valuebytes = (ByteBuffer) region.get(ByteBuffer.wrap(keybytes));
			System.out.println("Value Retrieved " + new String(valuebytes.array()));
		}

	}

}
