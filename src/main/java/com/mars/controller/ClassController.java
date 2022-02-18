package com.mars.controller;

import com.mars.entity.Class;
import com.mars.entity.Major;
import com.mars.service.ClassService;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class ClassController {

    @Autowired
    ClassService service;

    /**
     * 查询所有班级
     */
    @GetMapping("/findAllClasses")
    public ResponseData findAllClasses() {
        return service.findAll();
    }

    /**
     * 模糊查询
     */
    @PostMapping("/findClassesLike")
    public ResponseData findClassesLike(@RequestBody Map<String, String> map) {
        return service.findLike(map.get("collegeName"),map.get("majorName"), map.get("name"));
    }

    /**
     * 新增班级
     */
    @PostMapping("/addClass")
    public ResponseData addClass(@RequestBody Class classBean) {
        return service.add(classBean.getName(),classBean.getTeacherNo(),classBean.getMajorName(),classBean.getYearName());
    }

    /**
     * 修改班级
     */
    @PostMapping("/updateClass")
    public ResponseData updateClass(@RequestBody Map<String,String> map){
        return service.update(map.get("newName"),map.get("teacherNo"),map.get("majorName"),map.get("yearName"),map.get("oldName"));
    }

    /**
     * 删除班级
     */
    @PostMapping("/deleteClass")
    public ResponseData deleteClass(@RequestBody Class classBean){
        return service.delete(classBean.getName());
    }

    /**
     * 查询指定专业下的班级
     */
    @PostMapping("/findClassByMajorName")
    public ResponseData findClassByMajorName(@RequestBody Map<String,String> map){
        return service.findByMajor(map.get("majorName"));
    }

    @PostMapping("/findClassByTeacherNo")
    public ResponseData findClassByTeacherNo(@RequestBody Map<String,String> map){
        return service.findClassByTeacherNo(map.get("teacherNo"));
    }

}
