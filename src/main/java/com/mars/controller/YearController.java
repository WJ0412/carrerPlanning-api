package com.mars.controller;

import com.mars.service.YearService;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangzhongwen
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class YearController {

    @Autowired
    YearService service;

    @GetMapping("/findAllYears")
    public ResponseData findAllYears() {
        return service.findAll();
    }

}
