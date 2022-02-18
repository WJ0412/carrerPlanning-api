package com.mars.controller.student;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mars.model.Business;
import com.mars.service.student.BusinessService;
import com.mars.service.student.OptionService;
import com.mars.utils.JWTUtil;
import com.mars.vo.OptionVo;
import com.mars.vo.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "BusinessController")
@RestController
@CrossOrigin
@RequestMapping("/student")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @Autowired
    private OptionService optionService;

    @GetMapping("/businessInfo")
    public ResponseData getStudentBaseInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        String id = tokenInfo.getClaim("username").asString();
        Business business = businessService.getBusinessInfo(id);
        if (business == null) {
            return ResponseData.buildError("未填写该数据");
        }
        return ResponseData.buildSuccess(business);
    }


    /**
     * 保存
     *
     * @param business
     * @param request
     * @return
     */
    @PostMapping("/addBusinessInfo")
    public ResponseData addBaseInfo(@RequestBody Business business, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        business.setStudentId(tokenInfo.getClaim("username").asString());
        business.setStatus("未提交");
        int i;
        if (businessService.getBusinessInfo(business.getStudentId()) == null) {
            i = businessService.addBusinessInfo(business);
        } else {
            i = businessService.updateBusinessInfo(business);
        }
        if (i == 1) {
            return ResponseData.buildSuccess(business);
        }
        return ResponseData.buildError("保存失败");
    }

    /**
     * 提交
     *
     * @param business
     * @param request
     * @return
     */
    @PostMapping("/saveBusinessInfo")
    public ResponseData saveBaseInfo(@RequestBody Business business, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        business.setStudentId(tokenInfo.getClaim("username").asString());
        business.setStatus("待审核");
        if (businessService.addBusinessInfo(business) == 1) {
            return ResponseData.buildSuccess(business);
        }
        return ResponseData.buildError("提交失败");
    }

    @GetMapping("/options")
    public ResponseData getOptions(String bh) {
        List<OptionVo> option = optionService.getOption(bh);
        if (option.isEmpty()) {
            return ResponseData.buildError("数据不存在");
        }
        return ResponseData.buildSuccess(option);

    }


}
