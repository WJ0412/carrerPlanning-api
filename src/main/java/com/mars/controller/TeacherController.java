package com.mars.controller;

import com.mars.entity.Teacher;
import com.mars.service.TeacherService;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author huangzhongwen
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class TeacherController {

    @Autowired
    TeacherService service;

    /**
     * 查询所有教师
     */
    @GetMapping("/findAllTeachers")
    public ResponseData findAllTeachers() {
        return service.findAll();
    }

    /**
     * 模糊查询
     */
    @PostMapping("/findTeachersLike")
    public ResponseData findTeachersLike(@RequestBody Teacher teacher) {
        return service.findLike(teacher.getName(), teacher.getCollegeName());
    }

    /**
     * 新增教师
     */
    @PostMapping("/addTeacher")
    public ResponseData addTeacher(@RequestBody Teacher teacher) {
        return service.add(teacher.getNo(), teacher.getName(), teacher.getPwd(), teacher.getTel(), teacher.getCollegeName());
    }

    /**
     * 修改教师
     */
    @PostMapping("/updateTeacher")
    public ResponseData updateTeacher(@RequestBody Teacher teacher) {
        return service.update(teacher.getNo(),teacher.getName(),teacher.getPwd(),teacher.getTel(),teacher.getCollegeName());
    }

    /**
     * 删除教师
     */
    @PostMapping("/deleteTeacher")
    public ResponseData deleteTeacher(@RequestBody Teacher teacher){
        return service.delete(teacher.getNo());
    }



}
