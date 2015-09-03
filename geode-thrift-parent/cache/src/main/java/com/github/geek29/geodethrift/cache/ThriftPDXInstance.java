package com.github.geek29.geodethrift.cache;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldMetaData;

import com.gemstone.gemfire.pdx.PdxInstance;
import com.gemstone.gemfire.pdx.PdxInstanceFactory;
import com.github.geek29.geodethrift.cache.FieldWriters.FieldWriter;

public class ThriftPDXInstance {
	
	//package level for testing
	static Map<Class,Map<String,FieldWriter>> metadataMap = new HashMap<Class,Map<String,FieldWriter>>();	
	
	private PdxInstanceFactory factory = null;
	
	public ThriftPDXInstance(TBase thriftObject, PdxInstanceFactory factory)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		this.factory = factory;
		introspectClass(thriftObject);
		addToPDX(thriftObject);
	}
	
	//package level for testing
	static void introspectClass(TBase tbase) throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		if(metadataMap.containsKey(tbase.getClass()))
			return;
		
		
		
		synchronized (tbase.getClass()) {
			Map<String,FieldWriter> writerMap = new HashMap<String,FieldWriter>();
			Map<String,Field> fieldMap = new HashMap<String,Field>();
			for(Field f : tbase.getClass().getDeclaredFields()){
				if(f.getName().equals("metaDataMap")) {
					Map<?,FieldMetaData> metaDataMap = (Map<?,FieldMetaData>) f.get(tbase);
					for(FieldMetaData mtdt : metaDataMap.values()) {			
						Field getter = tbase.getClass().getField(mtdt.fieldName);
						FieldWriter writer = getWriter(getter.getType(),getter);
						writerMap.put(mtdt.fieldName, writer);
						fieldMap.put(mtdt.fieldName,getter);
					}
				}
			}
			System.out.println("introspectClass Tbase class  " + tbase.getClass()+"\n " +writerMap);
			metadataMap.put(tbase.getClass(), writerMap);
		}		
	}

	private static FieldWriter getWriter(Class<?> type, Field f) {
		if(int.class.equals(type)){
			return new FieldWriters.IntWriter(f);
		} else if(byte.class.equals(type)) {
			return new FieldWriters.ByteWriter(f);
		} else if(short.class.equals(type)) {
			return new FieldWriters.ShortWriter(f);
		} else if (long.class.equals(type)) {
			return new FieldWriters.LongWriter(f);
		} else if (float.class.equals(type)) {
			return new FieldWriters.FloatWriter(f);
		} else if (double.class.equals(type)) {
			return new FieldWriters.DoubleWriter(f);
		} else if (char.class.equals(type)) {
			return new FieldWriters.CharWriter(f);
		} else if (boolean.class.equals(type)) {
			return new FieldWriters.BooleanWriter(f);
		} else if (String.class.equals(type)) {
			return new FieldWriters.StringWriter(f);
		} else if (Map.class.equals(type)) {
			return new FieldWriters.ListAndMapWriter(f);
		} else if (List.class.equals(type)) {
			return new FieldWriters.ListAndMapWriter(f);
		} else if (ByteBuffer.class.equals(type)) {
			//Need special care here TODO
			return new FieldWriters.ByteWriter(f);
		}
		return null;
	}

	private void addToPDX(TBase tbase) throws NoSuchFieldException, SecurityException, 
		IllegalArgumentException, IllegalAccessException  {		
		Map<String,FieldWriter> writeMap = metadataMap.get(tbase.getClass());
		for(Entry<String,FieldWriter> entry : writeMap.entrySet()) {			
			FieldWriter writer = entry.getValue();
			System.out.println("Getting value for field " + entry.getKey());
			writer.write(factory, tbase);								
		}		
	}

	private boolean isThriftStruct(Field getter) {		
		return getter.getType().equals(TBase.class);
	}

	public PdxInstance getPDXInstance() {
		return factory.create();
	}
	
}