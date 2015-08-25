/*     */ package com.github.geodethrift.cli.result;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class GfshResult
/*     */ {
/*  12 */   private String jsonString = null;
/*  13 */   private JSONObject jsonObject = null;
/*     */ 
/*  15 */   private String contentType = null;
/*     */   private int status;
/*  17 */   private String version = null;
/*  18 */   private String sender = null;
/*  19 */   private String debugInfo = null;
/*  20 */   private String when = null;
/*  21 */   private GfshResultContent content = null;
/*     */ 
/*     */   public GfshResult(String json) {
/*  24 */     this.jsonString = json;
/*  25 */     this.jsonObject = new JSONObject(json);
/*  26 */     build();
/*     */   }
/*     */ 
/*     */   private void build() {
/*  30 */     this.contentType = this.jsonObject.getString("contentType");
/*  31 */     this.version = this.jsonObject.getString("version");
/*  32 */     this.sender = this.jsonObject.getString("sender");
/*  33 */     this.debugInfo = this.jsonObject.getString("debugInfo");
/*  34 */     this.when = this.jsonObject.getString("when");
/*  35 */     this.status = this.jsonObject.getInt("status");
/*  36 */     buildData();
/*     */   }
/*     */ 
/*     */   private void buildData() {
/*  40 */     JSONObject object = this.jsonObject.getJSONObject("data");
/*  41 */     this.content = new GfshResultContent(object, this.contentType);
/*     */   }
/*     */ 
/*     */   public String getContentType() {
/*  45 */     return this.contentType;
/*     */   }
/*     */ 
/*     */   public int getStatus() {
/*  49 */     return this.status;
/*     */   }
/*     */ 
/*     */   public String getVersion() {
/*  53 */     return this.version;
/*     */   }
/*     */ 
/*     */   public String getSender() {
/*  57 */     return this.sender;
/*     */   }
/*     */ 
/*     */   public String getDebugInfo() {
/*  61 */     return this.debugInfo;
/*     */   }
/*     */ 
/*     */   public String getWhen() {
/*  65 */     return this.when;
/*     */   }
/*     */ 
/*     */   public GfshResultContent getContent() {
/*  69 */     return this.content;
/*     */   }
/*     */ 
/*     */   public static String readFile(String file) throws IOException {
/*  73 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/*  74 */     String line = null;
/*  75 */     StringBuilder stringBuilder = new StringBuilder();
/*  76 */     String ls = System.getProperty("line.separator");
/*     */ 
/*  78 */     while ((line = reader.readLine()) != null) {
/*  79 */       stringBuilder.append(line);
/*  80 */       stringBuilder.append(ls);
/*     */     }
/*     */ 
/*  83 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws IOException
/*     */   {
/*  88 */     String infoJson = readFile("/home/tushark/Downloads/info-result.json");
/*  89 */     String tableJson = readFile("/home/tushark/Downloads/tabular-result.json");
/*  90 */     String compositeJson = readFile("/home/tushark/Downloads/composite-result.json");
/*  91 */     String errorJson = readFile("/home/tushark/Downloads/error-result.json");
/*  92 */     toGfshResult(infoJson);
/*  93 */     toGfshResult(tableJson);
/*  94 */     toGfshResult(compositeJson);
/*  95 */     toGfshResult(errorJson);
/*     */   }
/*     */ 
/*     */   private static void toGfshResult(String infoJson) {
/*  99 */     GfshResult result = new GfshResult(infoJson);
/* 100 */     System.out.println("contentType : " + result.getContentType());
/* 101 */     System.out.println("status : " + result.getStatus());
/*     */   }
/*     */ 
/*     */   public static GfshSectionResult createSection(JSONObject object, String key) {
/* 105 */     System.out.println("Creating section for key " + key);
/* 106 */     JSONObject section = object.getJSONObject(key);
/* 107 */     GfshSectionResult sectionResult = new GfshSectionResult(section);
/* 108 */     return sectionResult;
/*     */   }
/*     */ 
/*     */   public static String getString(JSONObject jsonObject, String key) {
/* 112 */     Object object = jsonObject.get(key);
/* 113 */     return stringify(object);
/*     */   }
/*     */ 
/*     */   public static String stringify(Object object) {
/* 117 */     if (object == null)
/* 118 */       return null;
/* 119 */     if (object.equals(JSONObject.NULL)) {
/* 120 */       return null;
/*     */     }
/* 122 */     if ((object instanceof String)) {
/* 123 */       return (String)object;
/*     */     }
/*     */ 
/* 126 */     return object.toString();
/*     */   }
/*     */ }

/* Location:           /tushark1/bugs/webgfsh/WEB-INF/classes/
 * Qualified Name:     com.vmware.gemfire.tools.pulse.result.GfshResult
 * JD-Core Version:    0.6.2
 */