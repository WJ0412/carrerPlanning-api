package com.mars.service.student;

import com.mars.mapper.StudentInfoMapper;
import com.mars.model.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseInfoService {
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    public StudentInfo getStuInfo(String id) {
        return studentInfoMapper.selectById(id);
    }

    public int updateBaseInfo(StudentInfo studentInfo) {
        return studentInfoMapper.updateById(studentInfo);
    }
}
