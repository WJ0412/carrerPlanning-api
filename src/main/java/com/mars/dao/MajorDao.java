package com.mars.dao;

import com.mars.entity.Major;
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
public interface MajorDao extends JpaRepository<Major,String> {

    /**
     * 查询所有
     */
    @Query(value="select * from major order by collegename ,xuhao",nativeQuery = true)
    List<Major> find();

    /**
     * 模糊查询(专业名称)
     */
    @Query(value = "select * from major where name like %?1% order by collegename ,xuhao",nativeQuery = true)
    List<Major> findLike(String name);

    /**
     * 模糊查询(专业名称、学院名称)
     */
    @Query(value = "select * from major where name like %?1% and collegeName = ?2 order by collegename ,xuhao",nativeQuery = true)
    List<Major> findLike(String name,String collegeName);

    /**
     * 模糊查询(学院名称)
     */
    @Query(value = "select * from major where collegeName = ?1 order by collegename ,xuhao",nativeQuery = true)
    List<Major> findMajorsByCollegeName(String collegeName);

    /**
     * 新增专业
     */
    @Modifying
    @Transactional
    @Query(value = "insert into major values (?1,?2,?3)",nativeQuery = true)
    int add(String name, String collegeName,String xuhao);

    /**
     * 修改专业
     */
    @Modifying
    @Transactional
    @Query(value = "update major set name = ?1,collegeName = ?2,xuhao=?4 where name = ?3",nativeQuery = true)
    int update(String newName,String collegeName,String oldName,String xuhao);

    /**
     * 删除专业
     */
    @Modifying
    @Transactional
    @Query(value = "delete from major where name=?1", nativeQuery = true)
    int delete(String name);

    /**
     * 判断专业是否存在
     */
    @Query(value = "select count(*) from major where name = ?1",nativeQuery = true)
    int existsMajor(String name);


}
