package com.mars.controller.student;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mars.model.Employment;
import com.mars.service.student.EmploymentService;
import com.mars.utils.JWTUtil;
import com.mars.vo.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "EmploymentController")
@RestController
@CrossOrigin
@RequestMapping("/student")
public class EmploymentController {

    @Autowired
    private EmploymentService jobInfoService;

    @GetMapping("/jobInfo")
    public ResponseData getStudentJobInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        String id = tokenInfo.getClaim("username").asString();

        Employment employment = jobInfoService.getEmployment(id);
        if (employment == null) {
            return ResponseData.buildError("未填写");
        }
        return ResponseData.buildSuccess(employment);
    }

    /**
     * 保存
     *
     * @param employment
     * @param request
     * @return
     */
    @PostMapping("/addJobInfo")
    public ResponseData addJobInfo(@RequestBody Employment employment, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        employment.setStudentId(tokenInfo.getClaim("username").asString());
        employment.setStatus("未提交");
        int result = jobInfoService.addJobInfo(employment);
        if (result == 0){
            return ResponseData.buildError("保存失败");
        }
        return ResponseData.buildSuccess(employment);
    }

    /**
     * 提交
     *
     * @param employment
     * @param request
     * @return
     */
    @PostMapping("/saveJobInfo")
    public ResponseData saveJobInfo(@RequestBody Employment employment, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        employment.setStudentId(tokenInfo.getClaim("username").asString());
        employment.setStatus("待审核");
        int result = jobInfoService.addJobInfo(employment);
        if (result == 0){
            return ResponseData.buildError("提交失败");
        }
        return ResponseData.buildSuccess(employment);
    }

}
