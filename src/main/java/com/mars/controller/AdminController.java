package com.mars.controller;


import com.mars.service.AdminService;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/deleteAllData")
    public ResponseData deleteAllData() {
        return adminService.deleteAllData();
    }

    @PostMapping("/closeOrOpenSystem")
    public ResponseData closeOrOpenSystem(@RequestBody Map<String, String> map) {
        return adminService.closeOrOpenSystem(map.get("status"));
    }
}
