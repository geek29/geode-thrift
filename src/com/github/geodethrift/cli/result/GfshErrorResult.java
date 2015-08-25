/*    */ package com.github.geodethrift.cli.result;
/*    */ 
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class GfshErrorResult
/*    */ {
/*  8 */   private JSONObject jsonObject = null;
/*    */   private int errorCode;
/* 10 */   private String[] messageArray = null;
/*    */ 
/*    */   public GfshErrorResult(JSONObject content) {
/* 13 */     this.jsonObject = content;
/* 14 */     build();
/*    */   }
/*    */ 
/*    */   private void build() {
/* 18 */     if (this.jsonObject.has("errorCode"))
/* 19 */       this.errorCode = this.jsonObject.getInt("errorCode");
/* 20 */     JSONArray infoMessageArray = this.jsonObject.getJSONArray("message");
/* 21 */     this.messageArray = new String[infoMessageArray.length()];
/* 22 */     for (int i = 0; i < this.messageArray.length; i++)
/* 23 */       this.messageArray[i] = infoMessageArray.getString(i);
/*    */   }
/*    */ 
/*    */   public int getErrorCode()
/*    */   {
/* 29 */     return this.errorCode;
/*    */   }
/*    */ 
/*    */   public void setErrorCode(int errorCode) {
/* 33 */     this.errorCode = errorCode;
/*    */   }
/*    */ 
/*    */   public String[] getMessageArray() {
/* 37 */     return this.messageArray;
/*    */   }
/*    */ 
/*    */   public void setMessageArray(String[] messageArray) {
/* 41 */     this.messageArray = messageArray;
/*    */   }
/*    */ }

/* Location:           /tushark1/bugs/webgfsh/WEB-INF/classes/
 * Qualified Name:     com.vmware.gemfire.tools.pulse.result.GfshErrorResult
 * JD-Core Version:    0.6.2
 */