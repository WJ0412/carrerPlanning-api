package com.mars.controller.student;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mars.model.Acknowledge;
import com.mars.service.student.AcknowledgeService;
import com.mars.utils.JWTUtil;
import com.mars.vo.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "AcknowledgeController")
@RestController
@CrossOrigin
@RequestMapping("/student")
public class AcknowledgeController {
    @Autowired
    private AcknowledgeService acknowledgeService;

    @GetMapping("/acknowledgeInfo")
    public ResponseData getStudentBaseInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        String id = tokenInfo.getClaim("username").asString();
        Acknowledge acknowledgeInfo = acknowledgeService.getAcknowledgeInfo(id);
        if (acknowledgeInfo == null) {
            return ResponseData.buildError("未填写该数据");
        }
        return ResponseData.buildSuccess(acknowledgeInfo);
    }


    /**
     * 保存
     *
     * @param acknowledge
     * @param request
     * @return
     */
    @PostMapping("/addAcknowledgeInfo")
    public ResponseData addBaseInfo(@RequestBody Acknowledge acknowledge, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        acknowledge.setStudentId(tokenInfo.getClaim("username").asString());
        acknowledge.setStatus("未提交");
        int result = acknowledgeService.addAcknowledgeInfo(acknowledge);
        if (result == 1) {
            return ResponseData.buildSuccess(acknowledge);
        }
        return ResponseData.buildError("保存失败");
    }

    /**
     * 提交
     *
     * @param acknowledge
     * @param request
     * @return
     */
    @PostMapping("/saveAcknowledgeInfo")
    public ResponseData saveBaseInfo(@RequestBody Acknowledge acknowledge, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        acknowledge.setStudentId(tokenInfo.getClaim("username").asString());
        acknowledge.setStatus("待审核");
        int result = acknowledgeService.addAcknowledgeInfo(acknowledge);
        if (result == 1) {
            return ResponseData.buildSuccess(acknowledge);
        }
        return ResponseData.buildError("提交失败");
    }


}
