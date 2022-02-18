package com.mars.service.student;

import com.mars.mapper.BusinessMapper;
import com.mars.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
//    BusinessDao businessDao;

    public Business getBusinessInfo(String id) {
        return businessMapper.selectById(id);
    }

    public int addBusinessInfo(Business business) {
        return businessMapper.insert(business);
    }

    public int updateBusinessInfo(Business business) {
        return businessMapper.updateById(business);
    }
}
