package com.mars.service.student;

import com.mars.dao.*;

import com.mars.entity.*;
import com.mars.vo.XueYeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class XueyeService {
    @Autowired
    Xueye1Dao xueye1Dao;
    @Autowired
    Xueye2Dao xueye2Dao;
    @Autowired
    Xueye3Dao xueye3Dao;
    @Autowired
    Xueye4Dao xueye4Dao;
    @Autowired
    Xueye5Dao xueye5Dao;
    @Autowired
    Xueye6Dao xueye6Dao;
    @Autowired
    Xueye7Dao xueye7Dao;
    @Autowired
    Xueye8Dao xueye8Dao;

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    StudentDao studentDao;


    public XueYeVo getXueye(String sid, String tableName) {
        XueYeVo xueYeVo = new XueYeVo();
        Score score = null;
        switch (tableName) {
            case "1":
                Xueye1 xueye1 = xueye1Dao.findByStudentid(sid);
                if (xueye1 == null) {
                    return null;
                }
                score = scoreDao.findByStudentid(sid);
                BeanUtils.copyProperties(xueye1, xueYeVo);
                if (score != null) {
                    xueYeVo.setGenchengji(score.getGen1());
                    xueYeVo.setProchengji(score.getPro1());
                    xueYeVo.setInnchengji(score.getInn1());
                }
                return xueYeVo;
            case "2":
                Xueye2 xueye2 = xueye2Dao.findByStudentid(sid);
                if (xueye2 == null) {
                    return null;
                }
                score = scoreDao.findByStudentid(sid);
                BeanUtils.copyProperties(xueye2, xueYeVo);
                if (score != null) {
                    xueYeVo.setGenchengji(score.getGen2());
                    xueYeVo.setProchengji(score.getPro2());
                    xueYeVo.setInnchengji(score.getInn2());
                }
                return xueYeVo;
            case "3":
                Xueye3 xueye3 = xueye3Dao.findByStudentid(sid);
                if (xueye3 == null) {
                    return null;
                }
                score = scoreDao.findByStudentid(sid);
                BeanUtils.copyProperties(xueye3, xueYeVo);
                if (score != null) {
                    xueYeVo.setGenchengji(score.getGen3());
                    xueYeVo.setProchengji(score.getPro3());
                    xueYeVo.setInnchengji(score.getInn3());
                }
                return xueYeVo;
            case "4":
                Xueye4 xueye4 = xueye4Dao.findByStudentid(sid);
                if (xueye4 == null) {
                    return null;
                }
                BeanUtils.copyProperties(xueye4, xueYeVo);
                score = scoreDao.findByStudentid(sid);
                if (score != null) {
                    xueYeVo.setGenchengji(score.getGen4());
                    xueYeVo.setProchengji(score.getPro4());
                    xueYeVo.setInnchengji(score.getInn4());
                }
                return xueYeVo;
            case "5":
                Xueye5 xueye5 = xueye5Dao.findByStudentid(sid);
                if (xueye5 == null) {
                    return null;
                }
                score = scoreDao.findByStudentid(sid);
                BeanUtils.copyProperties(xueye5, xueYeVo);
                if (score != null) {
                    xueYeVo.setGenchengji(score.getGen5());
                    xueYeVo.setProchengji(score.getPro5());
                    xueYeVo.setInnchengji(score.getInn5());
                }
                return xueYeVo;
            case "6":
                Xueye6 xueye6 = xueye6Dao.findByStudentid(sid);
                if (xueye6 == null) {
                    return null;
                }
                score = scoreDao.findByStudentid(sid);
                BeanUtils.copyProperties(xueye6, xueYeVo);
                if (score != null) {
                    xueYeVo.setGenchengji(score.getGen6());
                    xueYeVo.setProchengji(score.getPro6());
                    xueYeVo.setInnchengji(score.getInn6());
                }
                return xueYeVo;
            case "7":
                Xueye7 xueye7 = xueye7Dao.findByStudentid(sid);
                if (xueye7 == null) {
                    return null;
                }
                score = scoreDao.findByStudentid(sid);
                BeanUtils.copyProperties(xueye7, xueYeVo);
                if (score != null) {
                    xueYeVo.setGenchengji(score.getGen7());
                    xueYeVo.setProchengji(score.getPro7());
                    xueYeVo.setInnchengji(score.getInn7());
                }
                return xueYeVo;
            case "8":
                Xueye8 xueye8 = xueye8Dao.findByStudentid(sid);
                if (xueye8 == null) {
                    return null;
                }
                score = scoreDao.findByStudentid(sid);
                BeanUtils.copyProperties(xueye8, xueYeVo);
                if (score != null) {
                    xueYeVo.setGenchengji(score.getGen8());
                    xueYeVo.setProchengji(score.getPro8());
                    xueYeVo.setInnchengji(score.getInn8());
                }
                return xueYeVo;
        }
        return null;
    }

    @Transactional
    public boolean addXueye(XueYeVo xueYeVo, String tableName) {
        String sid = xueYeVo.getStudentid();
        if (scoreDao.findByStudentid(sid) == null) {
            scoreDao.addStudent(sid);
        }

        switch (tableName) {
            case "1":
                System.out.println(xueYeVo);
                Xueye1 xueye1 = new Xueye1();
                //转换前的类，转换后的类
                BeanUtils.copyProperties(xueYeVo, xueye1);

                scoreDao.addScore1(xueYeVo.getGenchengji(), xueYeVo.getInnchengji(), xueYeVo.getProchengji(), xueYeVo.getStudentid());
                return xueye1Dao.save(xueye1) != null;
            case "2":
                Xueye2 xueye2 = new Xueye2();
                BeanUtils.copyProperties(xueYeVo, xueye2);
                scoreDao.addScore2(xueYeVo.getGenchengji(), xueYeVo.getInnchengji(), xueYeVo.getProchengji(), xueYeVo.getStudentid());
                return xueye2Dao.save(xueye2) != null;
            case "3":
                Xueye3 xueye3 = new Xueye3();
                BeanUtils.copyProperties(xueYeVo, xueye3);
                scoreDao.addScore3(xueYeVo.getGenchengji(), xueYeVo.getInnchengji(), xueYeVo.getProchengji(), xueYeVo.getStudentid());
                return xueye3Dao.save(xueye3) != null;
            case "4":
                Xueye4 xueye4 = new Xueye4();
                BeanUtils.copyProperties(xueYeVo, xueye4);
                scoreDao.addScore4(xueYeVo.getGenchengji(), xueYeVo.getInnchengji(), xueYeVo.getProchengji(), xueYeVo.getStudentid());
                return xueye4Dao.save(xueye4) != null;
            case "5":
                Xueye5 xueye5 = new Xueye5();
                BeanUtils.copyProperties(xueYeVo, xueye5);
                scoreDao.addScore5(xueYeVo.getGenchengji(), xueYeVo.getInnchengji(), xueYeVo.getProchengji(), xueYeVo.getStudentid());
                return xueye5Dao.save(xueye5) != null;
            case "6":
                Xueye6 xueye6 = new Xueye6();
                BeanUtils.copyProperties(xueYeVo, xueye6);
                scoreDao.addScore6(xueYeVo.getGenchengji(), xueYeVo.getInnchengji(), xueYeVo.getProchengji(), xueYeVo.getStudentid());
                return xueye6Dao.save(xueye6) != null;
            case "7":
                Xueye7 xueye7 = new Xueye7();
                BeanUtils.copyProperties(xueYeVo, xueye7);
                scoreDao.addScore7(xueYeVo.getGenchengji(), xueYeVo.getInnchengji(), xueYeVo.getProchengji(), xueYeVo.getStudentid());
                return xueye7Dao.save(xueye7) != null;
            case "8":
                Xueye8 xueye8 = new Xueye8();
                BeanUtils.copyProperties(xueYeVo, xueye8);
                scoreDao.addScore8(xueYeVo.getGenchengji(), xueYeVo.getInnchengji(), xueYeVo.getProchengji(), xueYeVo.getStudentid());
                return xueye8Dao.save(xueye8) != null;
        }
        return false;
    }

    public Integer getXuezhi(String sid) {
        String xuezhi = studentDao.findXuezhi(sid);
        try {
            int i = Integer.parseInt(xuezhi);
            return i;
        } catch (Exception e) {
            return 4;
        }
    }
}
