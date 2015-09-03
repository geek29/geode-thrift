package com.github.geek29.geodethrift.cache;

import java.lang.reflect.Field;

import org.apache.thrift.TBase;

import com.gemstone.gemfire.pdx.PdxInstance;
import com.gemstone.gemfire.pdx.PdxInstanceFactory;

public class FieldConverters {
	
	public static class ConverterException extends RuntimeException {

		public ConverterException(Exception e) {
			super(e);
		}
		
	}
	
	public static interface FieldConverter {
		public void write(PdxInstanceFactory factory, Object object);
		public void read(PdxInstance pdx, Object object);
	}
	
	public static class CharConverter implements FieldConverter {		
		
		private Field field=null;
		
		public CharConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				char value = field.getChar(object);
				factory.writeChar(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				char ch = (Character) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}		
	}
	
	public static class BooleanConverter implements FieldConverter {		
		
		private Field field=null;
		
		public BooleanConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				boolean value = field.getBoolean(object);
				factory.writeBoolean(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				boolean ch = (Boolean) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}		
	}
	
	public static class ByteConverter implements FieldConverter {		
		
		private Field field=null;
		
		public ByteConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				byte value = field.getByte(object);
				factory.writeByte(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				byte ch = (Byte) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}		
	}
	
	public static class ShortConverter implements FieldConverter {		
		
		private Field field=null;
		
		public ShortConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				short value = field.getShort(object);
				factory.writeShort(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				short ch = (Short) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}		
	}
	
	public static class IntConverter implements FieldConverter {		
		
		private Field field=null;
		
		public IntConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				int value = field.getInt(object);
				factory.writeInt(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				int ch = (Integer) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}		
	}
	
	public static class LongConverter implements FieldConverter {		
		
		private Field field=null;
		
		public LongConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				 long value = field.getLong(object);
				factory.writeLong(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				long ch = (Long) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}		
	}
	
	public static class FloatConverter implements FieldConverter {		
		
		private Field field=null;
		
		public FloatConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				 float value = field.getFloat(object);
				factory.writeFloat(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				float ch = (Float) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}		
	}
	
	public static class DoubleConverter implements FieldConverter {		
		
		private Field field=null;
		
		public DoubleConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				 double value = field.getDouble(object);
				factory.writeDouble(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				double ch = (Double) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}			
	}
	
	
	
	public static class StringConverter implements FieldConverter {		
		
		private Field field=null;
		
		public StringConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				String value = (String)field.get(object);
				factory.writeString(field.getName(), value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}
		
		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				String ch = (String) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}	
	}
	
	public static class ListAndMapConverter implements FieldConverter {		
		
		private Field field=null;
		
		public ListAndMapConverter(Field field) {
			this.field = field;
		}

		@Override
		public void write(PdxInstanceFactory factory, Object object) {
			try {
				factory.writeObject(field.getName(), field.get(object));
			} catch (IllegalArgumentException e) {
				throw new ConverterException(e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				Object ch = (Object) pdx.getField(field.getName());
				field.set(object, ch);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			}			
		}			
	}
	
	public static class TBaseConverter implements FieldConverter {
				
		private Field field=null;
		
		public TBaseConverter(Field field) {
			this.field = field;
		}
		
		public void write(PdxInstanceFactory factory, Object object) {
			TBase inner;
			try {
				inner = (TBase) field.get(object);
				ThriftPDXInstance instance = new ThriftPDXInstance(inner, factory);
				factory.writeObject(field.getName(), instance.getPDXInstance());	
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			} catch (NoSuchFieldException e) {
				throw new ConverterException(e);
			} catch (SecurityException e) {
				throw new ConverterException(e);
			}			
		}

		@Override
		public void read(PdxInstance pdx, Object object) {			
			try {
				PdxInstance ch = (PdxInstance) pdx.getField(field.getName());
				ThriftPDXInstance instance = new ThriftPDXInstance(ch, ch.getClassName());
				field.set(object, instance.getTbase());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(e);
			} catch (ClassNotFoundException e) {
				throw new ConverterException(e);
			} catch (NoSuchFieldException e) {
				throw new ConverterException(e);
			} catch (SecurityException e) {
				throw new ConverterException(e);
			} catch (InstantiationException e) {
				throw new ConverterException(e);
			}			
		}	
	}


}
