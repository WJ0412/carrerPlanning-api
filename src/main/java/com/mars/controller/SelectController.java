package com.mars.controller;

import com.mars.service.ClassService;
import com.mars.service.StudentService;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("teacher")
public class SelectController {

    @Autowired
    StudentService studentService;
    @Autowired
    ClassService classService;


    @PostMapping("/findAllStudentsByTeacher")
    public ResponseData findAllStudentsByTeacher(@RequestBody Map<String, String> map) {
        System.out.println(map.toString());
        return studentService.findAllStudentsByTeacher(map.get("teacherNo"));
    }

    @PostMapping("/findClassByTeacher")
    public ResponseData findClassByTeacher(@RequestBody Map<String, String> map) {
        return classService.findClassByTeacherNo(map.get("teacherNo"));
    }

    @PostMapping("/findStudentsLike")
    public ResponseData findStudentsLike(@RequestBody Map<String,String> map){
        return studentService.findStudentsLike(map.get("className"),map.get("status"),map.get("name"),map.get("teacherNo"));
    }


}
