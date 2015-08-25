/*    */ package com.github.geodethrift.cli.result;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class GfshSectionResult
/*    */ {
/* 13 */   public static String pattern = "__sections__";
/* 14 */   public static String seppattern = "__separator__";
/* 15 */   public static String tabpattern = "__tables__";
/* 16 */   private JSONObject jsonObject = null;
/* 17 */   private String header = "";
/*    */ 
/* 19 */   private List<GfshSectionResult> sections = new ArrayList();
/* 20 */   private List<GfshTableResult> tables = new ArrayList();
/* 21 */   private Map<String, String> sectionMap = new HashMap();
/*    */ 
/*    */   public GfshSectionResult(JSONObject data) {
/* 24 */     this.jsonObject = data;
/* 25 */     build();
/*    */   }
/*    */ 
/*    */   private void build() {
/* 29 */     Iterator iterator = this.jsonObject.keys();
/* 30 */     while (iterator.hasNext()) {
/* 31 */       String key = (String)iterator.next();
/* 32 */       if (key.contains(pattern)) {
/* 33 */         this.sections.add(GfshResult.createSection(this.jsonObject, key));
/* 34 */       } else if (key.contains(tabpattern)) {
/* 35 */         JSONObject object = this.jsonObject.getJSONObject(key);
/* 36 */         JSONObject content = object.getJSONObject("content");
/* 37 */         GfshTableResult table = new GfshTableResult(content);
/* 38 */         this.tables.add(table);
/* 39 */       } else if (key.equals("header")) {
/* 40 */         this.header = this.jsonObject.getString("header");
/* 41 */       } else if (!key.contains(seppattern))
/*    */       {
/* 44 */         String value = GfshResult.getString(this.jsonObject, key);
/* 45 */         System.out.println("adding key " + key);
/* 46 */         this.sectionMap.put(key, value);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<GfshSectionResult> getSections() {
/* 52 */     return this.sections;
/*    */   }
/*    */ 
/*    */   public List<GfshTableResult> getTables() {
/* 56 */     return this.tables;
/*    */   }
/*    */ 
/*    */   public Map<String, String> getSectionMap() {
/* 60 */     return this.sectionMap;
/*    */   }
/*    */ 
/*    */   public String getHeader() {
/* 64 */     return this.header;
/*    */   }
/*    */ }

/* Location:           /tushark1/bugs/webgfsh/WEB-INF/classes/
 * Qualified Name:     com.vmware.gemfire.tools.pulse.result.GfshSectionResult
 * JD-Core Version:    0.6.2
 */