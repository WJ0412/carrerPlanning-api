package com.mars.service;

import com.mars.dao.YearDao;
import com.mars.entity.Year;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearService {

    @Autowired
    YearDao dao;

    public ResponseData findAll(){
        List<Year> years = dao.findAll();
        if (years.isEmpty())
            return ResponseData.buildError("数据库中暂无学年信息，请及时添加");
        return ResponseData.buildSuccess(years);
    }

}
