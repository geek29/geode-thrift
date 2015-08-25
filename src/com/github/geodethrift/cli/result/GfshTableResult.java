/*    */ package com.github.geodethrift.cli.result;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class GfshTableResult
/*    */ {
/* 11 */   private JSONObject jsonObject = null;
/*    */ 
/* 13 */   private List<String> colNames = null;
/* 14 */   private Object[][] data = null;
/*    */ 
/*    */   public GfshTableResult(JSONObject data)
/*    */   {
/* 18 */     this.jsonObject = data;
/* 19 */     build();
/*    */   }
/*    */ 
/*    */   private void build() {
/* 23 */     Set keys = this.jsonObject.keySet();
/* 24 */     this.colNames = new ArrayList<String>();
/*    */ 
/* 26 */     int maxRows = 0;
/*    */     JSONArray array;
/* 28 */     for (Iterator localIterator = keys.iterator(); localIterator.hasNext(); ) { Object k = localIterator.next();
/* 29 */       this.colNames.add(k.toString());
/* 30 */       array = this.jsonObject.getJSONArray(k.toString());
/* 31 */       if (maxRows < array.length())
/* 32 */         maxRows = array.length();
/*    */     }
/* 34 */     this.data = new Object[maxRows][this.colNames.size()];
/*    */ 
/* 36 */     int col = 0;
/* 37 */     for (String column : this.colNames) {
/* 38 */       JSONArray array1 = this.jsonObject.getJSONArray(column);
/* 39 */       for (int row = 0; row < array1.length(); row++) {
/* 40 */         Object object = array1.get(row);
/* 41 */         this.data[row][col] = GfshResult.stringify(object);
/*    */       }
/* 43 */       col++;
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<String> getColNames() {
/* 48 */     return this.colNames;
/*    */   }
/*    */ 
/*    */   public Object[][] getData() {
/* 52 */     return this.data;
/*    */   }
/*    */ }

/* Location:           /tushark1/bugs/webgfsh/WEB-INF/classes/
 * Qualified Name:     com.vmware.gemfire.tools.pulse.result.GfshTableResult
 * JD-Core Version:    0.6.2
 */