package com.github.geek29.geodethrift.cache;

import java.lang.reflect.Field;

import org.apache.thrift.TBase;

import com.gemstone.gemfire.pdx.PdxInstanceFactory;

public class FieldWriters {
	
	public static class WriterException extends RuntimeException {

		public WriterException(Exception e) {
			super(e);
		}
		
	}
	
	public static interface FieldWriter {
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
	
	
	
	public static class CharWriter implements FieldWriter {		
		
		private Field field=null;
		
		public CharWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				char value = field.getChar(object);
				factory.writeChar(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	public static class BooleanWriter implements FieldWriter {		
		
		private Field field=null;
		
		public BooleanWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				boolean value = field.getBoolean(object);
				factory.writeBoolean(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	public static class ByteWriter implements FieldWriter {		
		
		private Field field=null;
		
		public ByteWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				byte value = field.getByte(object);
				factory.writeByte(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	public static class ShortWriter implements FieldWriter {		
		
		private Field field=null;
		
		public ShortWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				short value = field.getShort(object);
				factory.writeShort(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	public static class IntWriter implements FieldWriter {		
		
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
	
	public static class LongWriter implements FieldWriter {		
		
		private Field field=null;
		
		public LongWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				 long value = field.getLong(object);
				factory.writeLong(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	public static class FloatWriter implements FieldWriter {		
		
		private Field field=null;
		
		public FloatWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				 float value = field.getFloat(object);
				factory.writeFloat(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	public static class DoubleWriter implements FieldWriter {		
		
		private Field field=null;
		
		public DoubleWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				 double value = field.getDouble(object);
				factory.writeDouble(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	
	
	public static class StringWriter implements FieldWriter {		
		
		private Field field=null;
		
		public StringWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				String value = (String)field.get(object);
				factory.writeString(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	public static class ListAndMapWriter implements FieldWriter {		
		
		private Field field=null;
		
		public ListAndMapWriter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				factory.writeObject(field.getName(), field.get(object));
			} catch (IllegalArgumentException e) {
				throw new WriterException(e);
			} catch (IllegalAccessException e) {
				throw new WriterException(e);
			}			
		}		
	}
	
	public static class TBaseWriter implements FieldWriter {
				
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
