package com.mars.controller;

import com.mars.service.MapperService;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class MarsController {

    @Autowired
    MapperService service;

    /**
     * 管理员权限
     */
    @GetMapping("/findAllAdmins")
    public ResponseData findAllAdmins() {
        return service.findAllAdmins();
    }

    /**
     * 删除管理员
     */
    @PostMapping("/deleteAdmin")
    public ResponseData deleteAdmin(@RequestBody Map<String,String> map){
        return service.deleteAdmin(map.get("id"));
    }

    /**
     * 修改管理员
     */
    @PostMapping("/updateAdmin")
    public ResponseData updateAdmin(@RequestBody Map<String,String> map){
        return service.updateAdmin(map.get("newId"),map.get("pwd"),map.get("role"),map.get("oldId"));
    }

    /**
     * 新增管理员
     */
    @PostMapping("/addAdmin")
    public ResponseData addAdmin(@RequestBody Map<String,String> map){
        return service.addAdmin(map.get("name"),map.get("pwd"),map.get("role"));
    }

}
