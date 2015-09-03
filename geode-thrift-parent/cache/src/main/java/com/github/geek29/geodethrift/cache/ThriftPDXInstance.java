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
import com.github.geek29.geodethrift.cache.FieldConverters.FieldConverter;

public class ThriftPDXInstance {
	
	//package level for testing
	static Map<Class,Map<String,FieldConverter>> metadataMap = new HashMap<Class,Map<String,FieldConverter>>();	
	
	private PdxInstanceFactory factory = null;
	private TBase thriftObject = null;
	
	public ThriftPDXInstance(TBase thriftObject, PdxInstanceFactory factory)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		this.factory = factory;
		introspectClass(thriftObject.getClass());
		addToPDX(thriftObject);
	}
	
	public ThriftPDXInstance(PdxInstance ch, String klass) throws ClassNotFoundException, 
	IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException {
		Class klasss = Class.forName(klass);
		if(TBase.class.isAssignableFrom(klasss))
		introspectClass(klasss);
		converToTbase(ch, klasss);
	}
	
	public ThriftPDXInstance(PdxInstance ch, Class klasss) throws ClassNotFoundException, 
	IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException {
		if(TBase.class.isAssignableFrom(klasss))
		introspectClass(klasss);
		converToTbase(ch, klasss);
	}

	private void converToTbase(PdxInstance ch, Class klasss) throws InstantiationException, IllegalAccessException {
		Map<String,FieldConverter> writeMap = metadataMap.get(klasss);
		thriftObject = (TBase) klasss.newInstance();
		for(Entry<String,FieldConverter> entry : writeMap.entrySet()) {			
			FieldConverter reader = entry.getValue();
			System.out.println("Getting value for field " + entry.getKey());
			reader.read(ch, thriftObject);								
		}	
		
	}

	//package level for testing
	static void introspectClass(Class tbaseKlass) throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		if(metadataMap.containsKey(tbaseKlass))
			return;
		synchronized (tbaseKlass) {
			Map<String,FieldConverter> writerMap = new HashMap<String,FieldConverter>();
			Map<String,Field> fieldMap = new HashMap<String,Field>();
			//for(Field f : tbase.getClass().getDeclaredFields()){
			Field medataMapField = tbaseKlass.getField("metaDataMap");
			Map<?,FieldMetaData> metaDataMap = (Map<?,FieldMetaData>) medataMapField.get("_1st");
			for(FieldMetaData mtdt : metaDataMap.values()) {			
				Field getter = tbaseKlass.getField(mtdt.fieldName);
				FieldConverter writer = getWriter(getter.getType(),getter);
				writerMap.put(mtdt.fieldName, writer);
				fieldMap.put(mtdt.fieldName,getter);
			}
			//}
			System.out.println("introspectClass Tbase class  " + tbaseKlass+"\n " +writerMap);
			metadataMap.put(tbaseKlass, writerMap);
		}		
	}

	private static FieldConverter getWriter(Class<?> type, Field f) {
		if(int.class.equals(type)){
			return new FieldConverters.IntConverter(f);
		} else if(byte.class.equals(type)) {
			return new FieldConverters.ByteConverter(f);
		} else if(short.class.equals(type)) {
			return new FieldConverters.ShortConverter(f);
		} else if (long.class.equals(type)) {
			return new FieldConverters.LongConverter(f);
		} else if (float.class.equals(type)) {
			return new FieldConverters.FloatConverter(f);
		} else if (double.class.equals(type)) {
			return new FieldConverters.DoubleConverter(f);
		} else if (char.class.equals(type)) {
			return new FieldConverters.CharConverter(f);
		} else if (boolean.class.equals(type)) {
			return new FieldConverters.BooleanConverter(f);
		} else if (String.class.equals(type)) {
			return new FieldConverters.StringConverter(f);
		} else if (Map.class.equals(type)) {
			return new FieldConverters.ListAndMapConverter(f);
		} else if (List.class.equals(type)) {
			return new FieldConverters.ListAndMapConverter(f);
		} else if (ByteBuffer.class.equals(type)) {
			//Need special care here TODO
			return new FieldConverters.ByteConverter(f);
		}
		return null;
	}

	private void addToPDX(TBase tbase) throws NoSuchFieldException, SecurityException, 
		IllegalArgumentException, IllegalAccessException  {		
		Map<String,FieldConverter> writeMap = metadataMap.get(tbase.getClass());
		for(Entry<String,FieldConverter> entry : writeMap.entrySet()) {			
			FieldConverter writer = entry.getValue();
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

	public TBase getTbase() {
		return thriftObject;
	}
	
}