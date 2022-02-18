package com.mars.service;

import com.mars.dao.ClassDao;
import com.mars.dao.TeacherDao;
import com.mars.entity.Class;
import com.mars.entity.Teacher;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author huangzhongwen
 */
@Service
public class ClassService {

    @Autowired
    ClassDao dao;
    @Autowired
    TeacherDao teacherDao;

    /**
     * 查询所有班级
     */
    public ResponseData findAll() {
        List<Map<String, String>> classes = dao.findAllClasses();
        if (classes.isEmpty())
            return ResponseData.buildError("数据库中暂无班级数据");
        return ResponseData.buildSuccess(classes);
    }

    /**
     * 模糊查询
     */
    public ResponseData findLike(String collegeName, String majorName, String name) {
        System.out.println(collegeName);
        System.out.println(majorName.equals(""));
        System.out.println(name.equals(""));
        List<Map<String, String>> classes = null;
        if (!collegeName.trim().equals("")&&majorName.trim().equals("")&&name.trim().equals(""))
            classes = dao.findLikeByCollegeName(collegeName);
        if (collegeName.trim().equals("")&&majorName.trim().equals("")&&name.trim().equals(""))
            classes = dao.findAllClasses();
        if (majorName.trim().equals("")&&collegeName.trim().equals("")&&!name.trim().equals(""))
            classes = dao.findLikeByName(name);
        if (!majorName.trim().equals("")&&name.trim().equals("")&&collegeName.trim().equals(""))
            classes = dao.findLike(majorName);
        if (!majorName.trim().equals("")&&!name.trim().equals(""))
            classes = dao.findLike(majorName,name);
        if (classes.isEmpty())
            return ResponseData.buildError("无符合条件的班级");
        return ResponseData.buildSuccess(classes);
    }

    /**
     * 新增班级
     */
    public ResponseData add(String name, String teacherNo, String majorName, String yearName) {
        boolean flag = false;
        List<Class> classes = dao.findAll();
        for (Class classEntity : classes) {
            if (classEntity.getName().equals(name))
                return ResponseData.buildError("班级名称不可重复");
        }
        List<Teacher> teachers = teacherDao.findAll();
        for (Teacher teacher : teachers) {
            if (teacher.getNo()==null){
                continue;
            } else if(teacher.getNo().equals(teacherNo)){
                flag = true;
            }
        }
        int count = 0;
        if (name.trim().equals(""))
            return ResponseData.buildError("班级名称不可为空");
        else if (teacherNo.trim().equals(""))
            return ResponseData.buildError("教师工号不可为空");
        else if (majorName.trim().equals(""))
            return ResponseData.buildError("所属专业不可为空");
        else if (yearName.trim().equals(""))
            return ResponseData.buildError("入学年份不可为空");
        if (!flag)
            return ResponseData.buildError("该系统中并未存在该教师,请先添加该教师至系统中");
        try {
            count = dao.add(name,teacherNo,majorName,yearName);
        }catch (Exception e){
            return ResponseData.buildError("新增班级失败,请检查服务器配置.");
        }
        return ResponseData.buildSuccess(count);
    }

    /**
     * 修改班级
     */
    public ResponseData update(String newName, String teacherNo, String majorName, String yearName, String oldName) {
        boolean flag = false;
        List<Class> classes = dao.findAll();
        for (Class classEntity : classes) {
            if (classEntity.getName().equals(newName)&&!newName.equals(oldName))
                return ResponseData.buildError("班级名称不可重复");
        }
        List<Teacher> teachers = teacherDao.findAll();
        for (Teacher teacher : teachers) {
            if (teacher.getNo()==null){
                continue;
            } else if(teacher.getNo().equals(teacherNo)){
                flag = true;
            }
        }
        int count = 0;
        if (newName.trim().equals(""))
            return ResponseData.buildError("班级名称不可为空");
        else if (teacherNo.trim().equals(""))
            return ResponseData.buildError("教师工号不可为空");
        else if (majorName.trim().equals(""))
            return ResponseData.buildError("所属专业不可为空");
        else if (yearName.trim().equals(""))
            return ResponseData.buildError("入学年份不可为空");
        if (!flag)
            return ResponseData.buildError("该系统中并未存在该教师,请先添加该教师至系统中");
        try {
            count = dao.update(newName,teacherNo,majorName,yearName,oldName);
        }catch (Exception e){
            return ResponseData.buildError("新增班级失败,请检查服务器配置.");
        }
        return ResponseData.buildSuccess(count);
    }

    /**
     * 删除班级
     */
    public ResponseData delete(String name) {
        int count = 0;
        try {
            count = dao.delete(name);     // 捕捉外键约束删除异常
        }catch (Exception e){
            return ResponseData.buildError("删除教师失败,请删除外键约束");
        }
        if (count == 0)
            return ResponseData.buildError("删除教师失败,请检查服务器配置");
        return ResponseData.buildSuccess(count);
    }

    /**
     * 查询指定专业下的班级
     */
    public ResponseData findByMajor(String majorName) {
        List<Map<String, String>> classNames = dao.findByMajor(majorName);
        if (classNames.isEmpty())
            return ResponseData.buildError("当前专业下暂无班级信息");
        return ResponseData.buildSuccess(classNames);
    }

    public ResponseData findClassByTeacherNo(String teacherNo){
        List<Class> classes = dao.findClassByTeacherNo(teacherNo);
        if (classes.isEmpty())
            return ResponseData.buildError("当前教师尚未管理班级");
        return ResponseData.buildSuccess(classes);
    }

}
