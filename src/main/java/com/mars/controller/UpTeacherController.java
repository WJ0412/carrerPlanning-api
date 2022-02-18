package com.mars.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mars.dao.ClassDao;
import com.mars.dao.CollegeDao;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class UpTeacherController {

    @Autowired
    TeacherDao teacherDao;
    @Autowired
    CollegeDao collegeDao;


    @PostMapping("/importTeacherFromExcel")
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
                return addTeacher(getExcel(file.getAbsolutePath()));
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
        Cell cell1, cell2, cell3, cell4;
        JSONArray jsons = new JSONArray();
        try {
            workbook = Workbook.getWorkbook(new File(file));  //工作簿
            sheet = workbook.getSheet(0);  //获取第一张表（编号为0）
            for (int i = 1; i < sheet.getRows(); i++) {  //教师记录从第2行开始，第1行是列名
                cell1 = sheet.getCell(0, i);
                cell2 = sheet.getCell(1, i);
                cell3 = sheet.getCell(2, i);
                cell4 = sheet.getCell(3, i);
                //每一行创建一个JSONObject对象
                JSONObject object = new JSONObject();
                if (cell1.getContents()!=null&&!cell1.getContents().trim().equals("")){
                    object.put("no", cell1.getContents().trim());
                    object.put("name", cell2.getContents().trim());
                    object.put("pwd",cell1.getContents().trim());
                    object.put("tel", cell3.getContents().trim());
                    object.put("collegeName", cell4.getContents().trim());
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

    public ResponseData addTeacher(List<Map<String, String>> teacherList) {
        // 检查电子表格有无重复
        ArrayList<String> nos = new ArrayList<>();
        for (Map<String, String> map : teacherList) {
            String no = map.get("no");  //获取教工号
            if(!nos.contains(no)){
                nos.add(no);
            }else{
                return ResponseData.buildError("Excel表有重复的教工号："+map.get("no"));
            }
        }
        // 检查数据正确性
        for (Map<String, String> map : teacherList) {
            if (teacherDao.findByNo(map.get("no"))>0)
                return ResponseData.buildError("教师已存在："+map.get("no"));
            if (collegeDao.existCollege(map.get("collegeName"))==0)
                return ResponseData.buildError("学院不存在："+map.get("collegeName"));
        }
        for (Map<String, String> map : teacherList) {
            try {
                teacherDao.add(map.get("no"),map.get("name"),map.get("pwd"),map.get("tel"),map.get("collegeName"));
            }catch (Exception e){
                return ResponseData.buildError("导入教师失败,请联系服务器管理员");
            }
        }
        return ResponseData.buildSuccess("导入教师成功");
    }
}
