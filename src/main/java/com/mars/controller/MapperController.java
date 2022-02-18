package com.mars.controller;

import com.mars.service.MapperService;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("teacher")
public class MapperController {

    @Autowired
    MapperService service;

    @PostMapping("/findInfoById")
    public ResponseData findInfoById(@RequestBody Map<String, String> map) {
        return service.findInfoById(map.get("id"));
    }

    @PostMapping("/completeInfo")
    public ResponseData completeInfo(@RequestBody Map<String, String> map) {
        return service.completeInfo(map.get("id"));
    }

    @PostMapping("/failInfo")
    public ResponseData failInfo(@RequestBody Map<String, String> map) {
        return service.failInfo(map.get("id"), map.get("note"));
    }

    @PostMapping("/findStudentsByFamily")
    public ResponseData findStudentsByFamily(@RequestBody Map<String, String> map) {
        System.out.println(map.toString());
        return service.findStudentsByFamily(map.get("className"), map.get("status"), map.get("name"), map.get("teacherNo"));
    }

    @PostMapping("/findFamilyByTeacherNo")
    public ResponseData findFamilyByTeacherNo(@RequestBody Map<String, String> map) {
        return service.findFamilyByTeacherNo(map.get("teacherNo"));
    }

    @PostMapping("/findFamilyById")
    public ResponseData findFamilyById(@RequestBody Map<String, String> map) {
        return service.findFamilyById(map.get("id"));
    }

    @PostMapping("/completeFamily")
    public ResponseData completeFamily(@RequestBody Map<String, String> map) {
        return service.completeFamily(map.get("id"));
    }

    @PostMapping("/failFamily")
    public ResponseData failFamily(@RequestBody Map<String, String> map) {
        return service.failFamily(map.get("id"), map.get("note"));
    }

    /**
     * 其他亲属
     */

    @PostMapping("/findStudentsByOther")
    public ResponseData findStudentsByOther(@RequestBody Map<String, String> map) {
        return service.findStudentsByOther(map.get("className"), map.get("status"), map.get("name"), map.get("teacherNo"));
    }

    @PostMapping("/findOtherByTeacherNo")
    public ResponseData findOtherByTeacherNo(@RequestBody Map<String, String> map) {
        return service.findOtherByTeacherNo(map.get("teacherNo"));
    }

    @PostMapping("/findOtherById")
    public ResponseData findOtherById(@RequestBody Map<String, String> map) {
        return service.findOtherById(map.get("id"));
    }

    @PostMapping("/completeOther")
    public ResponseData completeOther(@RequestBody Map<String, String> map) {
        return service.completeOther(map.get("id"));
    }

    @PostMapping("/failOther")
    public ResponseData failOther(@RequestBody Map<String, String> map) {
        return service.failOther(map.get("id"), map.get("note"));
    }


    /**
     * 自我认知
     */
    @PostMapping("/findStudentsByRenZhi")
    public ResponseData findStudentsByRenZhi(@RequestBody Map<String, String> map) {
        return service.findStudentsByRenZhi(map.get("className"), map.get("status"), map.get("name"), map.get("teacherNo"));
    }

    @PostMapping("/findRenZhiByTeacherNo")
    public ResponseData findRenZhiByTeacherNo(@RequestBody Map<String, String> map) {
        return service.findRenZhiByTeacherNo(map.get("teacherNo"));
    }

    @PostMapping("/findRenZhiById")
    public ResponseData findRenZhiById(@RequestBody Map<String, String> map) {
        return service.findRenZhiById(map.get("id"));
    }

    @PostMapping("/completeRenZhi")
    public ResponseData completeRenZhi(@RequestBody Map<String, String> map) {
        return service.completeRenZhi(map.get("id"));
    }

    @PostMapping("/failRenZhi")
    public ResponseData failRenZhi(@RequestBody Map<String, String> map) {
        return service.failRenZhi(map.get("id"), map.get("note"));
    }


    /**
     * 就业规划
     */
    @PostMapping("/findStudentsByJob")
    public ResponseData findStudentsByJob(@RequestBody Map<String, String> map) {
        return service.findStudentsByJob(map.get("className"), map.get("status"), map.get("name"), map.get("teacherNo"));
    }

