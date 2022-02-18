package com.mars.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mars.dao.ClassDao;
import com.mars.dao.MajorDao;
import com.mars.dao.TeacherDao;
import com.mars.vo.ResponseData;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class UpClassController {

    @Autowired
    ClassDao classDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    MajorDao majorDao;


    @PostMapping("/importClassFromExcel")
    public ResponseData importTeacherFromExcel(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
        try {
            if (uploadFile == null) {
                return ResponseData.buildError("文件上传失败");
            }
            String uploadPath = request.getSession().getServletContext().getRealPath("/")
                    + File.separator;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = uploadFile.getOriginalFilename();
            String originalFileName = fileName.substring(0, fileName.lastIndexOf("."));
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = originalFileName + "_" + UUID.randomUUID().toString() + suffix;
            File file = new File(uploadPath, newFileName);
            try {
                uploadFile.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<String[]> fileData = null;
            if (suffix.equals(".xls")) {
                return addClass(getExcel(file.getAbsolutePath()));
            } else {
                return ResponseData.buildError("文件格式不正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.buildError("服务器异常！");
        }
    }

    public List<Map<String, String>> getExcel(String file) {
        Workbook workbook;
        Sheet sheet;
        Cell cell1, cell2, cell3, cell4, cell5;
        JSONArray jsons = new JSONArray();
        try {
            workbook = Workbook.getWorkbook(new File(file));  //工作簿
            sheet = workbook.getSheet(0);  //获取第一张表（编号为0）
            for (int i = 1; i < sheet.getRows(); i++) {  //教师记录从第2行开始，第1行是列名
                cell1 = sheet.getCell(0, i);
                cell2 = sheet.getCell(1, i);
                cell3 = sheet.getCell(2, i);
                cell4 = sheet.getCell(3, i);
                cell5 = sheet.getCell(4, i);
                //每一行创建一个JSONObject对象
                JSONObject object = new JSONObject();
                object.put("name", cell1.getContents().trim());
                object.put("teacherName", cell2.getContents().trim());
                object.put("teacherNo", cell3.getContents().trim());
                object.put("majorName", cell4.getContents().trim());
                object.put("yearName", cell5.getContents().trim());
                jsons.add(object);  //添加
            }
            List<Map<String, String>> listObjectFir = (List<Map<String, String>>) JSONArray.parse(jsons.toJSONString());
            workbook.close();
            return listObjectFir;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseData addClass(List<Map<String, String>> classList) {
        // 检查电子表格有无重复
        ArrayList<String> names = new ArrayList<>();
        for (Map<String, String> map : classList) {
            String name = map.get("name");  //获取教工号
            if(!names.contains(name)){
                names.add(name);
            }else{
                return ResponseData.buildError("Excel表有重复的班级："+name);
            }
        }


        // 检查数据正确性
        for (Map<String, String> map : classList) {
            if (classDao.findByName(map.get("name"))>0)
                return ResponseData.buildError("班级重复");
            if (teacherDao.existsName(map.get("teacherNo"),map.get("teacherName"))==0)
                return ResponseData.buildError("请检查教师信息："+map.get("teacherNo")+"-->"+map.get("teacherName")+"是否正确");
            if (majorDao.existsMajor(map.get("majorName"))==0)
                return ResponseData.buildError("专业不存在");
        }
        for (Map<String, String> map : classList) {
            try {
                classDao.add(map.get("name"),map.get("teacherNo"),map.get("majorName"),map.get("yearName"));
            }catch (Exception e){
                return ResponseData.buildError("导入班级失败,请联系服务器管理员");
            }
        }
        return ResponseData.buildSuccess("导入班级成功");
    }
}
