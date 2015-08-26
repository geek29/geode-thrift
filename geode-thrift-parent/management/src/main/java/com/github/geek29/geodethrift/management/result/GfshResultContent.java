package com.github.geodethrift.cli.result;

import org.json.JSONObject;

public class GfshResultContent
{
  private JSONObject jsonObject = null;
  private JSONObject content = null;
  private String header = null;
  private String footer = null;
  private String type = null;
  private GfshInfoResult infoResult = null;
  private GfshTableResult tableResult = null;
  private GfshCompositeResult compositeResult = null;
  private GfshErrorResult errorResult = null;

  public GfshResultContent(JSONObject data, String type) {
    this.jsonObject = data;
    this.type = type;
    build(type);
  }

  private void build(String type) {
    this.content = this.jsonObject.getJSONObject("content");
    this.footer = this.jsonObject.getString("footer");
    this.header = this.jsonObject.getString("header");
    if ("info".equals(type))
      buildInfoContent(this.content);
    else if ("table".equals(type))
      buildTableContent(this.content);
    else if ("composite".equals(type))
      buildCompositeContent(this.content);
    else if ("error".equals(type))
      buildErrorContent(this.content);
  }

  private void buildErrorContent(JSONObject content)
  {
    this.errorResult = new GfshErrorResult(content);
  }

  private void buildCompositeContent(JSONObject content) {
    this.compositeResult = new GfshCompositeResult(content);
  }

  public GfshCompositeResult getCompositeResult() {
    return this.compositeResult;
  }

  private void buildTableContent(JSONObject content) {
    this.tableResult = new GfshTableResult(content);
  }

  private void buildInfoContent(JSONObject content) {
    this.infoResult = new GfshInfoResult(content);
  }

  public String getHeader() {
    return this.header;
  }

  public String getFooter() {
    return this.footer;
  }

  public String getType() {
    return this.type;
  }

  public GfshInfoResult getInfoResult() {
    return this.infoResult;
  }

  public GfshTableResult getTableResult() {
    return this.tableResult;
  }

  public GfshErrorResult getErrorResult() {
    return this.errorResult;
  }
}
