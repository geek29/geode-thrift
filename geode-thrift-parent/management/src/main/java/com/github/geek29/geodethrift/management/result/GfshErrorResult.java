package com.github.geek29.geodethrift.management.result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GfshErrorResult
{
  private JSONObject jsonObject = null;
  private int errorCode;
  private String[] messageArray = null;

  public GfshErrorResult(JSONObject content) throws JSONException {
    this.jsonObject = content;
    build();
  }

  private void build() throws JSONException {
    if (this.jsonObject.has("errorCode"))
      this.errorCode = this.jsonObject.getInt("errorCode");
    JSONArray infoMessageArray = this.jsonObject.getJSONArray("message");
    this.messageArray = new String[infoMessageArray.length()];
    for (int i = 0; i < this.messageArray.length; i++)
      this.messageArray[i] = infoMessageArray.getString(i);
  }

  public int getErrorCode()
  {
    return this.errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String[] getMessageArray() {
    return this.messageArray;
  }

  public void setMessageArray(String[] messageArray) {
    this.messageArray = messageArray;
  }
}
