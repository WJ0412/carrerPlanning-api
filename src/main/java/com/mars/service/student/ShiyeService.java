package com.mars.service.student;

import com.mars.dao.Shiye1Dao;
import com.mars.dao.Shiye2Dao;
import com.mars.entity.Shiye1;
import com.mars.entity.Shiye2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiyeService {

    @Autowired
    Shiye1Dao shiye1Dao;

    @Autowired
    Shiye2Dao shiye2Dao;

    public Shiye1 getShiye1Info(String id) {
        return shiye1Dao.getShiyeByid(id);
    }

    public boolean addShiye1Info(Shiye1 shiye1) {
        return shiye1Dao.save(shiye1)!=null;
    }

    public Shiye2 getShiye2Info(String id) {
        return shiye2Dao.getShiyeByid(id);
    }

    public boolean addShiye2Info(Shiye2 shiye2) {
        return shiye2Dao.save(shiye2)!=null;
    }


}
