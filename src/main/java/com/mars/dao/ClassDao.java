package com.mars.dao;

import com.mars.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author huangzhongwen
 */
@Repository
public interface ClassDao extends JpaRepository<Class,String> {
    /**
     * 查询所有班级
     */
    @Query(value = "select c.name,t.name as teacherName,c.teacherNo,c.majorName,c.yearName from class as c,teacher as t where c.teacherno = t.no or c.teacherno = null ",nativeQuery = true)
    List<Map<String,String>> findAllClasses();

    /**
     * 模糊查询(专业名称)
     */
    @Query(value = "select c.name,t.name as teacherName,c.teacherNo,c.majorName,c.yearName from class as c,teacher as t where c.teacherno = t.no and c.majorname = ?1",nativeQuery = true)
    List<Map<String,String>> findLike(String majorName);

    /**
     * 模糊查询(专业名称 班级名称)
     */
    @Query(value = "select c.name,t.name as teacherName,c.teacherNo,c.majorName,c.yearName from class as c,teacher as t where c.teacherno = t.no and c.majorname = ?1 and c.name like %?2%",nativeQuery = true)
    List<Map<String,String>> findLike(String majorName,String name);

    /**
     * 模糊查询(班级名称)
     */
    @Query(value = "select c.name,t.name as teacherName,c.teacherNo,c.majorName,c.yearName from class as c,teacher as t where c.teacherno = t.no and c.name like %?1%",nativeQuery = true)
    List<Map<String,String>> findLikeByName(String name);

    /**
     * 模糊查询(学院名称)
     */
    @Query(value = "select c.name,t.name as teacherName,c.teacherNo,c.majorName,c.yearName from class as c,teacher as t,major as m where c.teacherno = t.no and c.majorName = m.name and m.collegeName = ?1",nativeQuery = true)
    List<Map<String,String>> findLikeByCollegeName(String collegeName);


    /**
     * 新增班级
     */
    @Modifying
    @Transactional
    @Query(value = "insert into class values (?1,?2,?3,?4)",nativeQuery = true)
    int add(String name, String teacherNo, String majorName, String yearName);

    /**
     * 修改班级
     */
    @Modifying
    @Transactional
    @Query(value = "update class set name = ?1,teacherNo = ?2, majorName = ?3, yearName = ?4 where name = ?5",nativeQuery = true)
    int update(String newName, String teacherNo, String majorName, String yearName, String oldName);

    /**
     * 删除班级
     */
    @Modifying
    @Transactional
    @Query(value = "delete from class where name = ?1",nativeQuery = true)
    int delete(String name);

    /**
     * 查询指定专业下的班级名称
     */
    @Query(value = "select name from class where majorName = ?1",nativeQuery = true)
    List<Map<String,String>> findByMajor(String majorName);

    /**
     * 通过name查询
     */
    @Query(value = "select count(*) from class where name = ?1",nativeQuery = true)
    int findByName(String name);

    /**
     * findClassByTeacherNo
     */
    @Query(value = "select * from class where teacherNo = ?1",nativeQuery = true)
    List<Class> findClassByTeacherNo(String teacherNo);

}
