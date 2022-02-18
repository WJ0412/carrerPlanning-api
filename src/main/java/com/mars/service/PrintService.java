package com.mars.service;

import com.mars.dao.PrintDao;
import com.mars.utils.*;
import com.mars.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class PrintService {

    @Autowired
    PrintDao dao;

    public ResponseData printInfo(String value, String term, String id, HttpServletRequest request) {
    
        String fileUrl="";
        if (value.equals("学业规划")) {
            if (term.equals("第一学期")) {
                fileUrl = new XueYeHandler().createXueYe(request,dao.findXuYe1ByStudentid(id));
            }
            if (term.equals("第二学期")) {
                fileUrl =new XueYe2Handler().createXueYe(request,dao.findXuYe2ByStudentid(id));
            }
            if (term.equals("第三学期")) {
                fileUrl =new XueYe3Handler().createXueYe(request,dao.findXuYe3ByStudentid(id));
            }
            if (term.equals("第四学期")) {
                fileUrl = new XueYe4Handler().createXueYe(request,dao.findXuYe4ByStudentid(id));
            }
            if (term.equals("第五学期")) {
                fileUrl = new XueYe5Handler().createXueYe(request,dao.findXuYe5ByStudentid(id));
            }
            if (term.equals("第六学期")) {
                fileUrl = new XueYe6Handler().createXueYe(request,dao.findXuYe6ByStudentid(id));
            }
            if (term.equals("第七学期")) {
                fileUrl =new XueYe7Handler().createXueYe(request,dao.findXuYe7ByStudentid(id));
            }
            if (term.equals("第八学期")) {
                fileUrl =new XueYe8Handler().createXueYe(request,dao.findXuYe8ByStudentid(id));
            }
        }
        if (value.equals("基本信息")) {
            InfoHandler infoHandler = new InfoHandler();
            Map<String, String> infoMap = dao.findInfoById(id);
            List<Map<String, String>> family = dao.findFamilyById(id);
            List<Map<String, String>> other = dao.findOtherById(id);
            fileUrl =infoHandler.create(request,infoMap,family,other);
        }
        if (value.equals("自我认知")) {
            fileUrl =new RenZhiHandler().createRenZhi(request,dao.findRenzhiByStudentid(id));
        }
        if (value.equals("就业规划")) {
            fileUrl =new JobHandler().createJob(request,dao.findJobByStudentid(id));
        }
        if (value.equals("创业规划")) {
            fileUrl =new BusinessHandler().createBusiness(request,dao.findBusinessByStudentid(id));
        }
        if (value.equals("事业规划")) {
            fileUrl =new ShiYe1Handler().createShiYe(request,dao.findShiYe1ByStudentid(id));
        }
        if (value.equals("事业规划修订")) {
            fileUrl =new ShiYe2Handler().createShiYe(request,dao.findShiYe2ByStudentid(id));
        }

        return ResponseData.buildSuccess(fileUrl);
    }
}
