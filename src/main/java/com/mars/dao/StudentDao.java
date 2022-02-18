package com.mars.dao;

import com.mars.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentDao extends JpaRepository<Student,String> {
    /**
     * 查询所有学生信息
     */
    @Query(value = "select student.id as id,stuinfo.name as name,student.pwd as pwd,stuinfo.sex as sex,stuinfo.tel as tel,student.className as className from student,stuInfo where student.id = stuinfo.id",nativeQuery = true)
    List<Map<String,String>> findAllStudents();

    /**
     * 模糊查询(根据学生姓名以及班级名称)
     */
    @Query(value = "select student.id as id,stuinfo.name as name,student.pwd as pwd,stuinfo.sex as sex,stuinfo.tel as tel,student.className as className from student,stuInfo where (student.id = stuinfo.id) and (student.classname = ?2) and (student.id like %?1% or stuinfo.name like %?1%)",nativeQuery = true)
    List<Map<String,String>> findLike(String nameOrId, String className);

    /**
     * 模糊查询
     */
    @Query(value = "select student.id as id,stuinfo.name as name,student.pwd as pwd,stuinfo.sex as sex,stuinfo.tel as tel,student.className as className from student,stuInfo where (student.id = stuinfo.id) and (student.id like %?1% or stuinfo.name like %?1%)",nativeQuery = true)
    List<Map<String,String>> findLike(String nameOrId);

    /**
     * 模糊查询（根据班级查询学生信息）
     */
    @Query(value = "select student.id as id,stuinfo.name as name,student.pwd as pwd,stuinfo.sex as sex,stuinfo.tel as tel,student.className as className from student,stuInfo where (student.id = stuinfo.id) and classname = ?1",nativeQuery = true)
    List<Map<String,String>> findLikeByClassName(String ClassName);

    /**
     * 新增学生
     */
    @Modifying
    @Transactional
    @Query(value = "insert into student(id,pwd,className,xuezhi) values(?1,?2,?3,?4)",nativeQuery = true)
    int add(String id, String pwd, String className,String xuezhi);

    @Modifying
    @Transactional
    @Query(value = "insert into student(id,pwd,className,xuezhi) values(?1,?2,?3,default)",nativeQuery = true)
    int add(String id, String pwd, String className);

    @Modifying
    @Transactional
    @Query(value = "update student set id = ?1,pwd=?2,className=?3 where id = ?4",nativeQuery = true)
    int update(String newId, String pwd, String className,String oldId);

    @Modifying
    @Transactional
    @Query(value = "delete from student where id = ?1",nativeQuery = true)
    int delete(String id);

    @Query(value = "select count(*) from student where id=?1",nativeQuery = true)
    int findId(String id);


    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    @Query(value = "select a.id as id, B.name from student as A,stuinfo as B where A.id=B.id and A.id=?1 and A.pwd =?2",nativeQuery = true)
    Map<String,String> getStudentByName(String username, String password);


    /**
     * 教师端表格查询
     */
    @Query(value = "SELECT student.id AS id,stuinfo.name AS name,stuinfo.status AS status,student.classname AS className FROM student INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher. NO = class.teacherno WHERE teacher. NO = ?1",nativeQuery = true)
    List<Map<String,String>> findAllStudentsByTeacher(String teacherNo);


    /**
     * 查询学制
     * @param sid
     * @return
     */
    @Query(value = "select xuezhi from student where id=?1",nativeQuery = true)
    String findXuezhi(String sid);

    @Modifying
    @Transactional
    @Query(value = "update student set pwd=?2 where id=?1",nativeQuery = true)
    Integer updatePwdById(String id, String pass);


    Student findStudentById(String id);
}
