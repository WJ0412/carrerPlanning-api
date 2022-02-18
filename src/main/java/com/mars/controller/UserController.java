package com.mars.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mars.dto.UserDTO;
import com.mars.model.Student;
import com.mars.service.UserService;
import com.mars.utils.JWTUtil;
import com.mars.vo.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(value = "UserController")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseData login(@RequestBody UserDTO userData) {

        System.out.println(userData.toString());
        Map<String, String> map = new HashMap<>(); //用来存放payload信息
        Map<String, String> user = userService.login(userData);

        if (user == null) {
            return ResponseData.buildError("账号或密码错误");
        }
        map.put("username", user.get("id"));
        map.put("role", userData.getType());
        map.put("uname", user.get("name"));  //账号
        // 生成token令牌（可解密的加密串）
        String token = JWTUtil.generateToken(map);

        //DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);  //解密方法
        //tokenInfo.getClaim("username").asString();  //取信息

        map.put("token", token);
        return ResponseData.buildSuccess(map);
    }

    @GetMapping("/findSystemStatus")
    public ResponseData findSystemStatus() {
        return userService.findSystemStatus();
    }


    @PostMapping("/editPwd")
    public ResponseData editPwd(String pwd, String pass, String checkPass, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        String id = tokenInfo.getClaim("username").asString();
        String role = tokenInfo.getClaim("role").asString();

        if (!pass.equals(checkPass)) {
            return ResponseData.buildError("两次输入的密码不一致");
        }
        if ("teacher".equals(role)) {
            return userService.editTeacherPwd(id, pwd.trim(), pass.trim());
        }
        return userService.editStudentPwd(id, pwd.trim(), pass.trim());
    }

    @GetMapping("/getUser")
    public ResponseData getUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        String id = tokenInfo.getClaim("username").asString();
        Student student = userService.findStudentById(id);
        if (student == null) {
            ResponseData.buildError("数据不存在");
        }
        return ResponseData.buildSuccess(student);
    }

    @PostMapping("/getRoleForAdmin")
    public ResponseData getRoleForAdmin(@RequestBody Map<String, String> map) {
        return userService.getRoleForAdmin(map.get("id"));
    }

}
