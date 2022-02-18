package com.mars.dao;

import com.mars.entity.StuInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StuInfoDao extends JpaRepository<StuInfo,String> {

    /**
     * 插入学生姓名以及学号
     */
    @Modifying
    @Transactional
    @Query(value = "insert into stuinfo(id,name) values(?1,?2)",nativeQuery = true)
    int addIdAndName(String id, String name);

    @Modifying
    @Transactional
    @Query(value = "update stuinfo set id = ?1,name=?2 where id = ?3",nativeQuery = true)
    int updateIdAndName(String newId, String name, String oldId);

    @Modifying
    @Transactional
    @Query(value = "delete from stuinfo where id = ?1",nativeQuery = true)
    int delete(String id);

    @Modifying
    @Transactional
    @Query(value = "insert into stuinfo(id,name,tel,code,birth) values(?1,?2,?3,?4,?5)",nativeQuery = true)
    int addInfo(String id,String name, String tel, String code,String birth);

    @Query(value = "select status from stuInfo where id = ?1",nativeQuery = true)
    String findStatus(String id);

    @Query(value = "select * from stuinfo where id=?1",nativeQuery = true)
    StuInfo findStuInfoById(String id);
}
