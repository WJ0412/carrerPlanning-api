package com.mars.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mars.mapper.CollegeMapper;
import com.mars.model.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    /**
     * 查询所有学院信息
     */
    public List<College> findAll() {
        return collegeMapper.selectList(null);
    }

    /**
     * 根据名称进行模糊查询
     */
    public List<College> findLike(String name) {
        QueryWrapper<College> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return collegeMapper.selectList(wrapper);
    }

    /**
     * 新增学院
     */
    public int addCollege(College college) {
        return collegeMapper.insert(college);
    }

    /**
     * 修改学院
     */
    public int update(College college) {
        return collegeMapper.updateById(college);
    }

    /**
     * 删除学院
     */
    public int delete(College college) {
        int result = 0;
        return collegeMapper.deleteById(college.getId());
    }


    public College findById(String id) {
        return collegeMapper.selectById(id);
    }

    public College findByName(String name) {
        return collegeMapper.selectOne(new QueryWrapper<College>().eq("name", name));
    }
}
