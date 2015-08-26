package com.github.geek29.geodethrift.management.result;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class GfshSectionResult
{
  public static String pattern = "__sections__";
  public static String seppattern = "__separator__";
  public static String tabpattern = "__tables__";
  private JSONObject jsonObject = null;
  private String header = "";

  private List<GfshSectionResult> sections = new ArrayList();
  private List<GfshTableResult> tables = new ArrayList();
  private Map<String, String> sectionMap = new HashMap();

  public GfshSectionResult(JSONObject data) throws JSONException {
    this.jsonObject = data;
    build();
  }

  private void build() throws JSONException {
    Iterator iterator = this.jsonObject.keys();
    while (iterator.hasNext()) {
      String key = (String)iterator.next();
      if (key.contains(pattern)) {
        this.sections.add(GfshResult.createSection(this.jsonObject, key));
      } else if (key.contains(tabpattern)) {
        JSONObject object = this.jsonObject.getJSONObject(key);
        JSONObject content = object.getJSONObject("content");
        GfshTableResult table = new GfshTableResult(content);
        this.tables.add(table);
      } else if (key.equals("header")) {
        this.header = this.jsonObject.getString("header");
      } else if (!key.contains(seppattern))
      {
        String value = GfshResult.getString(this.jsonObject, key);
        System.out.println("adding key " + key);
        this.sectionMap.put(key, value);
      }
    }
  }

  public List<GfshSectionResult> getSections() {
    return this.sections;
  }

  public List<GfshTableResult> getTables() {
    return this.tables;
  }

  public Map<String, String> getSectionMap() {
    return this.sectionMap;
  }

  public String getHeader() {
    return this.header;
  }
}
