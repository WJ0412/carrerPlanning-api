package com.mars.controller;

import com.mars.service.StudentService;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping("/findAllStudents")
    public ResponseData findAllStudents() {
        return service.findAll();
    }

    @PostMapping("/findStudentsLike")
    public ResponseData findStudentsLike(@RequestBody Map<String, String> map) {
        return service.findLike(map.get("nameOrId"), map.get("className"));
    }

    @PostMapping("/addStudent")
    public ResponseData addStudent(@RequestBody Map<String, String> map) {
        return service.add(map.get("id"), map.get("name"), map.get("pwd"), map.get("className"));
    }

    @PostMapping("/updateStudent")
    public ResponseData updateStudent(@RequestBody Map<String, String> map) {
        return service.update(map.get("newId"), map.get("name"), map.get("pwd"), map.get("className"), map.get("oldId"));
    }

    @PostMapping("/deleteStudent")
    public ResponseData deleteStudent(@RequestBody Map<String, String> map) {
        return service.delete(map.get("id"));
    }

}
