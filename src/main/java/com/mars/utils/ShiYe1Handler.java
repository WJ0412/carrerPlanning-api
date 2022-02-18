package com.mars.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ShiYe1Handler {
    private Configuration configuration = null;

    public ShiYe1Handler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public String createShiYe(HttpServletRequest request, Map<String, String> maps) {
        // 要填入模本的数据文件
        Map dataMap = new HashMap();
        if (maps.isEmpty()){
            System.out.println("该未填写事业规划");
            return null;
        }
        if (maps.get("mubiao")!=null){
            dataMap.put("mubiao",maps.get("mubiao"));
        }else{
            dataMap.put("mubiao","未填写");
        }
        if (maps.get("jubeisuzhi")!=null){
            dataMap.put("jubeisuzhi",maps.get("jubeisuzhi"));
        }else{
            dataMap.put("jubeisuzhi","未填写");
        }
        if (maps.get("buzu")!=null){
            dataMap.put("buzu",maps.get("buzu"));
        }else{
            dataMap.put("buzu","未填写");
        }
        if (maps.get("cuoshi")!=null){
            dataMap.put("cuoshi",maps.get("cuoshi"));
        }else{
            dataMap.put("cuoshi","未填写");
        }



        Template t = null;
        String filePath = request.getSession().getServletContext().getRealPath("/doc");
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }


        String fileName=maps.get("studentid")+"事业规划.doc";
        File outFile = new File(folder, fileName);  //输出文档路径及名称
        Writer out = null;
        try {
            configuration.setClassForTemplateLoading(this.getClass(),"/static");
        } catch (Exception e) {
            System.out.println("路径找不到");
            e.printStackTrace();
        }
        try {
            t = configuration.getTemplate("shiye.ftl");
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
