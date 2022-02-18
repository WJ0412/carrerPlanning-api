package com.mars.dao;

import com.mars.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * @author huangzhongwen
 */
@Repository
public interface AdminDao extends JpaRepository<Admin,String> {
    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    @Query(value = "select name,name as id from admin where name=?1 and pwd =?2",nativeQuery = true)
    Map<String,String> getAdminByName(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "delete from class ",nativeQuery = true)
    int deleteClasses();

    @Modifying
    @Transactional
    @Query(value = "delete from teacher ",nativeQuery = true)
    int deleteTeachers();

    @Modifying
    @Transactional
    @Query(value = "delete from stuInfo ",nativeQuery = true)
    int deleteInfo();

    @Modifying
    @Transactional
    @Query(value = "delete from student",nativeQuery = true)
    int deleteStudents();

    @Modifying
    @Transactional
    @Query(value = "update systemStatus set status = ?1 where id = '1001'",nativeQuery = true)
    int closeOrOpenSystem(String status);

    @Query(value = "select status from systemStatus where id = '1001'",nativeQuery = true)
    String findSystemStatus();

    @Query(value = "select role from admin where name = ?1",nativeQuery = true)
    String getRoleForAdmin(String id);
}
