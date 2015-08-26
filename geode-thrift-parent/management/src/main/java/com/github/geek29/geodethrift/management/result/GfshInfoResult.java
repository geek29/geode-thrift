package com.github.geek29.geodethrift.management.result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GfshInfoResult
{
  private JSONObject jsonObject = null;
  private JSONArray infoMessageArray = null;
  private String[] messageArray = null;

  public GfshInfoResult(JSONObject data) throws JSONException {
    this.jsonObject = data;
    build();
  }

  private void build() throws JSONException {
    this.infoMessageArray = this.jsonObject.getJSONArray("message");
    this.messageArray = new String[this.infoMessageArray.length()];
    for (int i = 0; i < this.messageArray.length; i++)
      this.messageArray[i] = this.infoMessageArray.getString(i);
  }

  public String[] getMessageArray()
  {
    return this.messageArray;
  }
}
