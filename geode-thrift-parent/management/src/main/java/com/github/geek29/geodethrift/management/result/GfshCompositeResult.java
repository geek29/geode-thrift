package com.github.geek29.geodethrift.management.result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

public class GfshCompositeResult
{
  private JSONObject jsonObject = null;

  private List<String> sectionNames = null;
  private String header = null;

  private List<GfshSectionResult> sections = new ArrayList();

  public GfshCompositeResult(JSONObject data) throws JSONException
  {
    this.jsonObject = data;
    build();
  }

  private void build() throws JSONException
  {    
    this.sectionNames = new ArrayList();
    for (Iterator localIterator = this.jsonObject.keys(); localIterator.hasNext(); ) { Object k = localIterator.next();
      if (k.toString().contains(GfshSectionResult.pattern)) {
        this.sectionNames.add(k.toString());
        this.sections.add(GfshResult.createSection(this.jsonObject, k.toString()));
      } else if (k.toString().equals("header")) {
        this.header = this.jsonObject.getString("header");
      } }
  }

  public String getHeader()
  {
    return this.header;
  }

  public List<GfshSectionResult> getSections() {
    return this.sections;
  }
}
