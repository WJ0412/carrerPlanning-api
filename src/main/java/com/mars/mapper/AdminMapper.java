package com.mars.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mars.model.Admin;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    @ResultType(Map.class)
    @Select("select name,name as id from admin where name=#{username} and password =#{password}")
    Map<String, String> getAdminByName(String username, String password);

    @Select("select status from system_status where id = '1001'")
    String findSystemStatus();

}
