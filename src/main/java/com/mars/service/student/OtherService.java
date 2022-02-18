package com.mars.service.student;

import com.mars.dao.FamilyDao;
import com.mars.dao.OtherDao;
import com.mars.entity.Family;
import com.mars.entity.Other;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherService {

    @Autowired
    OtherDao otherDao;




    public List<Other> getOtherInfo(String id) {
        return otherDao.findOthersByStudentid(id);
    }



    public boolean addOtherInfo(Other other) {
        return otherDao.save(other)!=null;
    }

    public void removeOther(String username) {
        otherDao.removeOtherBySid(username);
    }
}
