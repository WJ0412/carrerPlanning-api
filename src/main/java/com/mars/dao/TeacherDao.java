package com.mars.dao;

import com.mars.entity.Teacher;
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
public interface TeacherDao extends JpaRepository<Teacher, String> {

    /**
     * 模糊查询(教师名称)
     */
    @Query(value = "select * from teacher where name like %?1% or no like %?1%", nativeQuery = true)
    List<Teacher> findLike(String name);

    /**
     * 模糊查询(教师名称或工号、学院名称)
     */
    @Query(value = "select * from teacher where (name like %?1% or no like %?1%) and collegeName = ?2", nativeQuery = true)
    List<Teacher> findLike(String name, String collegeName);

    /**
     * 模糊查询(学院名称)
     */
    @Query(value = "select * from teacher where collegeName = ?1", nativeQuery = true)
    List<Teacher> findTeachersByCollegeName(String collegeName);

    /**
     * 新增教师
     */
    @Modifying
    @Transactional
    @Query(value = "insert into teacher values(?1,?2,?3,?4,?5)", nativeQuery = true)
    int add(String no, String name, String pwd, String tel, String collegeName);

    /**
     * 修改教师
     */
    @Modifying
    @Transactional
    @Query(value = "update teacher set name =?2,pwd=?3,tel=?4,collegeName=?5 where no =?1", nativeQuery = true)
    int update(String no, String name, String pwd, String tel, String collegeName);

    /**
     * 删除教师
     */
    @Modifying
    @Transactional
    @Query(value = "delete from teacher where no =?1", nativeQuery = true)
    int delete(String no);

    /**
     * 查询教师
     */
    @Query(value = "select count(*) from teacher where no = ?1 and name = ?2",nativeQuery = true)
    int existsName(String no,String name);

    @Query(value = "select count(*) from teacher where no = ?1",nativeQuery = true)
    int findByNo(String no);
    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    @Query(value = "select name,no as id from teacher where no=?1 and pwd =?2",nativeQuery = true)
    Map<String,String> getTeacherByName(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "update teacher set pwd=?2 where no=?1",nativeQuery = true)
    Integer updatePwdById(String id, String pass);
}
