package com.mars.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mars.model.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface StudentMapper extends BaseMapper<Student> {

    Map<String, String> getStudentByName(@Param("username") String username,
                                         @Param("password") String password);

    @Update("update student set password=#{password} where id=#{id}")
    Integer updatePwdById(String id, String password);
}
