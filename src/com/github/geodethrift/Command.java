/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.github.geodethrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Command implements org.apache.thrift.TBase<Command, Command._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Command");

  private static final org.apache.thrift.protocol.TField COMMAND_FIELD_DESC = new org.apache.thrift.protocol.TField("command", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ENV_FIELD_DESC = new org.apache.thrift.protocol.TField("env", org.apache.thrift.protocol.TType.MAP, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CommandStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CommandTupleSchemeFactory());
  }

  public String command; // required
  public Map<String,String> env; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COMMAND((short)1, "command"),
    ENV((short)2, "env");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // COMMAND
          return COMMAND;
        case 2: // ENV
          return ENV;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COMMAND, new org.apache.thrift.meta_data.FieldMetaData("command", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ENV, new org.apache.thrift.meta_data.FieldMetaData("env", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Command.class, metaDataMap);
  }

  public Command() {
  }

  public Command(
    String command,
    Map<String,String> env)
  {
    this();
    this.command = command;
    this.env = env;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Command(Command other) {
    if (other.isSetCommand()) {
      this.command = other.command;
    }
    if (other.isSetEnv()) {
      Map<String,String> __this__env = new HashMap<String,String>();
      for (Map.Entry<String, String> other_element : other.env.entrySet()) {

        String other_element_key = other_element.getKey();
        String other_element_value = other_element.getValue();

        String __this__env_copy_key = other_element_key;

        String __this__env_copy_value = other_element_value;

        __this__env.put(__this__env_copy_key, __this__env_copy_value);
      }
      this.env = __this__env;
    }
  }

  public Command deepCopy() {
    return new Command(this);
  }

  @Override
  public void clear() {
    this.command = null;
    this.env = null;
  }

  public String getCommand() {
    return this.command;
  }

  public Command setCommand(String command) {
    this.command = command;
    return this;
  }

  public void unsetCommand() {
    this.command = null;
  }

  /** Returns true if field command is set (has been assigned a value) and false otherwise */
  public boolean isSetCommand() {
    return this.command != null;
  }

  public void setCommandIsSet(boolean value) {
    if (!value) {
      this.command = null;
    }
  }

  public int getEnvSize() {
    return (this.env == null) ? 0 : this.env.size();
  }

  public void putToEnv(String key, String val) {
    if (this.env == null) {
      this.env = new HashMap<String,String>();
    }
    this.env.put(key, val);
  }

  public Map<String,String> getEnv() {
    return this.env;
  }

  public Command setEnv(Map<String,String> env) {
    this.env = env;
    return this;
  }

  public void unsetEnv() {
    this.env = null;
  }

  /** Returns true if field env is set (has been assigned a value) and false otherwise */
  public boolean isSetEnv() {
    return this.env != null;
  }

  public void setEnvIsSet(boolean value) {
    if (!value) {
      this.env = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case COMMAND:
      if (value == null) {
        unsetCommand();
      } else {
        setCommand((String)value);
      }
      break;

    case ENV:
      if (value == null) {
        unsetEnv();
      } else {
        setEnv((Map<String,String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COMMAND:
      return getCommand();

    case ENV:
      return getEnv();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case COMMAND:
      return isSetCommand();
    case ENV:
      return isSetEnv();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Command)
      return this.equals((Command)that);
    return false;
  }

  public boolean equals(Command that) {
    if (that == null)
      return false;

    boolean this_present_command = true && this.isSetCommand();
    boolean that_present_command = true && that.isSetCommand();
    if (this_present_command || that_present_command) {
      if (!(this_present_command && that_present_command))
        return false;
      if (!this.command.equals(that.command))
        return false;
    }

    boolean this_present_env = true && this.isSetEnv();
    boolean that_present_env = true && that.isSetEnv();
    if (this_present_env || that_present_env) {
      if (!(this_present_env && that_present_env))
        return false;
      if (!this.env.equals(that.env))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(Command other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Command typedOther = (Command)other;

    lastComparison = Boolean.valueOf(isSetCommand()).compareTo(typedOther.isSetCommand());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCommand()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.command, typedOther.command);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEnv()).compareTo(typedOther.isSetEnv());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEnv()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.env, typedOther.env);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Command(");
    boolean first = true;

    sb.append("command:");
    if (this.command == null) {
      sb.append("null");
    } else {
      sb.append(this.command);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("env:");
    if (this.env == null) {
      sb.append("null");
    } else {
      sb.append(this.env);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CommandStandardSchemeFactory implements SchemeFactory {
    public CommandStandardScheme getScheme() {
      return new CommandStandardScheme();
    }
  }

  private static class CommandStandardScheme extends StandardScheme<Command> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Command struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // COMMAND
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.command = iprot.readString();
              struct.setCommandIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ENV
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map0 = iprot.readMapBegin();
                struct.env = new HashMap<String,String>(2*_map0.size);
                for (int _i1 = 0; _i1 < _map0.size; ++_i1)
                {
                  String _key2; // required
                  String _val3; // required
                  _key2 = iprot.readString();
                  _val3 = iprot.readString();
                  struct.env.put(_key2, _val3);
                }
                iprot.readMapEnd();
              }
              struct.setEnvIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Command struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.command != null) {
        oprot.writeFieldBegin(COMMAND_FIELD_DESC);
        oprot.writeString(struct.command);
        oprot.writeFieldEnd();
      }
      if (struct.env != null) {
        oprot.writeFieldBegin(ENV_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.env.size()));
          for (Map.Entry<String, String> _iter4 : struct.env.entrySet())
          {
            oprot.writeString(_iter4.getKey());
            oprot.writeString(_iter4.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CommandTupleSchemeFactory implements SchemeFactory {
    public CommandTupleScheme getScheme() {
      return new CommandTupleScheme();
    }
  }

  private static class CommandTupleScheme extends TupleScheme<Command> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Command struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCommand()) {
        optionals.set(0);
      }
      if (struct.isSetEnv()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetCommand()) {
        oprot.writeString(struct.command);
      }
      if (struct.isSetEnv()) {
        {
          oprot.writeI32(struct.env.size());
          for (Map.Entry<String, String> _iter5 : struct.env.entrySet())
          {
            oprot.writeString(_iter5.getKey());
            oprot.writeString(_iter5.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Command struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.command = iprot.readString();
        struct.setCommandIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TMap _map6 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.env = new HashMap<String,String>(2*_map6.size);
          for (int _i7 = 0; _i7 < _map6.size; ++_i7)
          {
            String _key8; // required
            String _val9; // required
            _key8 = iprot.readString();
            _val9 = iprot.readString();
            struct.env.put(_key8, _val9);
          }
        }
        struct.setEnvIsSet(true);
      }
    }
  }

}
