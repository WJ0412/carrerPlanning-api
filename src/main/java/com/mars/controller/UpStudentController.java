package com.mars.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mars.dao.*;
import com.mars.vo.ResponseData;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class UpStudentController {

    @Autowired
    StudentDao studentDao;
    @Autowired
    ClassDao classDao;
    @Autowired
    StuInfoDao infoDao;


    @PostMapping("/importStudentFromExcel")
    public ResponseData importStudentFromExcel(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
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
                return addStudent(getExcel(file.getAbsolutePath()));
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
        Cell cell1, cell2, cell3, cell4, cell5,cell6;
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
                cell6 = sheet.getCell(5, i);
                //每一行创建一个JSONObject对象
                JSONObject object = new JSONObject();
                if (!cell1.getContents().trim().equals("")) {
                    object.put("id", cell1.getContents().trim());
                    object.put("name", cell2.getContents().trim());
                    object.put("tel", cell3.getContents().trim());
                    object.put("code", cell4.getContents().trim());
                    object.put("className", cell5.getContents().trim());
                    object.put("xuezhi", cell6.getContents().trim());
                    jsons.add(object);  //添加
                }
            }
            List<Map<String, String>> listObjectFir = (List<Map<String, String>>) JSONArray.parse(jsons.toJSONString());
            workbook.close();
            return listObjectFir;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseData addStudent(List<Map<String, String>> studentList) {
        // 检查电子表格有无重复
        ArrayList<String> ids = new ArrayList<>();
        for (Map<String, String> map : studentList) {
            String id = map.get("id");  //获取学号
            if (!ids.contains(id)) {
                ids.add(id);
            } else {
                return ResponseData.buildError("Excel表有重复的学号：" + map.get("id"));
            }
        }
        // 检查数据正确性
        for (Map<String, String> map : studentList) {
            if (studentDao.findId(map.get("id")) > 0)
                return ResponseData.buildError("学生已存在：" + map.get("id"));
            if (classDao.findByName(map.get("className")) == 0)
                return ResponseData.buildError("班级不存在：" + map.get("className"));
        }
        for (Map<String, String> map : studentList) {
            try {
                studentDao.add(map.get("id"), map.get("id"), map.get("className"),map.get("xuezhi"));
            } catch (Exception e) {
                return ResponseData.buildError("导入学生失败,请联系服务器管理员");
            }
            try {
                String sfz=map.get("code").trim(); // 身份证
                if(sfz.length()==18){ // 身份证应为18位
                    String birth=sfz . substring(6,10); // 截取年份
                    birth+="-";
                    birth+=sfz . substring(10,12);
                    birth+="-";
                    birth+=sfz. substring(12,14);
                    infoDao.addInfo(map.get("id"),map.get("name"),map.get("tel"),map.get("code"),birth);
                }
            }catch (Exception e){
                return ResponseData.buildError("导入学生失败,请联系服务器管理员");
            }
        }
        return ResponseData.buildSuccess("导入学生成功");
    }
}
