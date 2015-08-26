package com.github.geek29.geodethrift.management.result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GfshTableResult
{
  private JSONObject jsonObject = null;

  private List<String> colNames = null;
  private Object[][] data = null;

  public GfshTableResult(JSONObject data) throws JSONException
  {
    this.jsonObject = data;
    build();
  }

  private void build() throws JSONException {    
    this.colNames = new ArrayList<String>();

    int maxRows = 0;
    JSONArray array;
    for (Iterator localIterator = this.jsonObject.keys(); localIterator.hasNext(); ) { Object k = localIterator.next();
      this.colNames.add(k.toString());
      array = this.jsonObject.getJSONArray(k.toString());
      if (maxRows < array.length())
        maxRows = array.length();
    }
    this.data = new Object[maxRows][this.colNames.size()];

    int col = 0;
    for (String column : this.colNames) {
      JSONArray array1 = this.jsonObject.getJSONArray(column);
      for (int row = 0; row < array1.length(); row++) {
        Object object = array1.get(row);
        this.data[row][col] = GfshResult.stringify(object);
      }
      col++;
    }
  }

  public List<String> getColNames() {
    return this.colNames;
  }

  public Object[][] getData() {
    return this.data;
  }
}
