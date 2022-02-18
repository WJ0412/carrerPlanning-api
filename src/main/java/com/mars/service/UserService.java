package com.mars.service;

import com.mars.common.enumeration.UserTypeEnum;
import com.mars.dto.UserDTO;
import com.mars.mapper.AdminMapper;
import com.mars.mapper.StudentMapper;
import com.mars.mapper.TeacherMapper;
import com.mars.model.Admin;
import com.mars.model.Student;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private AdminMapper adminMapper;

    public Map<String, String> login(UserDTO user) {
        String type = user.getType();
        Map<String, String> map = new HashMap<>();
        if (type.equals(UserTypeEnum.ADMIN.getValue())) {
            map = adminMapper.getAdminByName(user.getName(),user.getPassword());
        } else if (type.equals(UserTypeEnum.TEACHER.getValue())) {
            map = teacherMapper.getTeacherByName(user.getName(),user.getPassword());
        } else if (type.equals(UserTypeEnum.STUDENT.getValue())) {
            map = studentMapper.getStudentByName(user.getName(), user.getPassword());
        }
        return map;
    }


    public ResponseData findSystemStatus() {
        String systemStatus = adminMapper.findSystemStatus();
        return ResponseData.buildSuccess(systemStatus);
    }

    public ResponseData getRoleForAdmin(String id) {
        Admin admin = adminMapper.selectById(id);
        String role = admin.getRole();
        if (role.trim().equals("root")) {
            return ResponseData.buildSuccess("超级管理员");
        } else {
            return ResponseData.buildSuccess("普通管理员");
        }
    }

    public Student findStudentById(String id) {
        return studentMapper.selectById(id);
    }

    public ResponseData editTeacherPwd(String id, String pwd, String password) {
        Map<String, String> map = teacherMapper.getTeacherByName(id, pwd);
        if (map == null) {
            return ResponseData.buildError("旧密码错误");
        }
        Integer row = teacherMapper.updatePwdById(id, password);
        if (row == 0) {
            return ResponseData.buildError("修改失败，请重试");
        }
        return ResponseData.buildSuccess("修改成功");
    }

    public ResponseData editStudentPwd(String id, String pwd, String pass) {
        Map<String, String> map = studentMapper.getStudentByName(id, pwd);
        if (map == null) {
            return ResponseData.buildError("旧密码错误");
        }
        Integer row = studentMapper.updatePwdById(id, pass);
        if (row == 0) {
            return ResponseData.buildError("修改失败，请重试");
        }
        return ResponseData.buildSuccess("修改成功");
    }

}
