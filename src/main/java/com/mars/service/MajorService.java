package com.mars.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mars.mapper.MajorMapper;
import com.mars.model.Major;
import com.mars.vo.MajorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {

    @Autowired
    private MajorMapper majorMapper;

    /**
     * 查询所有专业信息
     */
    public List<MajorVO> findAll() {
        return majorMapper.findByNameLike(null, null);
    }

    /**
     * 模糊查询
     */
    public List<MajorVO> findByNameLike(String name, String collegeId) {
        return majorMapper.findByNameLike(name, collegeId);
    }

    /**
     * 新增专业
     */
    public int addMajor(Major major) {
        return majorMapper.insert(major);
    }

    /**
     * 删除专业
     */
    public int delete(Integer id) {
        return majorMapper.deleteById(id);
    }

    /**
     * 修改专业
     */
    public int updateMajor(Major major) {
        return majorMapper.updateById(major);
    }

    public Major findByNameAndCollege(String name, String collegeId) {
        QueryWrapper<Major> wrapper = new QueryWrapper<>();
        if (name != null) {
            wrapper.eq("name", name);
        }
        if (collegeId != null) {
            wrapper.eq("college_id", collegeId);
        }
        return majorMapper.selectOne(wrapper);
    }
}
