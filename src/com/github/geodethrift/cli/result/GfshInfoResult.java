/*    */ package com.github.geodethrift.cli.result;
/*    */ 
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class GfshInfoResult
/*    */ {
/*  8 */   private JSONObject jsonObject = null;
/*  9 */   private JSONArray infoMessageArray = null;
/* 10 */   private String[] messageArray = null;
/*    */ 
/*    */   public GfshInfoResult(JSONObject data) {
/* 13 */     this.jsonObject = data;
/* 14 */     build();
/*    */   }
/*    */ 
/*    */   private void build() {
/* 18 */     this.infoMessageArray = this.jsonObject.getJSONArray("message");
/* 19 */     this.messageArray = new String[this.infoMessageArray.length()];
/* 20 */     for (int i = 0; i < this.messageArray.length; i++)
/* 21 */       this.messageArray[i] = this.infoMessageArray.getString(i);
/*    */   }
/*    */ 
/*    */   public String[] getMessageArray()
/*    */   {
/* 26 */     return this.messageArray;
/*    */   }
/*    */ }

/* Location:           /tushark1/bugs/webgfsh/WEB-INF/classes/
 * Qualified Name:     com.vmware.gemfire.tools.pulse.result.GfshInfoResult
 * JD-Core Version:    0.6.2
 */