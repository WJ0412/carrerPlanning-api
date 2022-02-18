package com.mars.utils;
/*
    DocumentHandler作为一个工具类，生成相应于学生成功规划的word文档
    准备：根据成功规划书的空word文档，生成相应的.xml文件，另存为.ftl的文件，插入占位字段。
    使用freemarker依赖包提供的模板类Template及其配置类Configuration
 */

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RenZhiHandler {
    private Configuration configuration = null;

    public RenZhiHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public String createRenZhi(HttpServletRequest request, Map<String, String> maps) {
        // 要填入模本的数据文件
        Map dataMap = new HashMap();
        if (maps.isEmpty()){
            System.out.println("该未填写自我认知");
            return null;
        }
        if (maps.get("shenti")!=null){
            dataMap.put("st",maps.get("shenti"));
        }else{
            dataMap.put("st","未填写");
        }
        if (maps.get("xinli")!=null){
            dataMap.put("xl",maps.get("xinli"));
        }else{
            dataMap.put("xl","未填写");
        }
        if (maps.get("xingqu")!=null){
            dataMap.put("xq",maps.get("xingqu"));
        }else{
            dataMap.put("xq","未填写");
        }
        if (maps.get("xingge")!=null){
            dataMap.put("xg",maps.get("xingge"));
        }else{
            dataMap.put("xg","未填写");
        }
        if (maps.get("youshi")!=null){
            dataMap.put("ys",maps.get("youshi"));
        }else{
            dataMap.put("ys","未填写");
        }
        if (maps.get("buzu")!=null){
            dataMap.put("bz",maps.get("buzu"));
        }else{
            dataMap.put("bz","未填写");
        }
        if (maps.get("fazhan")!=null){
            dataMap.put("rs",maps.get("fazhan"));
        }else{
            dataMap.put("rs","未填写");
        }

        Template t = null;
        String filePath = request.getSession().getServletContext().getRealPath("/doc");
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }


        String fileName=maps.get("studentid")+"自我认知.doc";
        File outFile = new File(folder, fileName);  //输出文档路径及名称
        Writer out = null;
        try {
            configuration.setClassForTemplateLoading(this.getClass(),"/static");
        } catch (Exception e) {
            System.out.println("路径找不到");
            e.printStackTrace();
        }
        try {
            t = configuration.getTemplate("renzhi.ftl");
            t.setEncoding("utf-8");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
            t.process(dataMap, out); //模板内容替换
            out.close();
        } catch (Exception e) {
           return null;
        }
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/doc/" + fileName;
    }
}
