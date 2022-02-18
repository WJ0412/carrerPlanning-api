package com.mars.service;

import com.mars.dto.ConditionDto;
import com.mars.entity.Completion;
import com.mars.entity.Education;
import com.mars.entity.Emp;
import com.mars.entity.Tiaojian;
import com.mars.vo.CompletionVo;
import com.mars.vo.EmpVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Service
public class SummaryService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public CompletionVo getCompletion(ConditionDto conditionDto) {
        CompletionVo cv = new CompletionVo();
        String college = conditionDto.getCollege();
        String xueqi = conditionDto.getXueqi();
        String year = conditionDto.getYear();
        String major = conditionDto.getMajor();
        String cls = conditionDto.getCls();
        String teacher = conditionDto.getTeacher();

        StringBuilder sb = new StringBuilder();
        //学期分8个表，另外处理
        cv.setXueqi(xueqi);
        xueqi="teacher"+xueqi;

        if (!StringUtils.isEmpty(college)) {
            cv.setCollege(college);
            sb.append(" and cg.name='" + college + "' ");
        }
        if (!StringUtils.isEmpty(year)) {
            cv.setYear(year);
            sb.append(" and c.yearname='" + year + "' ");
        }
        if (!StringUtils.isEmpty(major)) {
            cv.setMajor(major);
            sb.append(" and m.name ='" + major + "' ");
        }
        if (!StringUtils.isEmpty(cls)) {
            cv.setCls(cls);
            sb.append(" and c.name ='" + cls + "' ");
        }
        if (!StringUtils.isEmpty(teacher)) {
            cv.setTeacher(teacher);
            sb.append(" and t.name ='" + teacher + "' ");
        }

        StringBuilder sqlstr = new StringBuilder();
        sqlstr.append("SELECT\n" +
                "\t'总数' AS category,\n" +
                "\t0 as total,\n" +
                "\tsum( CASE WHEN teacher1 is not null and teacher1 !='' THEN 1 ELSE 0 END) AS finish,\n" +
                "\tsum( CASE WHEN teacher1 = 100 THEN 1 ELSE 0 END ) AS '_100',\n" +
                "\tsum( CASE WHEN teacher1 >= 90 AND teacher1 <= 99 THEN 1 ELSE 0 END ) AS '_9099',\n" +
                "\tsum( CASE WHEN teacher1 >= 80 AND teacher1 <= 89 THEN 1 ELSE 0 END ) AS '_8089',\n" +
                "\tsum( CASE WHEN teacher1 >= 70 AND teacher1 <= 79 THEN 1 ELSE 0 END ) AS '_7079',\n" +
                "\tsum( CASE WHEN teacher1 >= 60 AND teacher1 <= 69 THEN 1 ELSE 0 END ) AS '_6069',\n" +
                "\tsum( CASE WHEN teacher1 >= 50 AND teacher1 <= 59 THEN 1 ELSE 0 END ) AS '_5059',\n" +
                "\tsum( CASE WHEN teacher1 >= 40 AND teacher1 <= 49 THEN 1 ELSE 0 END ) AS '_4049',\n" +
                "\tsum( CASE WHEN teacher1 >= 30 AND teacher1 <= 39 THEN 1 ELSE 0 END ) AS '_3039',\n" +
                "\tsum( CASE WHEN teacher1 >= 20 AND teacher1 <= 29 THEN 1 ELSE 0 END ) AS '_2029',\n" +
                "\tsum( CASE WHEN teacher1 >= 10 AND teacher1 <= 19 THEN 1 ELSE 0 END ) AS '_1019',\n" +
                "\tsum( CASE WHEN teacher1 >= 0 AND teacher1 <= 9 THEN 1 ELSE 0 END ) AS '_09'\n" +
                "FROM\n" +
                "\tstudent s\n" +
                "\tLEFT JOIN stuinfo si ON si.id = s.id\n" +
                "\tLEFT JOIN class c ON s.classname = c.`name`\n" +
                "\tLEFT JOIN score sc ON s.id = sc.studentid\n" +
                "\tLEFT JOIN teacher t ON c.teacherno = t.`no`\n" +
                "\tLEFT JOIN major m ON m.NAME = c.majorname\n" +
                "\tLEFT JOIN college cg ON cg.NAME = m.collegename \n" +
                "WHERE\n" +
                "\t1 = 1\n ");
        //条件
        sqlstr.append(sb);

