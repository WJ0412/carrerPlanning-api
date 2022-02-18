package com.mars.service.student;

import com.mars.mapper.EmploymentMapper;
import com.mars.model.Employment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmploymentService {
    @Autowired
    private EmploymentMapper employmentMapper;

    public Employment getEmployment(String id) {
        return employmentMapper.selectById(id);
    }

    public int addJobInfo(Employment employment) {
        int result;
        if (employmentMapper.selectById(employment.getStudentId()) == null) {
            result = employmentMapper.insert(employment);
        }else {
            result = employmentMapper.updateById(employment);
        }
        return result;
    }
}
