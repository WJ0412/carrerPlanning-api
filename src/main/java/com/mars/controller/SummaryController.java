package com.mars.controller;

import com.mars.dao.ClassDao;
import com.mars.dto.ConditionDto;
import com.mars.entity.Education;
import com.mars.entity.Tiaojian;
import com.mars.service.SummaryService;
import com.mars.vo.CompletionVo;
import com.mars.vo.EmpVo;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 统计汇总
 */
@RestController
@CrossOrigin
@RequestMapping("admin")
public class SummaryController {

    @Autowired
    SummaryService summaryService;

    @Autowired
    ClassDao classDao;

    /**
     * 目标达成度分析
     * @return
     */
    @PostMapping("/completion")
    public ResponseData completion(@RequestBody ConditionDto conditionDto){
        if(StringUtils.isEmpty (conditionDto.getYear())&&StringUtils.isEmpty (conditionDto.getCollege())&&StringUtils.isEmpty (conditionDto.getXueqi())){
            return ResponseData.buildError("请选择院系，学年和学期");
        }

        try {
            int i = Integer.parseInt(conditionDto.getXueqi());
            if(i<1&&i>10){
                return ResponseData.buildError("请选择正确的学期");
            }
        }catch (Exception e){
            return ResponseData.buildError("请选择正确的学期");
        }
        CompletionVo completionVo=summaryService.getCompletion(conditionDto);
        if(completionVo==null){
            return ResponseData.buildError("数据不存在");
        }
        return ResponseData.buildSuccess(completionVo);
    }

    @PostMapping("/findClassByMajorNameAndYear")
    public ResponseData findClassByMajorNameAndYear(@RequestBody ConditionDto conditionDto){
        String major = conditionDto.getMajor();
        String year = conditionDto.getYear();
        List<Map<String, String>> byMajor = classDao.findByMajor(major);
        if (byMajor.isEmpty())
            return ResponseData.buildError("当前专业下暂无班级信息");
        return ResponseData.buildSuccess(byMajor);
    }



    @PostMapping("/employment")
    public ResponseData employment(@RequestBody ConditionDto conditionDto){


        EmpVo employment = summaryService.getEmployment(conditionDto);
        if(employment==null){
            return ResponseData.buildError("数据不存在");
        }
        return ResponseData.buildSuccess(employment);
    }

    /**
     * 具备条件
     * @param conditionDto
     * @return
     */
    @PostMapping("/jubeitiaojian")
    public ResponseData jubeitiaojian(@RequestBody ConditionDto conditionDto){
        List<Tiaojian> list = summaryService.getTiaojian(conditionDto);
        if(list==null){
            return ResponseData.buildError("数据不存在");
        }
        return ResponseData.buildSuccess(list);
    }


    /**
     * 升学查询统计
     * @param conditionDto
     * @return
     */
    @PostMapping("/education")
    public ResponseData education(@RequestBody ConditionDto conditionDto){
        List<Education> list = summaryService.getEducation(conditionDto);
        if(list==null){
            return ResponseData.buildError("数据不存在");
        }
        return ResponseData.buildSuccess(list);
    }

}