    @PostMapping("/findJobByTeacherNo")
    public ResponseData findJobByTeacherNo(@RequestBody Map<String, String> map) {
        return service.findJobByTeacherNo(map.get("teacherNo"));
    }

    @PostMapping("/findJobById")
    public ResponseData findJobById(@RequestBody Map<String, String> map) {
        return service.findJobById(map.get("id"));
    }

    @PostMapping("/completeJob")
    public ResponseData completeJob(@RequestBody Map<String, String> map) {
        return service.completeJob(map.get("id"));
    }

    @PostMapping("/failJob")
    public ResponseData failJob(@RequestBody Map<String, String> map) {
        return service.failJob(map.get("id"), map.get("note"));
    }


    /**
     * 创业规划
     */
    @PostMapping("/findStudentsByBusiness")
    public ResponseData findStudentsByBusiness(@RequestBody Map<String, String> map) {
        return service.findStudentsByBusiness(map.get("className"), map.get("status"), map.get("name"), map.get("teacherNo"));
    }

    @PostMapping("/findBusinessByTeacherNo")
    public ResponseData findBusinessByTeacherNo(@RequestBody Map<String, String> map) {
        return service.findBusinessByTeacherNo(map.get("teacherNo"));
    }

    @PostMapping("/findBusinessById")
    public ResponseData findBusinessById(@RequestBody Map<String, String> map) {
        return service.findBusinessById(map.get("id"));
    }

    @PostMapping("/completeBusiness")
    public ResponseData completeBusiness(@RequestBody Map<String, String> map) {
        return service.completeBusiness(map.get("id"));
    }

    @PostMapping("/failBusiness")
    public ResponseData failBusiness(@RequestBody Map<String, String> map) {
        return service.failBusiness(map.get("id"), map.get("note"));
    }


    /**
     * 学业规划
     */

    @PostMapping("/findNameAndIdByClassNameAndStatus")
    public ResponseData findNameAndIdByClassNameAndStatus(@RequestBody Map<String, String> map) {
        return service.findNameAndIdByClassNameAndStatus(map.get("className"),map.get("status"),map.get("xueqi"));
    }

    @PostMapping("/findSuccessById")
    public ResponseData findSuccessById(@RequestBody Map<String, String> map) {
        return service.findSuccessById(map.get("studentId"),map.get("xueqi"));
    }

    @PostMapping("/failSuccessById")
    public ResponseData failSuccessById(@RequestBody Map<String, String> map) {
        return service.failSuccessById(map.get("studentId"),map.get("xueqi"),map.get("note"));
    }


    @PostMapping("/completeSuccess")
    public ResponseData completeSuccess(@RequestBody Map<String, String> map) {
        return service.completeSuccess(map.get("studentId"),map.get("xueqi"),map.get("score"));
    }


    /**
     * 事业规划
     */

    @PostMapping("/findNameAndIdByClassNameAndStatus2")
    public ResponseData findNameAndIdByClassNameAndStatus2(@RequestBody Map<String, String> map) {
        return service.findNameAndIdByClassNameAndStatus2(map.get("className"),map.get("status"),map.get("xueqi"));
    }

    @PostMapping("/findShiyeById")
    public ResponseData findShiyeById(@RequestBody Map<String, String> map) {
        return service.findShiyeById(map.get("studentId"),map.get("xueqi"));
    }

    @PostMapping("/failShiyeById")
    public ResponseData failShiyeById(@RequestBody Map<String, String> map) {
        return service.failShiyeById(map.get("studentId"),map.get("xueqi"),map.get("note"));
    }


    @PostMapping("/completeShiye")
    public ResponseData completeShiye(@RequestBody Map<String, String> map) {
        System.out.println(map.toString());
        return service.completeShiye(map.get("studentId"),map.get("xueqi"));
    }


    /**
     * 重置学生密码
     */
    @PostMapping("/resetStuPwd")
    public ResponseData resetStuPwd(@RequestBody Map<String,String> map) {
        return service.resetStuPwd(map.get("id"));
    }

}
