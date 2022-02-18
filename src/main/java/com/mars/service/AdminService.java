package com.mars.service;

import com.mars.dao.AdminDao;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;

    public ResponseData deleteAllData() {
        int count = 0;
        try {
            count += adminDao.deleteStudents();  //删除学生账号表
            count += adminDao.deleteInfo();   //删除学生信息表
            count += adminDao.deleteClasses();  //删除班级表
            count += adminDao.deleteTeachers();  //删除教师表
        }catch (Exception e){
            return ResponseData.buildError("数据库外键约束,请联系服务器管理人员");
        }
        if (count==0)
            return ResponseData.buildError("数据库删除操作异常或欲相关表的记录已经清空！");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData closeOrOpenSystem(String status) {
        int count = adminDao.closeOrOpenSystem(status);
        if (count==0)
            return ResponseData.buildError("系统错误");
        return ResponseData.buildSuccess(count);
    }

}
