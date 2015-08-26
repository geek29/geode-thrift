package com.github.geodethrift.cli.result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

public class GfshCompositeResult
{
  private JSONObject jsonObject = null;

  private List<String> sectionNames = null;
  private String header = null;

  private List<GfshSectionResult> sections = new ArrayList();

  public GfshCompositeResult(JSONObject data)
  {
    this.jsonObject = data;
    build();
  }

  private void build()
  {
    Set keys = this.jsonObject.keySet();
    this.sectionNames = new ArrayList();
    for (Iterator localIterator = keys.iterator(); localIterator.hasNext(); ) { Object k = localIterator.next();
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
