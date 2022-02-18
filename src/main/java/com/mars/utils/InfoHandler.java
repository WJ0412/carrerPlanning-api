package com.mars.utils;

import com.mars.dao.PrintDao;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoHandler {
    private Configuration configuration = null;

    @Autowired
    PrintDao dao;

    public InfoHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public String create(HttpServletRequest request, Map<String, String> infoMap, List<Map<String, String>> family, List<Map<String, String>> other) {
        // 要填入模本的数据文件
        Map dataMap = new HashMap();
        if (infoMap.isEmpty()){
            System.out.println("该未填写基本信息");
            return null;
        }
        if (infoMap.get("name")!=null){
            dataMap.put("name",infoMap.get("name"));
        }else{
            dataMap.put("name","未填写");
        }
        if (infoMap.get("sex")!=null){
            dataMap.put("sex",infoMap.get("sex"));
        }else{
            dataMap.put("sex","未填写");
        }
        if (infoMap.get("birth")!=null){
            dataMap.put("birth",infoMap.get("birth"));
        }else{
            dataMap.put("birth","未填写");
        }
        if (infoMap.get("political")!=null){
            dataMap.put("political",infoMap.get("political"));
        }else{
            dataMap.put("political","未填写");
        }
        if (infoMap.get("nativePlace")!=null){
            dataMap.put("nativePlace",infoMap.get("nativePlace"));
        }else{
            dataMap.put("nativePlace","未填写");
        }
        if (infoMap.get("tel")!=null){
            dataMap.put("tel",infoMap.get("tel"));
        }else{
            dataMap.put("tel","未填写");
        }
        if (infoMap.get("wechat")!=null){
            dataMap.put("wechat",infoMap.get("wechat"));
        }else{
            dataMap.put("wechat","未填写");
        }
        if (infoMap.get("qq")!=null){
            dataMap.put("qq",infoMap.get("qq"));
        }else{
            dataMap.put("qq","未填写");
        }
        // 家庭成员
        if (family.isEmpty()){
            System.out.println("该生尚未填写家庭成员");
            return null;
        }
        if (family.size()<=1){
            if (family.get(0).get("hiscall")!=null){
                dataMap.put("hiscal0",family.get(0).get("hiscall"));
            }else{
                dataMap.put("hiscal0","未填写");
            }
            if (family.get(0).get("name")!=null){
                dataMap.put("name0",family.get(0).get("name"));
            }else{
                dataMap.put("name0","未填写");
            }
            if (family.get(0).get("tel")!=null){
                dataMap.put("tel0",family.get(0).get("tel"));
            }else{
                dataMap.put("tel0","未填写");
            }
            dataMap.put("hiscal1","未填写");
            dataMap.put("name1","未填写");
            dataMap.put("tel1","未填写");
        }else{
            if (family.get(0).get("hiscall")!=null){
                dataMap.put("hiscal0",family.get(0).get("hiscall"));
            }else{
                dataMap.put("hiscal0","未填写");
            }
            if (family.get(0).get("name")!=null){
                dataMap.put("name0",family.get(0).get("name"));
            }else{
                dataMap.put("name0","未填写");
            }
            if (family.get(0).get("tel")!=null){
                dataMap.put("tel0",family.get(0).get("tel"));
            }else{
                dataMap.put("tel0","未填写");
            }
            // 第二位家庭成员
            if (family.get(1).get("hiscall")!=null){
                dataMap.put("hiscal1",family.get(1).get("hiscall"));
            }else{
                dataMap.put("hiscal1","未填写");
            }
            if (family.get(1).get("name")!=null){
                dataMap.put("name1",family.get(1).get("name"));
            }else{
                dataMap.put("name1","未填写");
            }
            if (family.get(1).get("tel")!=null){
                dataMap.put("tel1",family.get(1).get("tel"));
            }else{
                dataMap.put("tel1","未填写");
            }
        }
        // 家庭成员
        if (other.isEmpty()){
            System.out.println("该生尚未填写其他亲属");
            return null;
        }
        if (other.size()<=1){
            if (other.get(0).get("hiscall")!=null){
                dataMap.put("hiscal2",other.get(0).get("hiscall"));
            }else{
                dataMap.put("hiscal2","未填写");
            }
            if (other.get(0).get("name")!=null){
                dataMap.put("name2",other.get(0).get("name"));
            }else{
                dataMap.put("name2","未填写");
            }
            if (other.get(0).get("tel")!=null){
                dataMap.put("tel2",other.get(0).get("tel"));
            }else{
                dataMap.put("tel2","未填写");
            }
            dataMap.put("hiscal3","未填写");
            dataMap.put("name3","未填写");
            dataMap.put("tel3","未填写");
        }else{
            if (other.get(0).get("hiscall")!=null){
                dataMap.put("hiscal2",other.get(0).get("hiscall"));
            }else{
                dataMap.put("hiscal2","未填写");
            }
            if (other.get(0).get("name")!=null){
                dataMap.put("name2",other.get(0).get("name"));
            }else{
                dataMap.put("name2","未填写");
            }
            if (other.get(0).get("tel")!=null){
                dataMap.put("tel2",other.get(0).get("tel"));
            }else{
                dataMap.put("tel2","未填写");
            }
            // 第二位家庭成员
            if (other.get(1).get("hiscall")!=null){
                dataMap.put("hiscal3",other.get(1).get("hiscall"));
            }else{
                dataMap.put("hiscal3","未填写");
            }
            if (other.get(1).get("name")!=null){
                dataMap.put("name3",other.get(1).get("name"));
            }else{
                dataMap.put("name3","未填写");
            }
            if (other.get(1).get("tel")!=null){
                dataMap.put("tel3",other.get(1).get("tel"));
            }else{
                dataMap.put("tel3","未填写");
            }
        }

        Template t = null;
        String filePath = request.getSession().getServletContext().getRealPath("/doc");
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName=infoMap.get("id")+"基本信息.doc";
        File outFile = new File(folder, fileName);  //输出文档路径及名称
        Writer out = null;
        try {
            //使用相对路径方式在打包上传至服务器后会出现路径问题！下面的代码不会出现路径问题。
            configuration.setClassForTemplateLoading(this.getClass(),"/static");
        } catch (Exception e) {
            //System.out.println("路径找不到");
            e.printStackTrace();
        }
        try {
           t = configuration.getTemplate("info.ftl");
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
