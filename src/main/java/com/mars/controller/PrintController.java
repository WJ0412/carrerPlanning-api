package com.mars.controller;

import com.mars.service.PrintService;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class PrintController {

    @Autowired
    PrintService service;

    @PostMapping("/print")
    public ResponseData printInfo(HttpServletRequest request, @RequestBody Map<String, String> map){

        return service.printInfo(map.get("value"),map.get("term"),map.get("id"),request);
    }



}
