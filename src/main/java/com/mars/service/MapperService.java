package com.mars.service;

import com.mars.dao.MapperDao;
import com.mars.dao.StudentDao;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MapperService {

    @Autowired
    MapperDao dao;
    @Autowired
    StudentDao studentDao;

    /**
     * info表
     */

    public ResponseData findInfoById(String id) {
        if (id.trim().equals(""))
            return ResponseData.buildError("未获取到学号");
        Map<String, String> infos = dao.findInfoById(id);
        if (infos.isEmpty())
            return ResponseData.buildError("该学生暂未填写任何数据");
        return ResponseData.buildSuccess(infos);
    }

    public ResponseData completeInfo(String id) {
        int count = dao.completeInfo(id);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData failInfo(String id, String note) {
        int count = dao.failInfo(id, note);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }

    /**
     * family表
     */

    public ResponseData findStudentsByFamily(String className, String status, String name, String teacherNo) {
        List<Map<String, String>> students = null;
        if (className.equals("")) {
            if (status.equals("")) {
                if (name.equals("")) {
                    students = dao.findFamilyByTeacherNo(teacherNo);
                } else {
                    // 根据姓名查询
                    students = dao.findFamilyByName(name, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    // 根据状态码查询
                    students = dao.findFamilyByStatus(status, teacherNo);
                } else {
                    // 根据状态码和姓名查询
                    students = dao.findFamilyByStatusAndName(status, name, teacherNo);
                }
            }
        } else {
            if (status.equals("")) {
                if (name.equals("")) {
                    // 根据班级名称查询
                    students = dao.findFamilyByClassName(className, teacherNo);
                } else {
                    // 根据姓名和班级名称查询
                    students = dao.findFamilyByNameAndClassName(name, className, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    if (status.equals("未提交")){
                        students = dao.findFamilyByWeiAndClassName(className,teacherNo);
                    }else{
                        //根据状态码和班级查询
                        students = dao.findFamilyByStatusAndClassName(status, className, teacherNo);
                    }
                } else {
                    //根据名称 状态码  班级 查询
                    students = dao.findFamilyByNameStatusAndClassName(name, status, className, teacherNo);
                }
            }
        }
        if (students.isEmpty())
            return ResponseData.buildError("无符合条件的学生");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findFamilyByTeacherNo(String teacherNo) {
        List<Map<String, String>> students = dao.findFamilyByTeacherNo(teacherNo);
        if (students.isEmpty())
            return ResponseData.buildError("您的学生都未填写家庭成员信息");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findFamilyById(String id) {
        if (id.trim().equals(""))
            return ResponseData.buildError("未获取到学号");
        List<Map<String, String>> family = dao.findFamilyById(id);
        if (family.isEmpty())
            return ResponseData.buildError("该学生暂未填写任何数据");
        return ResponseData.buildSuccess(family);
    }

    public ResponseData completeFamily(String id) {
        int count = dao.completeFamily(id);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData failFamily(String id, String note) {
        int count = dao.failFamily(id, note);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }


    /**
     * other表
     */

    public ResponseData findStudentsByOther(String className, String status, String name, String teacherNo) {
        List<Map<String, String>> students = null;
        if (className.equals("")) {
            if (status.equals("")) {
                if (name.equals("")) {
                    students = dao.findOtherByTeacherNo(teacherNo);
                } else {
                    // 根据姓名查询
                    students = dao.findOtherByName(name, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    // 根据状态码查询
                    students = dao.findOtherByStatus(status, teacherNo);
                } else {
                    // 根据状态码和姓名查询
                    students = dao.findOtherByStatusAndName(status, name, teacherNo);
                }
            }
        } else {
            if (status.equals("")) {
                if (name.equals("")) {
                    // 根据班级名称查询
                    students = dao.findOtherByClassName(className, teacherNo);
                } else {
                    // 根据姓名和班级名称查询
                    students = dao.findOtherByNameAndClassName(name, className, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    if (name.equals("")) {
                        if (status.equals("未提交")){
                            students = dao.findOtherByWeiAndClassName(className,teacherNo);
                        }else{
                            //根据状态码和班级查询
                            students = dao.findOtherByStatusAndClassName(status, className, teacherNo);
                        }
                    }
                } else {
                    //根据名称 状态码  班级 查询
                    students = dao.findOtherByNameStatusAndClassName(name, status, className, teacherNo);
                }
            }
        }
        if (students.isEmpty())
            return ResponseData.buildError("无符合条件的学生");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findOtherByTeacherNo(String teacherNo) {
        List<Map<String, String>> students = dao.findOtherByTeacherNo(teacherNo);
        if (students.isEmpty())
            return ResponseData.buildError("您的学生都未填写其他亲属信息");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findOtherById(String id) {
        if (id.trim().equals(""))
            return ResponseData.buildError("未获取到学号");
        List<Map<String, String>> family = dao.findOtherById(id);
        if (family.isEmpty())
            return ResponseData.buildError("该学生暂未填写任何数据");
        return ResponseData.buildSuccess(family);
    }

    public ResponseData completeOther(String id) {
        int count = dao.completeOther(id);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData failOther(String id, String note) {
        int count = dao.failOther(id, note);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }


    /**
     * 认知
     */

    public ResponseData findStudentsByRenZhi(String className, String status, String name, String teacherNo) {
        List<Map<String, String>> students = null;
        if (className.equals("")) {
            if (status.equals("")) {
                if (name.equals("")) {
                    students = dao.findRenZhiByTeacherNo(teacherNo);
                } else {
                    // 根据姓名查询
                    students = dao.findRenZhiByName(name, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    // 根据状态码查询
                    students = dao.findRenZhiByStatus(status, teacherNo);
                } else {
                    // 根据状态码和姓名查询
                    students = dao.findRenZhiByStatusAndName(status, name, teacherNo);
                }
            }
        } else {
            if (status.equals("")) {
                if (name.equals("")) {
                    // 根据班级名称查询
                    students = dao.findRenZhiByClassName(className, teacherNo);
                } else {
                    // 根据姓名和班级名称查询
                    students = dao.findRenZhiByNameAndClassName(name, className, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    if (status.equals("未提交")){
                        students = dao.findRenZhiByWeiAndClassName(className,teacherNo);
                    }else{
                        //根据状态码和班级查询
                        students = dao.findRenZhiByStatusAndClassName(status, className, teacherNo);
                    }
                } else {
                    //根据名称 状态码  班级 查询
                    students = dao.findRenZhiByNameStatusAndClassName(name, status, className, teacherNo);
                }
            }
        }
        if (students.isEmpty())
            return ResponseData.buildError("无符合条件的学生");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findRenZhiByTeacherNo(String teacherNo) {
        List<Map<String, String>> students = dao.findRenZhiByTeacherNo(teacherNo);
        if (students.isEmpty())
            return ResponseData.buildError("您的学生都未填写自我认知信息");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findRenZhiById(String id) {
        if (id.trim().equals(""))
            return ResponseData.buildError("未获取到学号");
        List<Map<String, String>> family = dao.findRenZhiById(id);
        if (family.isEmpty())
            return ResponseData.buildError("该学生暂未填写任何数据");
        return ResponseData.buildSuccess(family);
    }


    public ResponseData completeRenZhi(String id) {
        int count = dao.completeRenZhi(id);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData failRenZhi(String id, String note) {
        int count = dao.failRenZhi(id, note);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }

    /**
     * 就业规划
     */

    public ResponseData findStudentsByJob(String className, String status, String name, String teacherNo) {
        List<Map<String, String>> students = null;
        if (className.equals("")) {
            if (status.equals("")) {
                if (name.equals("")) {
                    students = dao.findJobByTeacherNo(teacherNo);
                } else {
                    // 根据姓名查询
                    students = dao.findJobByName(name, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    // 根据状态码查询
                    students = dao.findJobByStatus(status, teacherNo);
                } else {
                    // 根据状态码和姓名查询
                    students = dao.findJobByStatusAndName(status, name, teacherNo);
                }
            }
        } else {
            if (status.equals("")) {
                if (name.equals("")) {
                    // 根据班级名称查询
                    students = dao.findJobByClassName(className, teacherNo);
                } else {
                    // 根据姓名和班级名称查询
                    students = dao.findJobByNameAndClassName(name, className, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    if (status.equals("未提交")){
                        students = dao.findJobByWeiAndClassName(className,teacherNo);
                    }else{
                        //根据状态码和班级查询
                        students = dao.findJobByStatusAndClassName(status, className, teacherNo);
                    }
                } else {
                    //根据名称 状态码  班级 查询
                    students = dao.findJobByNameStatusAndClassName(name, status, className, teacherNo);
                }
            }
        }
        if (students.isEmpty())
            return ResponseData.buildError("无符合条件的学生");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findJobByTeacherNo(String teacherNo) {
        List<Map<String, String>> students = dao.findJobByTeacherNo(teacherNo);
        if (students.isEmpty())
            return ResponseData.buildError("您的学生都未填写就业规划信息");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findJobById(String id) {
        if (id.trim().equals(""))
            return ResponseData.buildError("未获取到学号");
        List<Map<String, String>> family = dao.findJobById(id);
        if (family.isEmpty())
            return ResponseData.buildError("该学生暂未填写任何数据");
        return ResponseData.buildSuccess(family);
    }


    public ResponseData completeJob(String id) {
        int count = dao.completeJob(id);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData failJob(String id, String note) {
        int count = dao.failJob(id, note);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }


    /**
     * 创业规划
     */

    public ResponseData findStudentsByBusiness(String className, String status, String name, String teacherNo) {
        List<Map<String, String>> students = null;
        if (className.equals("")) {
            if (status.equals("")) {
                if (name.equals("")) {
                    students = dao.findBusinessByTeacherNo(teacherNo);
                } else {
                    // 根据姓名查询
                    students = dao.findBusinessByName(name, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    // 根据状态码查询
                    students = dao.findBusinessByStatus(status, teacherNo);
                } else {
                    // 根据状态码和姓名查询
                    students = dao.findBusinessByStatusAndName(status, name, teacherNo);
                }
            }
        } else {
            if (status.equals("")) {
                if (name.equals("")) {
                    // 根据班级名称查询
                    students = dao.findBusinessByClassName(className, teacherNo);
                } else {
                    // 根据姓名和班级名称查询
                    students = dao.findBusinessByNameAndClassName(name, className, teacherNo);
                }
            } else {
                if (name.equals("")) {
                    if (status.equals("未提交")){
                        students = dao.findBusinessByWeiAndClassName(className,teacherNo);
                    }else{
                        //根据状态码和班级查询
                        students = dao.findBusinessByStatusAndClassName(status, className, teacherNo);
                    }
                } else {
                    //根据名称 状态码  班级 查询
                    students = dao.findBusinessByNameStatusAndClassName(name, status, className, teacherNo);
                }
            }
        }
        if (students.isEmpty())
            return ResponseData.buildError("无符合条件的学生");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findBusinessByTeacherNo(String teacherNo) {
        List<Map<String, String>> students = dao.findBusinessByTeacherNo(teacherNo);
        if (students.isEmpty())
            return ResponseData.buildError("您的学生都未填写创业规划信息");
        return ResponseData.buildSuccess(students);
    }

    public ResponseData findBusinessById(String id) {
        if (id.trim().equals(""))
            return ResponseData.buildError("未获取到学号");
        List<Map<String, String>> family = dao.findBusinessById(id);
        if (family.isEmpty())
            return ResponseData.buildError("该学生暂未填写任何数据");
        return ResponseData.buildSuccess(family);
    }


    public ResponseData completeBusiness(String id) {
        int count = dao.completeBusiness(id);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData failBusiness(String id, String note) {
        int count = dao.failBusiness(id, note);
        if (count == 0)
            return ResponseData.buildError("服务器异常,请及时联系管理人员");
        return ResponseData.buildSuccess(count);
    }


    /**
     * 学业规划
     */




    public ResponseData findNameAndIdByClassNameAndStatus(String className,String status,String xueqi) {
        List<Map<String, String>> students = null;
        if (xueqi.equals("第一学期")){
            if (status.equals("未提交"))
                students = dao.findOneByClassNameAndStatus(className);
            else
                students = dao.findOneByClassNameAndStatus(className, status);
        }
        if (xueqi.equals("第二学期")){
            if (status.equals("未提交"))
                students = dao.findSecondByClassNameAndStatus(className);
            else
                students = dao.findSecondByClassNameAndStatus(className, status);
        }
        if (xueqi.equals("第三学期")){
            if (status.equals("未提交"))
                students = dao.findThirdByClassNameAndStatus(className);
            else
                students = dao.findThirdByClassNameAndStatus(className, status);
        }
        if (xueqi.equals("第四学期")){
            if (status.equals("未提交"))
                students = dao.findFourthByClassNameAndStatus(className);
            else
                students = dao.findFourthByClassNameAndStatus(className, status);
        }
        if (xueqi.equals("第五学期")){
            if (status.equals("未提交"))
                students = dao.findFifthByClassNameAndStatus(className);
            else
                students = dao.findFifthByClassNameAndStatus(className, status);
        }
        if (xueqi.equals("第六学期")){
            if (status.equals("未提交"))
                students = dao.findSixthByClassNameAndStatus(className);
            else
                students = dao.findSixthByClassNameAndStatus(className, status);
        }
        if (xueqi.equals("第七学期")){
            if (status.equals("未提交"))
                students = dao.findSeventhByClassNameAndStatus(className);
            else
                students = dao.findSeventhByClassNameAndStatus(className, status);
        }
        if (xueqi.equals("第八学期")){
            if (status.equals("未提交"))
                students = dao.findEighthByClassNameAndStatus(className);
            else
                students = dao.findEighthByClassNameAndStatus(className, status);
        }
        if (students.isEmpty())
            return ResponseData.buildError("当前条件下暂无学生");
        return ResponseData.buildSuccess(students);
    }


    public ResponseData findSuccessById(String studentId, String xueqi) {
        Map<String, String> success = null;
        if (xueqi.equals("第一学期")){
            success = dao.findFirstById(studentId);
        }
        if (xueqi.equals("第二学期")){
            success = dao.findSecondById(studentId);
        }
        if (xueqi.equals("第三学期")){
            success = dao.findThirdById(studentId);
        }
        if (xueqi.equals("第四学期")){
            success = dao.findFourthById(studentId);
        }
        if (xueqi.equals("第五学期")){
            success = dao.findFifthById(studentId);
        }
        if (xueqi.equals("第六学期")){
            success = dao.findSixthById(studentId);
        }
        if (xueqi.equals("第七学期")){
            success = dao.findSeventhById(studentId);
        }
        if (xueqi.equals("第八学期")){
            success = dao.findEighthById(studentId);
        }

        if (success.isEmpty())
            return ResponseData.buildError("当前学生暂未填写");
        return ResponseData.buildSuccess(success);
    }

    public ResponseData failSuccessById(String studentId, String xueqi,String note) {
        int count = 0;
        if (xueqi.equals("第一学期")){
            count = dao.failFirstById(studentId,note);
        }
        if (xueqi.equals("第二学期")){
            count = dao.failSecondById(studentId,note);
        }
        if (xueqi.equals("第三学期")){
            count = dao.failThirdById(studentId,note);
        }
        if (xueqi.equals("第四学期")){
            count = dao.failFourthById(studentId,note);
        }
        if (xueqi.equals("第五学期")){
            count = dao.failFifthById(studentId,note);
        }
        if (xueqi.equals("第六学期")){
            count = dao.failSixthById(studentId,note);
        }
        if (xueqi.equals("第七学期")){
            count = dao.failSeventhById(studentId,note);
        }
        if (xueqi.equals("第八学期")){
            count = dao.failEighthById(studentId,note);
        }
        if (count==0)
            return ResponseData.buildError("评分失败,请联系服务器管理员");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData completeSuccess(String studentId, String xueqi, String score) {
        int count = 0;
        if (xueqi.equals("第一学期")){
            count += dao.completeFirstById(studentId);
            count += dao.scoreFirstById(studentId,score);
        }
        if (xueqi.equals("第二学期")){
            count += dao.completeSecondById(studentId);
            count += dao.scoreSecondById(studentId,score);
        }
        if (xueqi.equals("第三学期")){
            count += dao.completeThirdById(studentId);
            count += dao.scoreThirdById(studentId,score);
        }
        if (xueqi.equals("第四学期")){
            count += dao.completeFourthById(studentId);
            count += dao.scoreFourthById(studentId,score);
        }
        if (xueqi.equals("第五学期")){
            count += dao.completeFifthById(studentId);
            count += dao.scoreFifthById(studentId,score);
        }
        if (xueqi.equals("第六学期")){
            count += dao.completeSixthById(studentId);
            count += dao.scoreSixthById(studentId,score);
        }
        if (xueqi.equals("第七学期")){
            count += dao.completeSeventhById(studentId);
            count += dao.scoreSeventhById(studentId,score);
        }
        if (xueqi.equals("第八学期")){
            count += dao.completeEighthById(studentId);
            count += dao.scoreEighthById(studentId,score);
        }
        if (count!=2)
            return ResponseData.buildError("评分失败,请联系服务器管理员");
        return ResponseData.buildSuccess(count);
    }


    // 事业规划

    public ResponseData findNameAndIdByClassNameAndStatus2(String className,String status,String xueqi) {
        List<Map<String, String>> students = null;
        if (xueqi.equals("事业规划")){
            if (status.equals("未提交"))
                students = dao.findShiye1ByClassNameAndStatus(className);
            else
                students = dao.findShiye1ByClassNameAndStatus(className, status);
        }
        if (xueqi.equals("事业规划修订")){
            if (status.equals("未提交"))
                students = dao.findShiye2ByClassNameAndStatus(className);
            else
                students = dao.findShiye2ByClassNameAndStatus(className, status);
        }
        if (students.isEmpty())
            return ResponseData.buildError("当前条件下暂无学生");
        return ResponseData.buildSuccess(students);
    }


    public ResponseData findShiyeById(String studentId, String xueqi) {
        Map<String, String> success = null;
        if (xueqi.equals("事业规划")){
            success = dao.findShiye1ById(studentId);
        }
        if (xueqi.equals("事业规划修订")){
            success = dao.findShiye2ById(studentId);
        }
        if (success.isEmpty())
            return ResponseData.buildError("当前学生暂未填写");
        return ResponseData.buildSuccess(success);
    }

    public ResponseData failShiyeById(String studentId, String xueqi,String note) {
        int count = 0;
        if (xueqi.equals("事业规划")){
            count = dao.failShiye1ById(studentId,note);
        }
        if (xueqi.equals("事业规划修订")){
            count = dao.failShiye2ById(studentId,note);
        }
        if (count==0)
            return ResponseData.buildError("评分失败,请联系服务器管理员");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData completeShiye(String studentId, String xueqi) {
        int count = 0;
        if (xueqi.equals("事业规划")){
            count = dao.completeShiye1ById(studentId);
        }
        if (xueqi.equals("事业规划修订")){
            count = dao.completeShiye2ById(studentId);
        }
        if (count==2)
            return ResponseData.buildError("评分失败,请联系服务器管理员");
        return ResponseData.buildSuccess(count);
    }


    public ResponseData resetStuPwd(String id) {
        int count = 0;
        try {
            count = dao.resetStuPwd(id);
        }catch (Exception e){
            return ResponseData.buildError("SQL异常");
        }
        if (count==0)
            return ResponseData.buildError("重置密码失败");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData findAllAdmins() {
        List<Map<String, String>> allAdmins = dao.findAllAdmins();
        if (allAdmins.isEmpty())
            return ResponseData.buildError("暂无管理员");
        return ResponseData.buildSuccess(allAdmins);
    }

    public ResponseData deleteAdmin(String id) {
        int count = dao.deleteAdmin(id);
        if (count==0)
            return ResponseData.buildError("删除失败");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData updateAdmin(String newId, String pwd, String role, String oldId) {
        if (newId.trim().equals("")||pwd.trim().equals("")||role.trim().equals("")||oldId.trim().equals(""))
            return ResponseData.buildError("必填项不可为空");
        List<Map<String, String>> allAdmins = dao.findAllAdmins();
        for (Map<String, String> allAdmin : allAdmins) {
            if (allAdmin.get("name").equals(newId)&&!oldId.equals(newId))
                return ResponseData.buildError("重复账号");
        }
        int count = dao.updateAdmin(newId, pwd, role, oldId);
        if (count==0)
            return ResponseData.buildError("修改失败");
        return ResponseData.buildSuccess(count);
    }

    public ResponseData addAdmin(String name, String pwd, String role) {
        List<Map<String, String>> allAdmins = dao.findAllAdmins();
        for (Map<String, String> allAdmin : allAdmins) {
            if (allAdmin.get("name").equals(name))
                return ResponseData.buildError("重复账号");
        }
        if (name.trim().equals("")||pwd.trim().equals("")||role.trim().equals(""))
            return ResponseData.buildError("必填项不可为空");
        int count = dao.addAdmin(name, pwd, role);
        if (count==0)
            return ResponseData.buildError("新增失败");
        return ResponseData.buildSuccess(count);
    }
}