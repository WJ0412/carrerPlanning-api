package com.mars.service;


import com.mars.dao.TeacherDao;
import com.mars.entity.Major;
import com.mars.entity.Teacher;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongwen
 */
@Service
public class TeacherService {

    @Autowired
    TeacherDao dao;

    /**
     * 查询所有教师
     */
    public ResponseData findAll(){
        List<Teacher> teachers = dao.findAll();
        if (teachers.isEmpty())
            return ResponseData.buildError("数据库中暂无专业数据");
        return ResponseData.buildSuccess(teachers);
    }

    /**
     * 模糊查询
     */
    public ResponseData findLike(String name, String collegeName) {
        List<Teacher> teachers = null;
        if (name.trim().equals("") && collegeName.trim().equals("")) {
            teachers = dao.findAll();
        } else if (name.trim().equals("")) {
            teachers = dao.findTeachersByCollegeName(collegeName);
        } else if (collegeName.trim().equals("")) {
            teachers = dao.findLike(name);
        } else {
            teachers = dao.findLike(name, collegeName);
        }
        if (teachers.isEmpty())
            return ResponseData.buildError("无符合条件的教师");
        return ResponseData.buildSuccess(teachers);
    }

    /**
     * 新增教师
     */
    public ResponseData add(String no, String name, String pwd, String tel, String collegeName) {
        if (no.trim().equals(""))
            return ResponseData.buildError("教师工号不可为空");
        else if (name.trim().equals(""))
            return ResponseData.buildError("教师名称不可为空");
        else if (pwd.trim().equals(""))
            return ResponseData.buildError("教师密码不可为空");
        else if (tel.trim().equals(""))
            return ResponseData.buildError("教师手机号码不可为空");
        else if (collegeName.trim().equals(""))
            return ResponseData.buildError("所属学院不可为空");
        List<Teacher> teachers = dao.findAll();
        for (Teacher teacher : teachers) {
            if (teacher.getNo().equals(no))
                return ResponseData.buildError("教师工号不可重复");
        }
        int count = dao.add(no,name,pwd,tel,collegeName);
        if (count==0)
            return ResponseData.buildError("新增教师失败,请检查服务器配置.");
        return ResponseData.buildSuccess(count);
    }

    /**
     * 修改教师
     */
    public ResponseData update(String no, String name, String pwd, String tel, String collegeName) {
        if (no.trim().equals(""))
            return ResponseData.buildError("教师工号不可为空");
        else if (name.trim().equals(""))
            return ResponseData.buildError("教师名称不可为空");
        else if (pwd.trim().equals(""))
            return ResponseData.buildError("教师密码不可为空");
        else if (tel.trim().equals(""))
            return ResponseData.buildError("教师手机号码不可为空");
        else if (collegeName.trim().equals(""))
            return ResponseData.buildError("所属学院不可为空");
        int count = dao.update(no,name,pwd,tel,collegeName);
        if (count==0)
            return ResponseData.buildError("新增教师失败,请检查服务器配置.");
        return ResponseData.buildSuccess(count);
    }

    /**
     * 删除教师
     */
    public ResponseData delete(String no) {
        int count = 0;
        try {
            count = dao.delete(no);     // 捕捉外键约束删除异常
        }catch (Exception e){
            return ResponseData.buildError("删除教师失败，尚未解绑该教师管理的班级!");
        }
        if (count == 0)
            return ResponseData.buildError("删除教师失败，请检查服务器配置...");
        return ResponseData.buildSuccess(count);
    }

}
