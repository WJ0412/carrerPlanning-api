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
public interface MapperDao extends JpaRepository<Student, String> {

    @Query(value = "SELECT student.id AS id,stuinfo.name AS name,stuinfo.status AS status,student.classname AS className FROM student INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findInfoByName(String name, String teacherNo);

    @Query(value = "SELECT student.id AS id,stuinfo.name AS name,stuinfo.status AS status,student.classname AS className FROM student INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and stuinfo.status = ?1", nativeQuery = true)
    List<Map<String, String>> findInfoByStatus(String status, String teacherNo);

    @Query(value = "SELECT student.id AS id,stuinfo.name AS name,stuinfo.status AS status,student.classname AS className FROM student INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and stuinfo.status = ?1 and stuinfo.name like %?2%", nativeQuery = true)
    List<Map<String, String>> findInfoByStatusAndName(String status, String name, String teacherNo);

    @Query(value = "SELECT student.id AS id,stuinfo.name AS name,stuinfo.status AS status,student.classname AS className FROM student INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and class.name = ?1", nativeQuery = true)
    List<Map<String, String>> findInfoByClassName(String className, String teacherNo);

    @Query(value = "SELECT student.id AS id,stuinfo.name AS name,stuinfo.status AS status,student.classname AS className FROM student INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findInfoByNameAndClassName(String name, String className, String teacherNo);

    @Query(value = "SELECT student.id AS id,stuinfo.name AS name,stuinfo.status AS status,student.classname AS className FROM student INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and stuinfo.status = ?1", nativeQuery = true)
    List<Map<String, String>> findInfoByStatusAndClassName(String status, String className, String teacherNo);

    @Query(value = "SELECT student.id AS id,stuinfo.name AS name,stuinfo.status AS status,student.classname AS className FROM student INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?4 and class.name = ?3 and stuinfo.status = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findInfoByNameStatusAndClassName(String name, String status, String className, String teacherNo);

    @Query(value = "select * from stuInfo where id = ?1", nativeQuery = true)
    Map<String, String> findInfoById(String id);

    @Modifying
    @Transactional
    @Query(value = "update stuinfo set status = '已完成' where id = ?1", nativeQuery = true)
    int completeInfo(String id);

    @Modifying
    @Transactional
    @Query(value = "update stuinfo set status = '未通过',note=?2 where id = ?1", nativeQuery = true)
    int failInfo(String id, String note);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,family.status AS status,student.classname AS className FROM student INNER JOIN family ON student.id = family.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findFamilyByName(String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,family.status AS status,student.classname AS className FROM student INNER JOIN family ON student.id = family.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and family.status = ?1", nativeQuery = true)
    List<Map<String, String>> findFamilyByStatus(String status, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,family.status AS status,student.classname AS className FROM student INNER JOIN family ON student.id = family.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and family.status = ?1 and stuinfo.name like %?2%", nativeQuery = true)
    List<Map<String, String>> findFamilyByStatusAndName(String status, String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,family.status AS status,student.classname AS className FROM student INNER JOIN family ON student.id = family.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and class.name = ?1", nativeQuery = true)
    List<Map<String, String>> findFamilyByClassName(String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,family.status AS status,student.classname AS className FROM student INNER JOIN family ON student.id = family.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findFamilyByNameAndClassName(String name, String className, String teacherNo);


    @Query(value = "SELECT DISTINCT\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name,\n" +
            "\t'未提交' as status,\n" +
            "\tstudent.classname AS className\n" +
            "FROM\n" +
            "\tstudent\n" +
            "INNER JOIN class ON student.className = class. NAME\n" +
            "INNER JOIN stuinfo ON student.id = stuinfo.id\n" +
            "INNER JOIN teacher ON teacher.no = class.teacherno\n" +
            "WHERE\n" +
            "\tteacher.no = ?2\n" +
            "AND class. NAME = ?1\n" +
            "and student.id not in (select studentid  from family)",nativeQuery = true)
    List<Map<String, String>> findFamilyByWeiAndClassName(String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,family.status AS status,student.classname AS className FROM student INNER JOIN family ON student.id = family.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and family.status = ?1", nativeQuery = true)
    List<Map<String, String>> findFamilyByStatusAndClassName(String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,family.status AS status,student.classname AS className FROM student INNER JOIN family ON student.id = family.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?4 and class.name = ?3 and family.status = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findFamilyByNameStatusAndClassName(String name, String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,family.status AS status,student.classname AS className FROM student INNER JOIN family ON student.id = family.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno where teacherNo = ?1", nativeQuery = true)
    List<Map<String, String>> findFamilyByTeacherNo(String teacherNo);

    @Query(value = "select * from family where studentid = ?1", nativeQuery = true)
    List<Map<String, String>> findFamilyById(String id);


    @Modifying
    @Transactional
    @Query(value = "update family set status = '已完成' where studentid = ?1", nativeQuery = true)
    int completeFamily(String id);

    @Modifying
    @Transactional
    @Query(value = "update family set status = '未通过',note=?2 where studentid = ?1", nativeQuery = true)
    int failFamily(String id, String note);


    /**
     * other表
     */

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,other.status AS status,student.classname AS className FROM student INNER JOIN other ON student.id = other.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findOtherByName(String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,other.status AS status,student.classname AS className FROM student INNER JOIN other ON student.id = other.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and other.status = ?1", nativeQuery = true)
    List<Map<String, String>> findOtherByStatus(String status, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,other.status AS status,student.classname AS className FROM student INNER JOIN other ON student.id = other.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and other.status = ?1 and stuinfo.name like %?2%", nativeQuery = true)
    List<Map<String, String>> findOtherByStatusAndName(String status, String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,other.status AS status,student.classname AS className FROM student INNER JOIN other ON student.id = other.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and class.name = ?1", nativeQuery = true)
    List<Map<String, String>> findOtherByClassName(String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,other.status AS status,student.classname AS className FROM student INNER JOIN other ON student.id = other.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findOtherByNameAndClassName(String name, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name,\n" +
            "\t'未提交' as status,\n" +
            "\tstudent.classname AS className\n" +
            "FROM\n" +
            "\tstudent\n" +
            "INNER JOIN class ON student.className = class. NAME\n" +
            "INNER JOIN stuinfo ON student.id = stuinfo.id\n" +
            "INNER JOIN teacher ON teacher.no = class.teacherno\n" +
            "WHERE\n" +
            "\tteacher.no = ?2\n" +
            "AND class. NAME = ?1\n" +
            "and student.id not in (select studentid  from other)",nativeQuery = true)
    List<Map<String, String>> findOtherByWeiAndClassName(String className, String teacherNo);


    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,other.status AS status,student.classname AS className FROM student INNER JOIN other ON student.id = other.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and other.status = ?1", nativeQuery = true)
    List<Map<String, String>> findOtherByStatusAndClassName(String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,other.status AS status,student.classname AS className FROM student INNER JOIN other ON student.id = other.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?4 and class.name = ?3 and other.status = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findOtherByNameStatusAndClassName(String name, String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,other.status AS status,student.classname AS className FROM student INNER JOIN other ON student.id = other.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno where teacherNo = ?1", nativeQuery = true)
    List<Map<String, String>> findOtherByTeacherNo(String teacherNo);

    @Query(value = "select * from other where studentid = ?1", nativeQuery = true)
    List<Map<String, String>> findOtherById(String id);


    @Modifying
    @Transactional
    @Query(value = "update other set status = '已完成' where studentid = ?1", nativeQuery = true)
    int completeOther(String id);

    @Modifying
    @Transactional
    @Query(value = "update other set status = '未通过',note=?2 where studentid = ?1", nativeQuery = true)
    int failOther(String id, String note);

    /**
     * 认知
     */
    @Modifying
    @Transactional
    @Query(value = "update renzhi set status = '已完成' where studentid = ?1", nativeQuery = true)
    int completeRenZhi(String id);

    @Modifying
    @Transactional
    @Query(value = "update renzhi set status = '未通过',note=?2 where studentid = ?1", nativeQuery = true)
    int failRenZhi(String id, String note);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,renzhi.status AS status,student.classname AS className FROM student INNER JOIN renzhi ON student.id = renzhi.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findRenZhiByName(String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,renzhi.status AS status,student.classname AS className FROM student INNER JOIN renzhi ON student.id = renzhi.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and renzhi.status = ?1", nativeQuery = true)
    List<Map<String, String>> findRenZhiByStatus(String status, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,renzhi.status AS status,student.classname AS className FROM student INNER JOIN renzhi ON student.id = renzhi.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and renzhi.status = ?1 and stuinfo.name like %?2%", nativeQuery = true)
    List<Map<String, String>> findRenZhiByStatusAndName(String status, String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,renzhi.status AS status,student.classname AS className FROM student INNER JOIN renzhi ON student.id = renzhi.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and class.name = ?1", nativeQuery = true)
    List<Map<String, String>> findRenZhiByClassName(String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,renzhi.status AS status,student.classname AS className FROM student INNER JOIN renzhi ON student.id = renzhi.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findRenZhiByNameAndClassName(String name, String className, String teacherNo);


    @Query(value = "SELECT DISTINCT\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name,\n" +
            "\t'未提交' as status,\n" +
            "\tstudent.classname AS className\n" +
            "FROM\n" +
            "\tstudent\n" +
            "INNER JOIN class ON student.className = class. NAME\n" +
            "INNER JOIN stuinfo ON student.id = stuinfo.id\n" +
            "INNER JOIN teacher ON teacher.no = class.teacherno\n" +
            "WHERE\n" +
            "\tteacher.no = ?2\n" +
            "AND class. NAME = ?1\n" +
            "and student.id not in (select studentid  from renzhi)",nativeQuery = true)
    List<Map<String, String>> findRenZhiByWeiAndClassName(String className, String teacherNo);


    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,renzhi.status AS status,student.classname AS className FROM student INNER JOIN renzhi ON student.id = renzhi.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and stuinfo.status = ?1", nativeQuery = true)
    List<Map<String, String>> findRenZhiByStatusAndClassName(String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,renzhi.status AS status,student.classname AS className FROM student INNER JOIN renzhi ON student.id = renzhi.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?4 and class.name = ?3 and renzhi.status = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findRenZhiByNameStatusAndClassName(String name, String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,renzhi.status AS status,student.classname AS className FROM student INNER JOIN renzhi ON student.id = renzhi.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno where teacherNo = ?1", nativeQuery = true)
    List<Map<String, String>> findRenZhiByTeacherNo(String teacherNo);

    @Query(value = "select * from renzhi where studentid = ?1", nativeQuery = true)
    List<Map<String, String>> findRenZhiById(String id);


    /**
     * 就业
     */
    @Modifying
    @Transactional
    @Query(value = "update job set status = '已完成' where studentid = ?1", nativeQuery = true)
    int completeJob(String id);

    @Modifying
    @Transactional
    @Query(value = "update job set status = '未通过',note=?2 where studentid = ?1", nativeQuery = true)
    int failJob(String id, String note);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,job.status AS status,student.classname AS className FROM student INNER JOIN job ON student.id = job.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findJobByName(String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,job.status AS status,student.classname AS className FROM student INNER JOIN job ON student.id = job.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and job.status = ?1", nativeQuery = true)
    List<Map<String, String>> findJobByStatus(String status, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,job.status AS status,student.classname AS className FROM student INNER JOIN job ON student.id = job.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and job.status = ?1 and stuinfo.name like %?2%", nativeQuery = true)
    List<Map<String, String>> findJobByStatusAndName(String status, String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,job.status AS status,student.classname AS className FROM student INNER JOIN job ON student.id = job.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and class.name = ?1", nativeQuery = true)
    List<Map<String, String>> findJobByClassName(String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,job.status AS status,student.classname AS className FROM student INNER JOIN job ON student.id = job.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findJobByNameAndClassName(String name, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name,\n" +
            "\t'未提交' as status,\n" +
            "\tstudent.classname AS className\n" +
            "FROM\n" +
            "\tstudent\n" +
            "INNER JOIN class ON student.className = class. NAME\n" +
            "INNER JOIN stuinfo ON student.id = stuinfo.id\n" +
            "INNER JOIN teacher ON teacher.no = class.teacherno\n" +
            "WHERE\n" +
            "\tteacher.no = ?2\n" +
            "AND class. NAME = ?1\n" +
            "and student.id not in (select studentid  from job)",nativeQuery = true)
    List<Map<String, String>> findJobByWeiAndClassName(String className, String teacherNo);


    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,job.status AS status,student.classname AS className FROM student INNER JOIN job ON student.id = job.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and stuinfo.status = ?1", nativeQuery = true)
    List<Map<String, String>> findJobByStatusAndClassName(String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,job.status AS status,student.classname AS className FROM student INNER JOIN job ON student.id = job.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?4 and class.name = ?3 and job.status = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findJobByNameStatusAndClassName(String name, String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,job.status AS status,student.classname AS className FROM student INNER JOIN job ON student.id = job.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno where teacherNo = ?1", nativeQuery = true)
    List<Map<String, String>> findJobByTeacherNo(String teacherNo);

    @Query(value = "select * from job where studentid = ?1", nativeQuery = true)
    List<Map<String, String>> findJobById(String id);


    /**
     * 创业
     */
    @Modifying
    @Transactional
    @Query(value = "update business set status = '已完成' where studentid = ?1", nativeQuery = true)
    int completeBusiness(String id);

    @Modifying
    @Transactional
    @Query(value = "update business set status = '未通过',note=?2 where studentid = ?1", nativeQuery = true)
    int failBusiness(String id, String note);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,business.status AS status,student.classname AS className FROM student INNER JOIN business ON student.id = business.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findBusinessByName(String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,business.status AS status,student.classname AS className FROM student INNER JOIN business ON student.id = business.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and business.status = ?1", nativeQuery = true)
    List<Map<String, String>> findBusinessByStatus(String status, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,business.status AS status,student.classname AS className FROM student INNER JOIN business ON student.id = business.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and business.status = ?1 and stuinfo.name like %?2%", nativeQuery = true)
    List<Map<String, String>> findBusinessByStatusAndName(String status, String name, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,business.status AS status,student.classname AS className FROM student INNER JOIN business ON student.id = business.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?2 and class.name = ?1", nativeQuery = true)
    List<Map<String, String>> findBusinessByClassName(String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,business.status AS status,student.classname AS className FROM student INNER JOIN business ON student.id = business.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findBusinessByNameAndClassName(String name, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name,\n" +
            "\t'未提交' as status,\n" +
            "\tstudent.classname AS className\n" +
            "FROM\n" +
            "\tstudent\n" +
            "INNER JOIN class ON student.className = class. NAME\n" +
            "INNER JOIN stuinfo ON student.id = stuinfo.id\n" +
            "INNER JOIN teacher ON teacher.no = class.teacherno\n" +
            "WHERE\n" +
            "\tteacher.no = ?2\n" +
            "AND class. NAME = ?1\n" +
            "and student.id not in (select studentid  from business)",nativeQuery = true)
    List<Map<String, String>> findBusinessByWeiAndClassName(String className, String teacherNo);


    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,business.status AS status,student.classname AS className FROM student INNER JOIN business ON student.id = business.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?3 and class.name = ?2 and business.status = ?1", nativeQuery = true)
    List<Map<String, String>> findBusinessByStatusAndClassName(String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,business.status AS status,student.classname AS className FROM student INNER JOIN business ON student.id = business.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno WHERE teacher.no = ?4 and class.name = ?3 and business.status = ?2 and stuinfo.name like %?1%", nativeQuery = true)
    List<Map<String, String>> findBusinessByNameStatusAndClassName(String name, String status, String className, String teacherNo);

    @Query(value = "SELECT DISTINCT student.id AS id,stuinfo.name AS name,business.status AS status,student.classname AS className FROM student INNER JOIN business ON student.id = business.studentid INNER JOIN class ON student.className = class. NAME INNER JOIN stuinfo ON student.id = stuinfo.id INNER JOIN teacher ON teacher.no = class.teacherno where teacherNo = ?1", nativeQuery = true)
    List<Map<String, String>> findBusinessByTeacherNo(String teacherNo);

    @Query(value = "select * from business where studentid = ?1", nativeQuery = true)
    List<Map<String, String>> findBusinessById(String id);

    /**
     * 学业
     */


    @Query(value = "select student.id as id,stuinfo.name as name from student inner join stuinfo on student.id=stuinfo.id where student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findStudentsByClassName(String className);

    // 第一学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join xueye1 on xueye1.studentid = student.id\n" +
            "where\n" +
            "\txueye1.studentid=stuinfo.id and student.classname = ?1 and xueye1.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findOneByClassNameAndStatus(String className, String status);

    // 第一学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from xueye1) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findOneByClassNameAndStatus(String className);

    // 第二学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join xueye2 on xueye2.studentid = student.id\n" +
            "where\n" +
            "\txueye2.studentid=stuinfo.id and student.classname = ?1 and xueye2.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findSecondByClassNameAndStatus(String className, String status);

    // 第二学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from xueye2) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findSecondByClassNameAndStatus(String className);

    // 第三学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join xueye3 on xueye3.studentid = student.id\n" +
            "where\n" +
            "\txueye3.studentid=stuinfo.id and student.classname = ?1 and xueye3.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findThirdByClassNameAndStatus(String className, String status);

    // 第三学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from xueye3) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findThirdByClassNameAndStatus(String className);

    // 第四学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join xueye4 on xueye4.studentid = student.id\n" +
            "where\n" +
            "\txueye4.studentid=stuinfo.id and student.classname = ?1 and xueye4.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findFourthByClassNameAndStatus(String className, String status);

    // 第四学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from xueye4) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findFourthByClassNameAndStatus(String className);

    // 第五学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join xueye5 on xueye5.studentid = student.id\n" +
            "where\n" +
            "\txueye5.studentid=stuinfo.id and student.classname = ?1 and xueye5.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findFifthByClassNameAndStatus(String className, String status);

    // 第五学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from xueye5) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findFifthByClassNameAndStatus(String className);

    // 第六学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join xueye6 on xueye6.studentid = student.id\n" +
            "where\n" +
            "\txueye6.studentid=stuinfo.id and student.classname = ?1 and xueye6.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findSixthByClassNameAndStatus(String className, String status);

    // 第六学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from xueye6) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findSixthByClassNameAndStatus(String className);

    // 第七学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join xueye7 on xueye7.studentid = student.id\n" +
            "where\n" +
            "\txueye7.studentid=stuinfo.id and student.classname = ?1 and xueye7.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findSeventhByClassNameAndStatus(String className, String status);

    // 第七学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from xueye7) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findSeventhByClassNameAndStatus(String className);

    // 第八学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join xueye8 on xueye8.studentid = student.id\n" +
            "where\n" +
            "\txueye8.studentid=stuinfo.id and student.classname = ?1 and xueye8.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findEighthByClassNameAndStatus(String className, String status);

    // 第八学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from xueye8) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findEighthByClassNameAndStatus(String className);

    @Query(value = "select xueye1.*,score.pro1 as proscore,score.gen1 as genscore,score.inn1 as innscore from xueye1,score where xueye1.studentid = ?1 and score.studentid=xueye1.studentid", nativeQuery = true)
    Map<String, String> findFirstById(String studentId);

    @Query(value = "select xueye2.*,score.pro2 as proscore,score.gen2 as genscore,score.inn2 as innscore from xueye2,score where xueye2.studentid = ?1 and score.studentid=xueye2.studentid", nativeQuery = true)
    Map<String, String> findSecondById(String studentId);

    @Query(value = "select xueye3.*,score.pro3 as proscore,score.gen3 as genscore,score.inn3 as innscore from xueye3,score where xueye3.studentid = ?1 and score.studentid=xueye3.studentid", nativeQuery = true)
    Map<String, String> findThirdById(String studentId);

    @Query(value = "select xueye4.*,score.pro4 as proscore,score.gen4 as genscore,score.inn4 as innscore from xueye4,score where xueye4.studentid = ?1 and score.studentid=xueye4.studentid", nativeQuery = true)
    Map<String, String> findFourthById(String studentId);

    @Query(value = "select xueye5.*,score.pro5 as proscore,score.gen5 as genscore,score.inn5 as innscore from xueye5,score where xueye5.studentid = ?1 and score.studentid=xueye5.studentid", nativeQuery = true)
    Map<String, String> findFifthById(String studentId);

    @Query(value = "select xueye6.*,score.pro6 as proscore,score.gen6 as genscore,score.inn6 as innscore from xueye6,score where xueye6.studentid = ?1 and score.studentid=xueye6.studentid", nativeQuery = true)
    Map<String, String> findSixthById(String studentId);

    @Query(value = "select xueye7.*,score.pro7 as proscore,score.gen7 as genscore,score.inn7 as innscore from xueye7,score where xueye7.studentid = ?1 and score.studentid=xueye7.studentid", nativeQuery = true)
    Map<String, String> findSeventhById(String studentId);

    @Query(value = "select xueye8.*,score.pro8 as proscore,score.gen8 as genscore,score.inn8 as innscore from xueye8,score where xueye8.studentid = ?1 and score.studentid=xueye8.studentid", nativeQuery = true)
    Map<String, String> findEighthById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update xueye1 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failFirstById(String studentId,String note);

    @Modifying
    @Transactional
    @Query(value = "update xueye2 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failSecondById(String studentId,String note);

    @Modifying
    @Transactional
    @Query(value = "update xueye3 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failThirdById(String studentId,String note);

    @Modifying
    @Transactional
    @Query(value = "update xueye4 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failFourthById(String studentId,String note);

    @Modifying
    @Transactional
    @Query(value = "update xueye5 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failFifthById(String studentId,String note);

    @Modifying
    @Transactional
    @Query(value = "update xueye6 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failSixthById(String studentId,String note);

    @Modifying
    @Transactional
    @Query(value = "update xueye7 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failSeventhById(String studentId,String note);

    @Modifying
    @Transactional
    @Query(value = "update xueye8 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failEighthById(String studentId,String note);


    @Modifying
    @Transactional
    @Query(value = "update xueye1 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeFirstById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update xueye2 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeSecondById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update xueye3 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeThirdById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update xueye4 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeFourthById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update xueye5 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeFifthById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update xueye6 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeSixthById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update xueye7 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeSeventhById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update xueye8 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeEighthById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update score set teacher1 = ?2 where studentid = ?1",nativeQuery = true)
    int scoreFirstById(String studentId, String score);

    @Modifying
    @Transactional
    @Query(value = "update score set teacher2 = ?2 where studentid = ?1",nativeQuery = true)
    int scoreSecondById(String studentId, String score);

    @Modifying
    @Transactional
    @Query(value = "update score set teacher3 = ?2 where studentid = ?1",nativeQuery = true)
    int scoreThirdById(String studentId, String score);

    @Modifying
    @Transactional
    @Query(value = "update score set teacher4 = ?2 where studentid = ?1",nativeQuery = true)
    int scoreFourthById(String studentId, String score);

    @Modifying
    @Transactional
    @Query(value = "update score set teacher5 = ?2 where studentid = ?1",nativeQuery = true)
    int scoreFifthById(String studentId, String score);

    @Modifying
    @Transactional
    @Query(value = "update score set teacher6 = ?2 where studentid = ?1",nativeQuery = true)
    int scoreSixthById(String studentId, String score);

    @Modifying
    @Transactional
    @Query(value = "update score set teacher7 = ?2 where studentid = ?1",nativeQuery = true)
    int scoreSeventhById(String studentId, String score);

    @Modifying
    @Transactional
    @Query(value = "update score set teacher8 = ?2 where studentid = ?1",nativeQuery = true)
    int scoreEighthById(String studentId, String score);


    /**
     * 事业规划
     */

    // 第一学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join shiye1 on shiye1.studentid = student.id\n" +
            "where\n" +
            "\tshiye1.studentid=stuinfo.id and student.classname = ?1 and shiye1.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findShiye1ByClassNameAndStatus(String className, String status);

    // 第一学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from shiye1) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findShiye1ByClassNameAndStatus(String className);

    // 第二学期其他状态的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "inner join shiye2 on shiye2.studentid = studentid\n" +
            "where\n" +
            "\tshiye2.studentid=stuinfo.id and student.classname = ?1 and shiye2.status  = ?2", nativeQuery = true)
    List<Map<String, String>> findShiye2ByClassNameAndStatus(String className, String status);

    // 第二学期未提交的
    @Query(value = "select\n" +
            "\tstudent.id as id,\n" +
            "\tstuinfo.name as name\n" +
            "from\n" +
            "\tstudent\n" +
            "inner join stuinfo on student.id = stuinfo.id\n" +
            "where\n" +
            "\tstuinfo.id not in (select studentid from shiye2) and student.classname = ?1", nativeQuery = true)
    List<Map<String, String>> findShiye2ByClassNameAndStatus(String className);


    @Modifying
    @Transactional
    @Query(value = "update shiye1 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failShiye1ById(String studentId,String note);

    @Modifying
    @Transactional
    @Query(value = "update shiye2 set status = '未通过',note = ?2 where studentid = ?1",nativeQuery = true)
    int failShiye2ById(String studentId,String note);


    @Modifying
    @Transactional
    @Query(value = "update shiye1 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeShiye1ById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update shiye2 set status = '已完成' where studentid = ?1",nativeQuery = true)
    int completeShiye2ById(String studentId);

    @Query(value = "select * from shiye1 where studentid = ?1 ", nativeQuery = true)
    Map<String, String> findShiye1ById(String studentId);


    @Query(value = "select * from shiye2 where studentid = ?1 ", nativeQuery = true)
    Map<String, String> findShiye2ById(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update student set pwd = ?1 where id=?1", nativeQuery = true)
    int resetStuPwd(String id);

    @Query(value = "select * from admin",nativeQuery = true)
    List<Map<String,String>> findAllAdmins();

    @Modifying
    @Transactional
    @Query(value = "delete from admin where name=?1", nativeQuery = true)
    int deleteAdmin(String id);

    @Modifying
    @Transactional
    @Query(value = "update admin set name = ?1,pwd=?2,role=?3 where name=?4", nativeQuery = true)
    int updateAdmin(String newId, String pwd, String role, String oldId);

    @Modifying
    @Transactional
    @Query(value = "insert into admin values(?1,?2,?3)", nativeQuery = true)
    int addAdmin(String name, String pwd, String role);
}