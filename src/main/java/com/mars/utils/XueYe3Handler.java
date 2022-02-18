package com.mars.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.aspectj.weaver.ast.Var;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class XueYe3Handler {
    private Configuration configuration = null;

    public XueYe3Handler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public String createXueYe(HttpServletRequest request, Map<String, String> maps) {
        // 要填入模本的数据文件
        Map dataMap = new HashMap();
        if (maps.isEmpty()){
            System.out.println("该未填写学业规划（三）");
            return null;
        }
        if (maps.get("protarget")!=null){
            dataMap.put("protarget",maps.get("protarget"));
        }else{
            dataMap.put("protarget","未填写");
        }
        if (maps.get("gentarget")!=null){
            dataMap.put("gentarget",maps.get("gentarget"));
        }else{
            dataMap.put("gentarget","未填写");
        }
        if (maps.get("inntarget")!=null){
            dataMap.put("inntarget",maps.get("inntarget"));
        }else{
            dataMap.put("inntarget","未填写");
        }
        if (maps.get("proshijian")!=null){
            dataMap.put("proshijian",maps.get("proshijian"));
        }else{
            dataMap.put("proshijian","未填写");
        }
        if (maps.get("genshijian")!=null){
            dataMap.put("genshijian",maps.get("genshijian"));
        }else{
            dataMap.put("genshijian","未填写");
        }
        if (maps.get("innshijian")!=null){
            dataMap.put("innshijian",maps.get("innshijian"));
        }else{
            dataMap.put("innshijian","未填写");
        }
        if (maps.get("profangfa")!=null){
            dataMap.put("profangfa",maps.get("profangfa"));
        }else{
            dataMap.put("profangfa","未填写");
        }
        if (maps.get("genfangfa")!=null){
            dataMap.put("genfangfa",maps.get("genfangfa"));
        }else{
            dataMap.put("genfangfa","未填写");
        }
        if (maps.get("innfangfa")!=null){
            dataMap.put("innfangfa",maps.get("innfangfa"));
        }else{
            dataMap.put("innfangfa","未填写");
        }
        if (maps.get("profenxi")!=null){
            dataMap.put("profenxi",maps.get("profenxi"));
        }else{
            dataMap.put("profenxi","未填写");
        }
        if (maps.get("genfenxi")!=null){
            dataMap.put("genfenxi",maps.get("genfenxi"));
        }else{
            dataMap.put("genfenxi","未填写");
        }
        if (maps.get("innfenxi")!=null){
            dataMap.put("innfenxi",maps.get("innfenxi"));
        }else{
            dataMap.put("innfenxi","未填写");
        }
        if (maps.get("inncuoshi")!=null){
            dataMap.put("inncuoshi",maps.get("inncuoshi"));
        }else{
            dataMap.put("inncuoshi","未填写");
        }
        if (maps.get("procuoshi")!=null){
            dataMap.put("procuoshi",maps.get("procuoshi"));
        }else{
            dataMap.put("procuoshi","未填写");
        }
        if (maps.get("gencuoshi")!=null){
            dataMap.put("gencuoshi",maps.get("gencuoshi"));
        }else{
            dataMap.put("gencuoshi","未填写");
        }

        Template t = null;
        String filePath = request.getSession().getServletContext().getRealPath("/doc");
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }


        String fileName=maps.get("studentid")+"学业规划（三）.doc";
        File outFile = new File(folder, fileName);  //输出文档路径及名称
        Writer out = null;
        try {
            configuration.setClassForTemplateLoading(this.getClass(),"/static");
        } catch (Exception e) {
            System.out.println("路径找不到");
            e.printStackTrace();
        }
        try {
            t = configuration.getTemplate("xueye3.ftl");
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
