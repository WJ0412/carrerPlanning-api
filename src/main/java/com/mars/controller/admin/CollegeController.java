package com.mars.controller.admin;

import com.mars.model.College;
import com.mars.service.CollegeService;
import com.mars.vo.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "CollegeController")
@CrossOrigin
@RestController
@RequestMapping("admin")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    /**
     * 查询所有学院信息
     */
    @GetMapping("/findAllColleges")
    public ResponseData findAllColleges() {
        List<College> colleges = collegeService.findAll();
        if (colleges == null) {
            return ResponseData.buildError("数据库中暂无学院数据");
        }
        return ResponseData.buildSuccess(colleges);
    }

    /**
     * 根据名称进行模糊查询
     */
    @PostMapping("/findCollegesByNameLike")
    public ResponseData findCollegesByNameLike(@RequestBody College college) {
        List<College> colleges = collegeService.findLike(college.getName());
        if (colleges == null) {
            return ResponseData.buildError("暂无当前学院信息");
        }
        return ResponseData.buildSuccess(colleges);
    }

    /**
     * 新增学院
     */
    @PostMapping("/addCollege")
    public ResponseData addCollege(@RequestBody College college) {
        if (collegeService.findByName(college.getName()) != null ||
                collegeService.findById(college.getId()) != null) {
            return ResponseData.buildError("该学院已存在");
        }
        int result = collegeService.addCollege(college);
        if (result == 0) {
            return ResponseData.buildError("新增学院失败");
        }
        return ResponseData.buildSuccess(result);
    }

    /**
     * 修改学院
     */
    @PostMapping("/updateCollege")
    public ResponseData updateCollege(@RequestBody College college) {
        if (collegeService.findByName(college.getName()) != null) {
            return ResponseData.buildError("该学院已存在");
        }
        int result = collegeService.update(college);
        return ResponseData.buildSuccess(result);
    }

    /**
     * 删除学院
     */
    @PostMapping("/deleteCollege")
    public ResponseData deleteCollege(@RequestBody College collegeWithName) {
        College college = collegeService.findByName(collegeWithName.getName());
        int result = collegeService.delete(college);
        if (result == 0) {
            return ResponseData.buildError("删除学院失败");
        }
        return ResponseData.buildSuccess(result);
    }
}
