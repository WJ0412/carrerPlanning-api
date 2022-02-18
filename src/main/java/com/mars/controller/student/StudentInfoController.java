package com.mars.controller.student;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mars.model.StudentInfo;
import com.mars.service.student.BaseInfoService;
import com.mars.service.student.OptionService;
import com.mars.utils.JWTUtil;
import com.mars.vo.OptionVo;
import com.mars.vo.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 基本信息
 */

@Api(value = "StudentInfoController")
@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentInfoController {

    @Autowired
    private BaseInfoService baseInfoService;

    @Autowired
    private OptionService optionService;

    @GetMapping("/baseInfo")
    public ResponseData getStudentBaseInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        String id = tokenInfo.getClaim("username").asString();
        StudentInfo studentInfo = baseInfoService.getStuInfo(id);
        if (studentInfo == null) {
            return ResponseData.buildError("该用户不存在");
        }
        return ResponseData.buildSuccess(studentInfo);
    }

    @PostMapping("/addBaseInfo")
    public ResponseData addBaseInfo(@RequestBody StudentInfo studentInfo, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        studentInfo.setId(tokenInfo.getClaim("username").asString());
        studentInfo.setStatus("未提交");
        int result = baseInfoService.updateBaseInfo(studentInfo);
        if (result == 0){
            ResponseData.buildError("提交失败");
        }
        return ResponseData.buildSuccess(studentInfo);
    }

    @PostMapping("/saveBaseInfo")
    public ResponseData saveBaseInfo(@RequestBody StudentInfo studentInfo, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        studentInfo.setId(tokenInfo.getClaim("username").asString());
        studentInfo.setStatus("待审核");
        int result = baseInfoService.updateBaseInfo(studentInfo);
        if (result == 0){
            ResponseData.buildError("保存失败");
        }
        return ResponseData.buildSuccess(studentInfo);
    }

    @GetMapping("/getOptions")
    public ResponseData getOptions(String bh_name) {
        List<OptionVo> option = optionService.getOption(bh_name);
        return ResponseData.buildSuccess(option);
    }
}
