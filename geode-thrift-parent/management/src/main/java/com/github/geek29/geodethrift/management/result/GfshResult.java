package com.github.geodethrift.cli.result;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import org.json.JSONObject;

public class GfshResult
{
  private String jsonString = null;
  private JSONObject jsonObject = null;

  private String contentType = null;
  private int status;
  private String version = null;
  private String sender = null;
  private String debugInfo = null;
  private String when = null;
  private GfshResultContent content = null;

  public GfshResult(String json) {
    this.jsonString = json;
    this.jsonObject = new JSONObject(json);
    build();
  }

  private void build() {
    this.contentType = this.jsonObject.getString("contentType");
    this.version = this.jsonObject.getString("version");
    this.sender = this.jsonObject.getString("sender");
    this.debugInfo = this.jsonObject.getString("debugInfo");
    this.when = this.jsonObject.getString("when");
    this.status = this.jsonObject.getInt("status");
    buildData();
  }

  private void buildData() {
    JSONObject object = this.jsonObject.getJSONObject("data");
    this.content = new GfshResultContent(object, this.contentType);
  }

  public String getContentType() {
    return this.contentType;
  }

  public int getStatus() {
    return this.status;
  }

  public String getVersion() {
    return this.version;
  }

  public String getSender() {
    return this.sender;
  }

  public String getDebugInfo() {
    return this.debugInfo;
  }

  public String getWhen() {
    return this.when;
  }

  public GfshResultContent getContent() {
    return this.content;
  }

  public static String readFile(String file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line = null;
    StringBuilder stringBuilder = new StringBuilder();
    String ls = System.getProperty("line.separator");

    while ((line = reader.readLine()) != null) {
      stringBuilder.append(line);
      stringBuilder.append(ls);
    }

    return stringBuilder.toString();
  }

  public static void main(String[] args) throws IOException
  {
    String infoJson = readFile("/home/tushark/Downloads/info-result.json");
    String tableJson = readFile("/home/tushark/Downloads/tabular-result.json");
    String compositeJson = readFile("/home/tushark/Downloads/composite-result.json");
    String errorJson = readFile("/home/tushark/Downloads/error-result.json");
    toGfshResult(infoJson);
    toGfshResult(tableJson);
    toGfshResult(compositeJson);
    toGfshResult(errorJson);
  }

  private static void toGfshResult(String infoJson) {
    GfshResult result = new GfshResult(infoJson);
    System.out.println("contentType : " + result.getContentType());
    System.out.println("status : " + result.getStatus());
  }

  public static GfshSectionResult createSection(JSONObject object, String key) {
    System.out.println("Creating section for key " + key);
    JSONObject section = object.getJSONObject(key);
    GfshSectionResult sectionResult = new GfshSectionResult(section);
    return sectionResult;
  }

  public static String getString(JSONObject jsonObject, String key) {
    Object object = jsonObject.get(key);
    return stringify(object);
  }

  public static String stringify(Object object) {
    if (object == null)
      return null;
    if (object.equals(JSONObject.NULL)) {
      return null;
    }
    if ((object instanceof String)) {
      return (String)object;
    }

    return object.toString();
  }
}