        sqlstr.append("\nUNION ALL\n" +
                "SELECT\n" +
                "\tsi.sex AS category,\n" +
                "\t0 as total,\n" +
                "\tsum( CASE WHEN teacher1 is not null and teacher1 !='' THEN 1 ELSE 0 END) AS finish,\n" +
                "\tsum( CASE WHEN teacher1 = 100 THEN 1 ELSE 0 END ) AS '_100',\n" +
                "\tsum( CASE WHEN teacher1 >= 90 AND teacher1 <= 99 THEN 1 ELSE 0 END ) AS '_9099',\n" +
                "\tsum( CASE WHEN teacher1 >= 80 AND teacher1 <= 89 THEN 1 ELSE 0 END ) AS '_8089',\n" +
                "\tsum( CASE WHEN teacher1 >= 70 AND teacher1 <= 79 THEN 1 ELSE 0 END ) AS '_7079',\n" +
                "\tsum( CASE WHEN teacher1 >= 60 AND teacher1 <= 69 THEN 1 ELSE 0 END ) AS '_6069',\n" +
                "\tsum( CASE WHEN teacher1 >= 50 AND teacher1 <= 59 THEN 1 ELSE 0 END ) AS '_5059',\n" +
                "\tsum( CASE WHEN teacher1 >= 40 AND teacher1 <= 49 THEN 1 ELSE 0 END ) AS '_4049',\n" +
                "\tsum( CASE WHEN teacher1 >= 30 AND teacher1 <= 39 THEN 1 ELSE 0 END ) AS '_3039',\n" +
                "\tsum( CASE WHEN teacher1 >= 20 AND teacher1 <= 29 THEN 1 ELSE 0 END ) AS '_2029',\n" +
                "\tsum( CASE WHEN teacher1 >= 10 AND teacher1 <= 19 THEN 1 ELSE 0 END ) AS '_1019',\n" +
                "\tsum( CASE WHEN teacher1 >= 0 AND teacher1 <= 9 THEN 1 ELSE 0 END ) AS '_09'\n" +
                "FROM\n" +
                "\tstudent s\n" +
                "\tLEFT JOIN stuinfo si ON si.id = s.id\n" +
                "\tLEFT JOIN class c ON s.classname = c.`name`\n" +
                "\tLEFT JOIN score sc ON s.id = sc.studentid\n" +
                "\tLEFT JOIN teacher t ON c.teacherno = t.`no`\n" +
                "\tLEFT JOIN major m ON m.NAME = c.majorname\n" +
                "\tLEFT JOIN college cg ON cg.NAME = m.collegename \n" +
                "WHERE\n" +
                "\t1 = 1\n ");

        //条件
        sqlstr.append(sb);

        sqlstr.append("\nGROUP BY\n" +
                "\tsi.sex");


        //System.out.println(sqlstr.toString());
        String str=sqlstr.toString();
        String teacherN = str.replaceAll("teacher1", xueqi);
        Query query = entityManager.createNativeQuery(teacherN,Completion.class);//后面的参数是因为entityManager得到的结果是Objetc类型的，因此要转换为需要的对象

