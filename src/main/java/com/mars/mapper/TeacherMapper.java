package com.mars.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mars.model.Teacher;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {

    @ResultType(Map.class)
    @Select("select name,id from teacher where id = #{username} and password = #{password}")
    Map<String, String> getTeacherByName(String username, String password);

    @Update("update teacher set password = #{password} where id = #{id}")
    Integer updatePwdById(String id, String password);
}
