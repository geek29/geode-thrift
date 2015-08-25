/*    */ package com.github.geodethrift.cli.result;
/*    */ 
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class GfshResultContent
/*    */ {
/*  7 */   private JSONObject jsonObject = null;
/*  8 */   private JSONObject content = null;
/*  9 */   private String header = null;
/* 10 */   private String footer = null;
/* 11 */   private String type = null;
/* 12 */   private GfshInfoResult infoResult = null;
/* 13 */   private GfshTableResult tableResult = null;
/* 14 */   private GfshCompositeResult compositeResult = null;
/* 15 */   private GfshErrorResult errorResult = null;
/*    */ 
/*    */   public GfshResultContent(JSONObject data, String type) {
/* 18 */     this.jsonObject = data;
/* 19 */     this.type = type;
/* 20 */     build(type);
/*    */   }
/*    */ 
/*    */   private void build(String type) {
/* 24 */     this.content = this.jsonObject.getJSONObject("content");
/* 25 */     this.footer = this.jsonObject.getString("footer");
/* 26 */     this.header = this.jsonObject.getString("header");
/* 27 */     if ("info".equals(type))
/* 28 */       buildInfoContent(this.content);
/* 29 */     else if ("table".equals(type))
/* 30 */       buildTableContent(this.content);
/* 31 */     else if ("composite".equals(type))
/* 32 */       buildCompositeContent(this.content);
/* 33 */     else if ("error".equals(type))
/* 34 */       buildErrorContent(this.content);
/*    */   }
/*    */ 
/*    */   private void buildErrorContent(JSONObject content)
/*    */   {
/* 39 */     this.errorResult = new GfshErrorResult(content);
/*    */   }
/*    */ 
/*    */   private void buildCompositeContent(JSONObject content) {
/* 43 */     this.compositeResult = new GfshCompositeResult(content);
/*    */   }
/*    */ 
/*    */   public GfshCompositeResult getCompositeResult() {
/* 47 */     return this.compositeResult;
/*    */   }
/*    */ 
/*    */   private void buildTableContent(JSONObject content) {
/* 51 */     this.tableResult = new GfshTableResult(content);
/*    */   }
/*    */ 
/*    */   private void buildInfoContent(JSONObject content) {
/* 55 */     this.infoResult = new GfshInfoResult(content);
/*    */   }
/*    */ 
/*    */   public String getHeader() {
/* 59 */     return this.header;
/*    */   }
/*    */ 
/*    */   public String getFooter() {
/* 63 */     return this.footer;
/*    */   }
/*    */ 
/*    */   public String getType() {
/* 67 */     return this.type;
/*    */   }
/*    */ 
/*    */   public GfshInfoResult getInfoResult() {
/* 71 */     return this.infoResult;
/*    */   }
/*    */ 
/*    */   public GfshTableResult getTableResult() {
/* 75 */     return this.tableResult;
/*    */   }
/*    */ 
/*    */   public GfshErrorResult getErrorResult() {
/* 79 */     return this.errorResult;
/*    */   }
/*    */ }

/* Location:           /tushark1/bugs/webgfsh/WEB-INF/classes/
 * Qualified Name:     com.vmware.gemfire.tools.pulse.result.GfshResultContent
 * JD-Core Version:    0.6.2
 */