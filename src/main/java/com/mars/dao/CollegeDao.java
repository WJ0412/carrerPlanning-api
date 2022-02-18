package com.mars.dao;

import com.mars.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangzhongwen
 */
@Repository
public interface CollegeDao extends JpaRepository<College, Integer> {

    /**
     * 根据名称进行模糊查询
     */
    @Query(value = "select * from college where name like %?1% order by xuhao", nativeQuery = true)
    List<College> findLike(String name);


    @Query(value = "select * from college order by xuhao",nativeQuery = true)
    List<College> find();

    /**
     * 新增学院
     */
    @Modifying
    @Transactional
    @Query(value = "insert into college values (?1,?2)", nativeQuery = true)
    int add(String name,String xuhao);

    /**
     * 修改学院名称
     */
    @Modifying
    @Transactional
    @Query(value = "update college set name = ?1,xuhao = ?3 where name = ?2",nativeQuery = true)
    int updateCollege(String newName,String oldName,String xuhao);

    /**
     * 删除学院
     */
    @Modifying
    @Transactional
    @Query(value = "delete from college where name=?1", nativeQuery = true)
    int deleteCollege(String name);

    @Query(value = "select count(*) from college where name = ?1",nativeQuery = true)
    int existCollege(String name);
}
