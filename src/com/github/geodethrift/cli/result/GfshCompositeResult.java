/*    */ package com.github.geodethrift.cli.result;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class GfshCompositeResult
/*    */ {
/* 10 */   private JSONObject jsonObject = null;
/*    */ 
/* 12 */   private List<String> sectionNames = null;
/* 13 */   private String header = null;
/*    */ 
/* 20 */   private List<GfshSectionResult> sections = new ArrayList();
/*    */ 
/*    */   public GfshCompositeResult(JSONObject data)
/*    */   {
/* 16 */     this.jsonObject = data;
/* 17 */     build();
/*    */   }
/*    */ 
/*    */   private void build()
/*    */   {
/* 23 */     Set keys = this.jsonObject.keySet();
/* 24 */     this.sectionNames = new ArrayList();
/* 25 */     for (Iterator localIterator = keys.iterator(); localIterator.hasNext(); ) { Object k = localIterator.next();
/* 26 */       if (k.toString().contains(GfshSectionResult.pattern)) {
/* 27 */         this.sectionNames.add(k.toString());
/* 28 */         this.sections.add(GfshResult.createSection(this.jsonObject, k.toString()));
/* 29 */       } else if (k.toString().equals("header")) {
/* 30 */         this.header = this.jsonObject.getString("header");
/*    */       } }
/*    */   }
/*    */ 
/*    */   public String getHeader()
/*    */   {
/* 36 */     return this.header;
/*    */   }
/*    */ 
/*    */   public List<GfshSectionResult> getSections() {
/* 40 */     return this.sections;
/*    */   }
/*    */ }

/* Location:           /tushark1/bugs/webgfsh/WEB-INF/classes/
 * Qualified Name:     com.vmware.gemfire.tools.pulse.result.GfshCompositeResult
 * JD-Core Version:    0.6.2
 */