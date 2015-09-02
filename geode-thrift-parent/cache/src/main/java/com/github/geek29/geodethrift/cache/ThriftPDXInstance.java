package com.github.geek29.geodethrift.cache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldMetaData;

import com.gemstone.gemfire.pdx.PdxInstance;
import com.gemstone.gemfire.pdx.PdxInstanceFactory;

public class ThriftPDXInstance {
	
	//package level for testing
	static Map<Class,Map<String,Writer>> metadataMap = new HashMap<Class,Map<String,Writer>>();	
	
	private PdxInstanceFactory factory = null;
	
	public ThriftPDXInstance(TBase thriftObject, PdxInstanceFactory factory)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		this.factory = factory;
		introspectClass(thriftObject);
		addToPDX(thriftObject);
	}
	
	//package level for testing
	void introspectClass(TBase tbase) throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		if(metadataMap.containsKey(tbase.getClass()))
			return;
		
		System.out.println("introspectClass Tbase class  " + tbase.getClass());
		
		synchronized (tbase.getClass()) {
			Map<String,Writer> writerMap = new HashMap<String,Writer>();
			Map<String,Field> fieldMap = new HashMap<String,Field>();
			for(Field f : tbase.getClass().getDeclaredFields()){
				if(f.getName().equals("metaDataMap")) {
					Map<?,FieldMetaData> metaDataMap = (Map<?,FieldMetaData>) f.get(tbase);
					for(FieldMetaData mtdt : metaDataMap.values()) {			
						Field getter = tbase.getClass().getField(mtdt.fieldName);
						Writer writer = writerMap.get(getter.getType());
						writerMap.put(mtdt.fieldName, writer);
						fieldMap.put(mtdt.fieldName,getter);
					}
				}
			}
			metadataMap.put(tbase.getClass(), writerMap);
		}		
	}

	private void addToPDX(TBase tbase) throws NoSuchFieldException, SecurityException, 
		IllegalArgumentException, IllegalAccessException  {		
		Map<String,Writer> writeMap = metadataMap.get(tbase.getClass());
		for(Entry<String,Writer> entry : writeMap.entrySet()) {			
			Writer writer = entry.getValue();			
			writer.write(factory, tbase);								
		}		
	}

	private boolean isThriftStruct(Field getter) {		
		return getter.getType().equals(TBase.class);
	}

	public PdxInstance getPDXInstance() {
		return factory.create();
	}
	
	public static class WriterException extends RuntimeException {

		public WriterException(Exception e) {
			super(e);
		}
		
	}
	
	public static interface Writer {
		public void write(PdxInstanceFactory factory, Object object);
	}
	
	
	/*	
	Below taken from PdxInstanceFactory
	 
	Write following writers : 
	
		writeChar(String, char)
		writeBoolean(String, boolean)
		writeByte(String, byte)
		writeShort(String, short)
		writeInt(String, int)
		writeLong(String, long)
		writeFloat(String, float)
		writeDouble(String, double)
		writeString(String, String)
		
		writeDate(String, Date) - NotRequired
		writeBooleanArray(String, boolean[]) - NotRequired thrift only supports lists and maps
		writeCharArray(String, char[]) - NotRequired thrift only supports lists and maps
		writeByteArray(String, byte[]) - NotRequired thrift only supports lists and maps
		writeShortArray(String, short[]) - NotRequired thrift only supports lists and maps
		writeIntArray(String, int[]) - NotRequired thrift only supports lists and maps
		writeLongArray(String, long[]) - NotRequired thrift only supports lists and maps
		writeFloatArray(String, float[]) - NotRequired thrift only supports lists and maps
		writeDoubleArray(String, double[]) - NotRequired thrift only supports lists and maps
		writeStringArray(String, String[]) - NotRequired thrift only supports lists and maps
		writeObjectArray(String, Object[]) - NotRequired thrift only supports lists and maps
		writeArrayOfByteArrays(String, byte[][]) - Need to see how binary is treated in Thrift	
	*/
	
	public static class IntWriter implements Writer {		
		
		private Field field=null;
		
		public IntWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				int value = field.getInt(object);
				factory.writeInt(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	public static class TBaseWriter implements Writer {
				
		private Field field=null;
		
		public TBaseWriter(Field field) {
			this.field = field;
		}
		
		public void write(PdxInstanceFactory factory, Object object) {
			TBase inner;
			try {
				inner = (TBase) field.get(object);
				ThriftPDXInstance instance = new ThriftPDXInstance(inner, factory);
				factory.writeObject(field.getName(), instance.getPDXInstance());	
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			} catch (NoSuchFieldException e) {
				throw new WriterException(e);
			} catch (SecurityException e) {
				throw new WriterException(e);
			}			
		}
	}

}