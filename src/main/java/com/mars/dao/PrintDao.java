package com.mars.dao;

import com.mars.entity.Renzhi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PrintDao extends JpaRepository<Renzhi,String> {

    @Query(value = "select * from renzhi where studentid = ?1",nativeQuery = true)
    Map<String,String> findRenzhiByStudentid(String id);

    @Query(value = "select * from xueye1 where studentid = ?1",nativeQuery = true)
    Map<String,String> findXuYe1ByStudentid(String id);

    @Query(value = "select * from xueye2 where studentid = ?1",nativeQuery = true)
    Map<String,String> findXuYe2ByStudentid(String id);

    @Query(value = "select * from xueye3 where studentid = ?1",nativeQuery = true)
    Map<String,String> findXuYe3ByStudentid(String id);

    @Query(value = "select * from xueye4 where studentid = ?1",nativeQuery = true)
    Map<String,String> findXuYe4ByStudentid(String id);

    @Query(value = "select * from xueye5 where studentid = ?1",nativeQuery = true)
    Map<String,String> findXuYe5ByStudentid(String id);

    @Query(value = "select * from xueye6 where studentid = ?1",nativeQuery = true)
    Map<String,String> findXuYe6ByStudentid(String id);

    @Query(value = "select * from xueye7 where studentid = ?1",nativeQuery = true)
    Map<String,String> findXuYe7ByStudentid(String id);

    @Query(value = "select * from xueye8 where studentid = ?1",nativeQuery = true)
    Map<String,String> findXuYe8ByStudentid(String id);

    @Query(value = "select * from job where studentid = ?1",nativeQuery = true)
    Map<String,String> findJobByStudentid(String id);

    @Query(value = "select * from business where studentid = ?1",nativeQuery = true)
    Map<String,String> findBusinessByStudentid(String id);

    @Query(value = "select * from shiYe1 where studentid = ?1",nativeQuery = true)
    Map<String,String> findShiYe1ByStudentid(String id);

    @Query(value = "select * from shiYe2 where studentid = ?1",nativeQuery = true)
    Map<String,String> findShiYe2ByStudentid(String id);

    @Query(value = "select * from stuinfo where id = ?1",nativeQuery = true)
    Map<String,String> findInfoById(String id);


    @Query(value = "select * from family where studentid = ?1",nativeQuery = true)
    List<Map<String,String>> findFamilyById(String id);

    @Query(value = "select * from other where studentid = ?1",nativeQuery = true)
    List<Map<String,String>> findOtherById(String id);

}
