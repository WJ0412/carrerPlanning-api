package com.mars.controller.student;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.mars.service.student.XueyeService;
import com.mars.utils.JWTUtil;
import com.mars.vo.ResponseData;
import com.mars.vo.XueYeVo;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.LinkedList;

/**
 * 基本信息
 */

@RestController
@CrossOrigin
@RequestMapping("/student")
public class XueyeController {

    @Autowired
    XueyeService xueyeService;





    @GetMapping("/xueye")
    public ResponseData getStudentXueye(HttpServletRequest request,String xueqi){
        if(xueqi==null){
            return  ResponseData.buildError("查询失败");
        }
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        String id = tokenInfo.getClaim("username").asString();
        System.out.println(xueqi);
        XueYeVo xueYeVo= xueyeService.getXueye(id,xueqi);
        if(xueYeVo==null){
          return  ResponseData.buildError("记录不存在");
        }
        return ResponseData.buildSuccess(xueYeVo);
    }
    /**
     * 保存
     * @param request
     * @return
     */
    @PostMapping("/addXueye")
    public ResponseData addXueye(@RequestBody XueYeVo xueYeVo, HttpServletRequest request, String xueqi){
        if(xueqi==null){
            return ResponseData.buildError("请选择学期");
        }
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        xueYeVo.setStudentid(tokenInfo.getClaim("username").asString());
        xueYeVo.setStatus("未提交");
        boolean b=xueyeService.addXueye(xueYeVo,xueqi);

        return ResponseData.buildSuccess(xueYeVo);
    }
    /**
     * 提交
     * @param request
     * @return
     */
    @PostMapping("/saveXueye")
    public ResponseData saveXueye(@RequestBody XueYeVo xueYeVo,HttpServletRequest request,String xueqi){
        if(xueqi==null){
            return ResponseData.buildError("请选择学期");
        }
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        xueYeVo.setStudentid(tokenInfo.getClaim("username").asString());
        xueYeVo.setStatus("待审核");
        boolean b=xueyeService.addXueye(xueYeVo,xueqi);
        return ResponseData.buildSuccess(xueYeVo);
    }
    @GetMapping("/xuezhi")
    public  ResponseData getXuezhi(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        //token中的username为学生id
        String sid = tokenInfo.getClaim("username").asString();
        JSONObject jsonobj1=new JSONObject();
        jsonobj1.put("value","1");
        jsonobj1.put("label","第一学期");
        JSONObject jsonobj2=new JSONObject();
        jsonobj2.put("value","2");
        jsonobj2.put("label","第二学期");
        JSONObject jsonobj3=new JSONObject();
        jsonobj3.put("value","3");
        jsonobj3.put("label","第三学期");
        JSONObject jsonobj4=new JSONObject();
        jsonobj4.put("value","4");
        jsonobj4.put("label","第四学期");
        JSONObject jsonobj5=new JSONObject();
        jsonobj5.put("value","5");
        jsonobj5.put("label","第五学期");
        JSONObject jsonobj6=new JSONObject();
        jsonobj6.put("value","6");
        jsonobj6.put("label","第六学期");
        JSONObject jsonobj7=new JSONObject();
        jsonobj7.put("value","7");
        jsonobj7.put("label","第七学期");
        JSONObject jsonobj8=new JSONObject();
        jsonobj8.put("value","8");
        jsonobj8.put("label","第八学期");
        JSONObject jsonobj9=new JSONObject();
        jsonobj9.put("value","9");
        jsonobj9.put("label","第九学期");
        JSONObject jsonobj10=new JSONObject();
        jsonobj10.put("value","10");
        jsonobj10.put("label","第十学期");
        LinkedList<JSONObject> list=new LinkedList<>();
        list.add(jsonobj1);
        list.add(jsonobj2);
        list.add(jsonobj3);
        list.add(jsonobj4);
        list.add(jsonobj5);
        list.add(jsonobj6);
        list.add(jsonobj7);
        list.add(jsonobj8);
        list.add(jsonobj9);
        list.add(jsonobj10);
        Integer xuezhi=xueyeService.getXuezhi(sid);
        JSONArray array=new JSONArray(xuezhi);
        for (Integer i = 0; i < xuezhi*2; i++) {
            array.add(i,list.get(i));
        }
        if(array.isEmpty()){
            return ResponseData.buildError("请添加学制信息");
        }
        return ResponseData.buildSuccess(array);

    }







}
