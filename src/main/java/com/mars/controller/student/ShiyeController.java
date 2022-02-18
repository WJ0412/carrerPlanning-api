package com.mars.controller.student;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mars.entity.Shiye1;
import com.mars.entity.Shiye2;
import com.mars.service.student.ShiyeService;
import com.mars.service.student.OptionService;
import com.mars.utils.JWTUtil;
import com.mars.vo.OptionVo;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class ShiyeController {
    @Autowired
    ShiyeService shiyeService;

    @Autowired
    OptionService optionService;

    @GetMapping("/shiyeInfo1")
    public ResponseData getStudentBaseInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        String id = tokenInfo.getClaim("username").asString();
        Shiye1 shiye = shiyeService.getShiye1Info(id);
        System.out.println(shiye);
        if (shiye == null) {
            return ResponseData.buildError("未填写该数据");
        }
        return ResponseData.buildSuccess(shiye);
    }


    /**
     * 保存
     *
     * @param request
     * @return
     */
    @PostMapping("/addShiyeInfo1")
    public ResponseData addBaseInfo(@RequestBody Shiye1 shiye1, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        shiye1.setStudentid(tokenInfo.getClaim("username").asString());
        shiye1.setStatus("未提交");
        if (shiyeService.addShiye1Info(shiye1)) {
            return ResponseData.buildSuccess(shiye1);
        }
        return ResponseData.buildError("保存失败");
    }

    /**
     * 提交
     *
     * @param request
     * @return
     */
    @PostMapping("/saveShiyeInfo1")
    public ResponseData saveBaseInfo(@RequestBody Shiye1 shiye1, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        shiye1.setStudentid(tokenInfo.getClaim("username").asString());
        shiye1.setStatus("待审核");
        if (shiyeService.addShiye1Info(shiye1)) {
            return ResponseData.buildSuccess(shiye1);
        }
        return ResponseData.buildError("提交失败");
    }




    @GetMapping("/shiyeInfo2")
    public ResponseData getShiye2Info(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        String id = tokenInfo.getClaim("username").asString();
        Shiye2 shiye2 = shiyeService.getShiye2Info(id);
        if (shiye2 == null) {
            return ResponseData.buildError("未填写该数据");
        }
        return ResponseData.buildSuccess(shiye2);
    }


    /**
     * 保存
     *
     * @param request
     * @return
     */
    @PostMapping("/addShiyeInfo2")
    public ResponseData addShiyeInfo2(@RequestBody Shiye2 shiye2, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        shiye2.setStudentid(tokenInfo.getClaim("username").asString());
        shiye2.setStatus("未提交");
        if (shiyeService.addShiye2Info(shiye2)) {
            return ResponseData.buildSuccess(shiye2);
        }
        return ResponseData.buildError("保存失败");
    }

    /**
     * 提交
     *
     * @param request
     * @return
     */
    @PostMapping("/saveShiyeInfo2")
    public ResponseData saveShiyeInfo2(@RequestBody Shiye2 shiye2, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        shiye2.setStudentid(tokenInfo.getClaim("username").asString());
        shiye2.setStatus("待审核");
        if (shiyeService.addShiye2Info(shiye2)) {
            return ResponseData.buildSuccess(shiye2);
        }
        return ResponseData.buildError("提交失败");
    }

}
