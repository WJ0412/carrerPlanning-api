package com.mars.service.student;

import com.mars.dao.FamilyDao;
import com.mars.entity.Family;
import com.mars.entity.StuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    @Autowired
    FamilyDao familyDao;

    /**
     * 通过学生Id查家长
     * @param id
     * @return
     */
    public List<Family> getFamilyInfo(String id) {

        return familyDao.findFamiliesByStudentid(id);
    }

    public boolean addFamilyInfo(Family family) {

        return familyDao.save(family)!=null;
    }

    public void removeFamily(String sid){
        familyDao.removeByStuId(sid);
    }
}
