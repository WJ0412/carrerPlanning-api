package com.mars.controller;

import com.mars.model.College;
import com.mars.model.Major;
import com.mars.service.CollegeService;
import com.mars.service.MajorService;
import com.mars.vo.MajorVO;
import com.mars.vo.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "MajorController")
@CrossOrigin
@RestController
@RequestMapping("admin")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @Autowired
    private CollegeService collegeService;

    /**
     * 查询所有专业信息
     */
    @GetMapping("/findAllMajors")
    public ResponseData findAllMajors() {
        List<MajorVO> majors = majorService.findAll();
        if (majors == null){
            return ResponseData.buildError("数据库中暂无专业数据");
        }
        return ResponseData.buildSuccess(majors);
    }

    /**
     * 模糊查询
     */
    @PostMapping("/findMajorsByNameLike")
    public ResponseData findMajorsByNameLike(@RequestBody MajorVO majorVO) {
        College college = new College();
        if (!majorVO.getCollegeName().isEmpty()) {
            college = collegeService.findByName(majorVO.getCollegeName());
        }
        List<MajorVO> majorVOS = majorService.findByNameLike(majorVO.getName(), college.getId());
        if (majorVOS == null)
            return ResponseData.buildError("无符合条件的专业");
        return ResponseData.buildSuccess(majorVOS);
    }

    /**
     * 新增专业
     */
    @PostMapping("/addMajor")
    public ResponseData addMajor(@RequestBody MajorVO majorVO) {
        Major byName = majorService.findByNameAndCollege(majorVO.getName(),null);
        if (byName != null) {
            return ResponseData.buildError("新增失败！专业名已存在");
        }
        College college = collegeService.findByName(majorVO.getCollegeName());
        Major major = new Major(majorVO.getId(), majorVO.getName(), college.getId());
        int result = majorService.addMajor(major);
        if (result == 0) {
            return ResponseData.buildError("新增专业失败,请检查服务器配置.");
        }
        return ResponseData.buildSuccess(result);
    }

    /**
     * 删除专业
     */
    @PostMapping("/deleteMajor")
    public ResponseData deleteMajor(@RequestBody MajorVO majorVO) {
        int result = majorService.delete(majorVO.getId());
        if (result == 0) {
            return ResponseData.buildError("删除学院失败,请检查服务器配置");
        }
        return ResponseData.buildSuccess(result);
    }

    /**
     * 修改专业
     */
    @PostMapping("/updateMajor")
    public ResponseData updateMajor(@RequestBody MajorVO majorVO) {
        College college = collegeService.findByName(majorVO.getCollegeName());
        Major major = new Major(majorVO.getId(), majorVO.getName(), college.getId());
        Major byName = majorService.findByNameAndCollege(major.getName(),major.getCollegeId());
        if (byName != null) {
            return ResponseData.buildError("专业名称重复");
        }
        int result = majorService.updateMajor(major);
        if (result == 0) {
            return ResponseData.buildError("修改学院失败,请检查服务器配置.");
        }
        return ResponseData.buildSuccess(result);
    }

}
