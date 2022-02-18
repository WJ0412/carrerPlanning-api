package com.mars.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mars.model.Major;
import com.mars.vo.MajorVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorMapper extends BaseMapper<Major> {

    List<MajorVO> findByNameLike(@Param("name") String name,
                                 @Param("collegeId") String collegeId);
}
