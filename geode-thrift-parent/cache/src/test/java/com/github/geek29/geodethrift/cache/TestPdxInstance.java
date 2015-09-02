package com.github.geek29.geodethrift.cache;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

import org.apache.thrift.meta_data.FieldMetaData;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.RegionFactory;
import com.gemstone.gemfire.cache.RegionShortcut;
import com.gemstone.gemfire.distributed.DistributedSystem;
import com.gemstone.gemfire.internal.cache.GemFireCacheImpl;
import com.gemstone.gemfire.management.ManagementService;
import com.gemstone.gemfire.management.internal.cli.CommandManager;

public class TestPdxInstance {
	
	private static CommandManager startGemFire() {
		Properties pr = new Properties();
		pr.put("jmx-manager", "true");
		pr.put("jmx-manager-start", "true");
		DistributedSystem ds = DistributedSystem.connect(pr);
		Cache cache = new CacheFactory().setPdxReadSerialized(false).create();
		GemFireCacheImpl impl = (GemFireCacheImpl)cache;
		ManagementService service = ManagementService.getManagementService(cache);
		CommandManager manager = CommandManager.getExisting();
		//createRegion("region1", impl);
		return manager;
	}
	
	private static Region createRegion(String name, GemFireCacheImpl impl) {
		RegionFactory factory = impl.createRegionFactory(RegionShortcut.REPLICATE);
		return factory.create(name);		
	}

	private static void stopGemfire() {
		GemFireCacheImpl.getExisting().close();	
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {		
		/*startGemFire();
		GemFireCacheImpl impl = GemFireCacheImpl.getExisting();
		Region pdxRegion = createRegion("pdxRegion", impl);
		
		PdxInstance inner = impl.createPdxInstanceFactory("com.company.Day", false)
					.writeString("day", "FRIDAY")
					.writeInt("ordinal", 5)
					.create();
		
		PdxInstance pi = impl.createPdxInstanceFactory("com.company.DomainObject", false)
				   .writeInt("id", 37)
				   .markIdentityField("id")
				   .writeString("name", "Mike Smith")
				   .writeObject("favoriteDay", inner)
				   .create();*/
		/*
		PutArgs put = new PutArgs((short)10,"mykey","myvalue","thrfitregion");
		org.apache.thrift.TBase tbase = put;		
		
		System.out.println("Tbase class  " + tbase.getClass());
		System.out.println("Fields");
		for(Field f : tbase.getClass().getDeclaredFields()){
			if(f.getName().equals("metaDataMap")) {
				//Map<org.apache.thrift.TFieldIdEnum, org.apache.thrift.meta_data.FieldMetaData> metaDataMap = (Map<_org.apache.thrift.TFieldIdEnum, FieldMetaData>) f.get(put);				
				Map<?,FieldMetaData> metaDataMap = (Map<?,FieldMetaData>) f.get(put);
				System.out.println("Map Values " + metaDataMap.values());
				for(FieldMetaData mtdt : metaDataMap.values()) {					
					Field getter = tbase.getClass().getField(mtdt.fieldName);
					System.out.println("name " + mtdt.fieldName + "value " + getter.get(tbase));
				}
			}
		}*/
		
		/*pdxRegion.put("1", pi);
		Object pi2 = pdxRegion.get("1");
		System.out.println("Pi2 " + pi2);
		PdxInstance piRead = (PdxInstance) pi2;
		System.out.println("Name " + piRead.getField("name"));
		System.out.println("id " + piRead.getField("id"));
		System.out.println("favoriteDay " + piRead.getField("favoriteDay"));
		impl.close();*/
	}

}