        List list = query.getResultList();
        if (list.isEmpty())
            return null;
        ArrayList<Completion> completions = new ArrayList<>();
        for (Object o : list) {
            if (o==null){
                continue;
            }
            Completion c = new Completion();
            BeanUtils.copyProperties(o, c);
            if (c.getCategory().equals("总数")) {
                c.setTotal(getAllCount(sb.toString()));
                cv.setAll(c);
            }

            if (c.getCategory().equals("女")) {
                c.setTotal(getFemaleCount(sb.toString()));
                cv.setFemale(c);
            }
            if (c.getCategory().equals("男")) {
                c.setTotal(getMaleCount(sb.toString()));
                cv.setMale(c);
            }
        }
        return cv;
    }

    private String getAllCount(String sb) {
        StringBuilder sqlstr2 = new StringBuilder();
        sqlstr2.append("SELECT\n" +
                "\tcount(*) AS allcount\n" +
                "FROM\n" +
                "\tstudent s\n" +
                "\tLEFT JOIN stuinfo si ON si.id = s.id\n" +
                "\tLEFT JOIN class c ON s.classname = c.`name`\n" +
                "\tLEFT JOIN teacher t ON c.teacherno = t.`no`\n" +
                "\tLEFT JOIN major m ON m.NAME = c.majorname\n" +
                "\tLEFT JOIN college cg ON cg.NAME = m.collegename \n" +
                "WHERE\n" +
                "\t1 = 1\n");
        sqlstr2.append(sb);
        String s = null;
        try {
            s = jdbcTemplate.queryForObject(sqlstr2.toString(), String.class);
        } catch (Exception e) {
            return null;
        }

        return s;
    }
    private String getMaleCount(String sb) {
        StringBuilder sqlstr2 = new StringBuilder();
        sqlstr2.append("SELECT\n" +
                "\tcount(*) AS allcount\n" +
                "FROM\n" +
                "\tstudent s\n" +
                "\tLEFT JOIN stuinfo si ON si.id = s.id\n" +
                "\tLEFT JOIN class c ON s.classname = c.`name`\n" +
                "\tLEFT JOIN teacher t ON c.teacherno = t.`no`\n" +
                "\tLEFT JOIN major m ON m.NAME = c.majorname\n" +
                "\tLEFT JOIN college cg ON cg.NAME = m.collegename \n" +
                "WHERE\n" +
                "\tsi.sex='男' and \n" +
                "\t1 = 1\n");
        sqlstr2.append(sb);
        String s = null;
        try {
            s = jdbcTemplate.queryForObject(sqlstr2.toString(), String.class);
        } catch (Exception e) {
            return null;
        }

        return s;
    }
    private String getFemaleCount(String sb) {
        StringBuilder sqlstr2 = new StringBuilder();
        sqlstr2.append("SELECT\n" +
                "\tcount(*) AS allcount\n" +
                "FROM\n" +
                "\tstudent s\n" +
                "\tLEFT JOIN stuinfo si ON si.id = s.id\n" +
                "\tLEFT JOIN class c ON s.classname = c.`name`\n" +
                "\tLEFT JOIN teacher t ON c.teacherno = t.`no`\n" +
                "\tLEFT JOIN major m ON m.NAME = c.majorname\n" +
                "\tLEFT JOIN college cg ON cg.NAME = m.collegename \n" +
                "WHERE\n" +
                "\tsi.sex='女'and \n" +
                "\t1 = 1\n");
        sqlstr2.append(sb);
        String s = null;
        try {
            s = jdbcTemplate.queryForObject(sqlstr2.toString(), String.class);
        } catch (Exception e) {
            return null;
        }
        return s;
    }


    public EmpVo getEmployment(ConditionDto conditionDto) {
        EmpVo empVo = new EmpVo();
        String college = conditionDto.getCollege();
        String year = conditionDto.getYear();
        String major = conditionDto.getMajor();
        String cls = conditionDto.getCls();
        String teacher = conditionDto.getTeacher();

        StringBuilder sb = new StringBuilder();

        if (!StringUtils.isEmpty(college)) {
            empVo.setCollege(college);
            sb.append(" and cg.name='" + college + "' ");
        }
        if (!StringUtils.isEmpty(year)) {
            empVo.setYear(year);
            sb.append(" and c.yearname='" + year + "' ");
        }
        if (!StringUtils.isEmpty(major)) {
            empVo.setMajor(major);
            sb.append(" and m.name ='" + major + "' ");
        }
        if (!StringUtils.isEmpty(cls)) {
            empVo.setCls(cls);
            sb.append(" and c.name ='" + cls + "' ");
        }
        if (!StringUtils.isEmpty(teacher)) {
            empVo.setTeacher(teacher);
            sb.append(" and t.name ='" + teacher + "' ");
        }

        StringBuilder sqlstr = new StringBuilder();
        sqlstr.append("SELECT\n" +
                "\t'总数' AS category,\n" +
                "\t0 as total,\n" +
                "\tsum( CASE WHEN mubiao is not null and mubiao !=''  THEN 1 ELSE 0 END) AS finish,\n" +
                "\tsum( CASE WHEN mubiao like '%考取选调生%' THEN 1 ELSE 0 END )AS 'xds',\n" +
                "\tsum( CASE WHEN mubiao like '%考取事业单位%' THEN 1 ELSE 0 END )AS 'sydw',\n" +
                "\tsum( CASE WHEN mubiao like '%考取公务员%' THEN 1 ELSE 0 END )AS 'gwy',\n" +
                "\tsum( CASE WHEN mubiao like '%参加三支一扶%' THEN 1 ELSE 0 END )AS 'szyf',\n" +
                "\tsum( CASE WHEN mubiao like '%参加西部计划%' THEN 1 ELSE 0 END )AS 'xbjh',\n" +
                "\tsum( CASE WHEN mubiao like '%参加特岗教师%' THEN 1 ELSE 0 END )AS 'tgjs',\n" +
                "\tsum( CASE WHEN mubiao like '%参军入伍%' THEN 1 ELSE 0 END )AS 'cjrw',\n" +
                "\tsum( CASE WHEN mubiao like '%大中型企业%' THEN 1 ELSE 0 END )AS 'dxqy',\n" +
                "\tsum( CASE WHEN fangxiang like '%升学%' THEN 1 ELSE 0 END )AS 'yjs'\n" +
                "FROM\n" +
                "\tstudent s\n" +
                "\tLEFT JOIN stuinfo si ON si.id = s.id\n" +
                "\tLEFT JOIN class c ON s.classname = c.`name`\n" +
                "\tLEFT JOIN job j ON s.id = j.studentid\n" +
                "\tLEFT JOIN teacher t ON c.teacherno = t.`no`\n" +
                "\tLEFT JOIN major m ON m.NAME = c.majorname\n" +
                "\tLEFT JOIN college cg ON cg.NAME = m.collegename \n" +
                "\twhere 1=1 and  j.`status`='已完成' \n");
        String sb2=sqlstr.toString().replace("'总数'","sex");
      
        //条件
        sqlstr.append(sb);

        sqlstr.append("\nUNION ALL\n");
        sqlstr.append(sb2);
        //条件
        sqlstr.append(sb);

        sqlstr.append("\nGROUP BY\n" +
                "\tsi.sex");

        //System.out.println(sqlstr.toString());
        Query query = entityManager.createNativeQuery(sqlstr.toString(), Emp.class);//后面的参数是因为entityManager得到的结果是Objetc类型的，因此要转换为需要的对象

        List list = query.getResultList();
        if (list.isEmpty())
            return null;
        for (Object o : list) {
            Emp e = new Emp();
            BeanUtils.copyProperties(o, e);
            if (e.getCategory().equals("总数")) {
                e.setTotal(getAllCount(sb.toString()));
                empVo.setAll(e);
            }

            if (e.getCategory().equals("女")) {
                e.setTotal(getFemaleCount(sb.toString()));
                System.out.println(getFemaleCount(sb.toString()));
                empVo.setFemale(e);
            }
            if (e.getCategory().equals("男")) {
                e.setTotal(getMaleCount(sb.toString()));
                System.out.println(getMaleCount(sb.toString())+"22222222222");
                empVo.setMale(e);
            }
        }

        return empVo;
    }

    public List<Tiaojian> getTiaojian(ConditionDto conditionDto) {
        String college = conditionDto.getCollege();
        String year = conditionDto.getYear();
        String major = conditionDto.getMajor();
        String cls = conditionDto.getCls();
        String teacher = conditionDto.getTeacher();

        StringBuilder sb = new StringBuilder();

        if (!StringUtils.isEmpty(college)) {
            sb.append(" and cg.name='" + college + "' ");
        }
        if (!StringUtils.isEmpty(year)) {
            sb.append(" and c.yearname='" + year + "' ");
        }
        if (!StringUtils.isEmpty(major)) {
            sb.append(" and m.name ='" + major + "' ");
        }
        if (!StringUtils.isEmpty(cls)) {
            sb.append(" and c.name ='" + cls + "' ");
        }
        if (!StringUtils.isEmpty(teacher)) {
            sb.append(" and t.name ='" + teacher + "' ");
        }

        StringBuilder sqlstr = new StringBuilder();
        sqlstr.append("SELECT\n" +
                "\tcg.`name` as clgname,\n" +
                "\tc.`name` as cname,\n" +
                "\tm.`name` as mname,\n" +
                "\ts.id as sid,\n" +
                "\tsi.`name` as sname,\n" +
                "\tj.yijingjubei,\n" +
                "\tj.mubiao,\n" +
                "\tj.jubeitiaojian,\n" +
                "\tj.buzu\n" +
                "FROM\n" +
                "\tstudent s\n" +
                "\tLEFT JOIN stuinfo si ON si.id = s.id\n" +
                "\tLEFT JOIN class c ON s.classname = c.`name`\n" +
                "\tLEFT JOIN job j ON s.id = j.studentid\n" +
                "\tLEFT JOIN teacher t ON c.teacherno = t.`no`\n" +
                "\tLEFT JOIN major m ON m.NAME = c.majorname\n" +
                "\tLEFT JOIN college cg ON cg.NAME = m.collegename \n" +
                "\twhere 1=1 and j.`status`='已完成'\n");
        //条件
        sqlstr.append(sb);
        System.out.println(sqlstr.toString());
        Query query = entityManager.createNativeQuery(sqlstr.toString(), Tiaojian.class);//后面的参数是因为entityManager得到的结果是Objetc类型的，因此要转换为需要的对象

        List list = query.getResultList();
        if (list.isEmpty())
            return null;
        /*for (Object o : list) {
            Tiaojian t = new Tiaojian();
            BeanUtils.copyProperties(o, t);
        }*/

        return list;
    }

    public List<Education> getEducation(ConditionDto conditionDto) {
        String college = conditionDto.getCollege();
        String year = conditionDto.getYear();
        String major = conditionDto.getMajor();
        String cls = conditionDto.getCls();
        String teacher = conditionDto.getTeacher();

        StringBuilder sb = new StringBuilder();

        if (!StringUtils.isEmpty(college)) {
            sb.append(" and cg.name='" + college + "' ");
        }
        if (!StringUtils.isEmpty(year)) {
            sb.append(" and c.yearname='" + year + "' ");
        }
        if (!StringUtils.isEmpty(major)) {
            sb.append(" and m.name ='" + major + "' ");
        }
        if (!StringUtils.isEmpty(cls)) {
            sb.append(" and c.name ='" + cls + "' ");
        }
        if (!StringUtils.isEmpty(teacher)) {
            sb.append(" and t.name ='" + teacher + "' ");
        }

        StringBuilder sqlstr = new StringBuilder();
        sqlstr.append("SELECT\n" +
                "\tcg.`name` as clgname,\n" +
                "\tm.`name` as mname,\n" +
                "\tc.`name` as cname,\n" +
                "\ts.id as sid,\n" +
                "\tsi.`name` as sname,\n" +
                "\tj.mubiao,\n" +
                "\tj.fangxiang\n" +
                "FROM\n" +
                "\tstudent s\n" +
                "\tLEFT JOIN stuinfo si ON si.id = s.id\n" +
                "\tLEFT JOIN class c ON s.classname = c.`name`\n" +
                "\tLEFT JOIN job j ON s.id = j.studentid\n" +
                "\tLEFT JOIN teacher t ON c.teacherno = t.`no`\n" +
                "\tLEFT JOIN major m ON m.NAME = c.majorname\n" +
                "\tLEFT JOIN college cg ON cg.NAME = m.collegename \n" +
                "\twhere 1=1 and  j.`status`='已完成' and j.fangxiang='升学'\n");
        //条件
        sqlstr.append(sb);
        System.out.println(sqlstr.toString());
        Query query = entityManager.createNativeQuery(sqlstr.toString(), Education.class);//后面的参数是因为entityManager得到的结果是Objetc类型的，因此要转换为需要的对象

        List list = query.getResultList();
        if (list.isEmpty())
            return null;

        return list;
    }
}
