package com.mars.service;

import com.mars.dao.MapperDao;
import com.mars.dao.StuInfoDao;
import com.mars.dao.StudentDao;
import com.mars.entity.StuInfo;
import com.mars.entity.Student;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    StudentDao dao;
    @Autowired
    StuInfoDao infoDao;
    @Autowired
    MapperDao mapperDao;

    public ResponseData findAll() {
        List<Map<String, String>> allStudents = dao.findAllStudents();
        if (allStudents.isEmpty())
            return ResponseData.buildError("数据库中暂无学生数据");
        return ResponseData.buildSuccess(allStudents);
    }

    /**
     * 模糊查询
     */
    public ResponseData findLike(String nameOrId,String className){
        List<Map<String,String>> students = null;
        if (nameOrId.trim().equals("")&&className.trim().equals(""))
            students = dao.findAllStudents();
        if (nameOrId.trim().equals(""))
            students = dao.findLikeByClassName(className);
        if (className.trim().equals(""))
            students = dao.findLike(nameOrId);
        else
            students = dao.findLike(nameOrId, className);
        if (students.isEmpty())
            return ResponseData.buildError("无符合条件的学生");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData add(String id, String name, String pwd, String className) {
        int count = 0;
        List<Student> students = dao.findAll();
        for (Student student : students) {
            if (student.getId().equals(id))
                return ResponseData.buildError("该学生已存在");
        }
        if (id.trim().equals(""))
            return ResponseData.buildError("学生学号不可为空");
        else if (name.trim().equals(""))
            return ResponseData.buildError("学生姓名不可为空");
        else if (pwd.trim().equals(""))
            return ResponseData.buildError("学生密码不可为空");
        else if (className.trim().equals(""))
            return ResponseData.buildError("所属班级不可为空");
        try {
            count = dao.add(id,pwd,className);     // 先创建student表里面的id  避免出现stuinfo外键找不到主表里面的id
        }catch (Exception e){
            return ResponseData.buildError("外键约束,请联系服务器管理员进行修改");
        }
        try {
            count += infoDao.addIdAndName(id,name);
        }catch (Exception e){
            return ResponseData.buildError("学生账号尚未录入至系统");
        }
        if (count!=2)
            return ResponseData.buildError("新增学生失败，请检查服务器配置");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData update(String newId, String name, String pwd, String className,String oldId) {
        int count = 0;
        List<Student> students = dao.findAll();
        for (Student student : students) {
            if (student.getId().equals(newId)&&!newId.equals(oldId))
                return ResponseData.buildError("该学生已存在");
        }
        if (newId.trim().equals(""))
            return ResponseData.buildError("学生学号不可为空");
        else if (name.trim().equals(""))
            return ResponseData.buildError("学生姓名不可为空");
        else if (pwd.trim().equals(""))
            return ResponseData.buildError("学生密码不可为空");
        else if (className.trim().equals(""))
            return ResponseData.buildError("所属班级不可为空");
        try {
            count = dao.update(newId,pwd,className,oldId);
        }catch (Exception e){
            return ResponseData.buildError("外键约束,请联系服务器管理员进行修改");
        }
        try {
            count += infoDao.updateIdAndName(newId,name,oldId);
        }catch (Exception e){
            return ResponseData.buildError("学生账号尚未录入至系统");
        }
        if (count!=2)
            return ResponseData.buildError("新增学生失败，请检查服务器配置");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData delete(String id) {
        try {
            dao.delete(id);
        }catch (Exception e){
            return ResponseData.buildError("删除失败,请检查服务器配置");
        }
        // 判断是否级联删除
        List<StuInfo> students = infoDao.findAll();
        for (StuInfo student : students) {
            if (student.getId().equals(id)){
                try {
                    infoDao.delete(id);
                }catch (Exception e){
                    return ResponseData.buildError("删除失败,请检查服务器配置");
                }
            }
        }
        return ResponseData.buildSuccess("删除成功");
    }

    public ResponseData findAllStudentsByTeacher(String teacherNo) {
        List<Map<String,String>> students = dao.findAllStudentsByTeacher(teacherNo);
        if (students.isEmpty())
            return ResponseData.buildError("您还未管理任何学生哦");
        return ResponseData.buildSuccess(students);
    }


    public ResponseData findStudentsLike(String className, String status, String name,String teacherNo) {
        List<Map<String,String>> students = null;
        if (className.equals("")){
            if (status.equals("")){
                if (name.equals("")){
                    students = dao.findAllStudentsByTeacher(teacherNo);
                }else{
                    // 根据姓名查询
                    students = mapperDao.findInfoByName(name,teacherNo);
                }
            }else{
                if (name.equals("")){
                    // 根据状态码查询
                    students = mapperDao.findInfoByStatus(status,teacherNo);
                }else{
                    // 根据状态码和姓名查询
                    students = mapperDao.findInfoByStatusAndName(status,name,teacherNo);
                }
            }
        }else {
            if (status.equals("")){
                if (name.equals("")){
                    // 根据班级名称查询
                    students = mapperDao.findInfoByClassName(className,teacherNo);
                }else{
                    // 根据姓名和班级名称查询
                    students = mapperDao.findInfoByNameAndClassName(name,className,teacherNo);
                }
            }else{
                if (name.equals("")){
                    //根据状态码和班级查询
                    students = mapperDao.findInfoByStatusAndClassName(status,className,teacherNo);
                }else{
                    //根据名称 状态码  班级 查询
                    students = mapperDao.findInfoByNameStatusAndClassName(name,status,className,teacherNo);
                }
            }
        }

        if (students.isEmpty())
            return ResponseData.buildError("无符合条件的学生");
        return ResponseData.buildSuccess(students);
    }

}
