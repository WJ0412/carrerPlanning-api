package com.mars.vo;

import com.mars.entity.Emp;
import lombok.Data;

@Data
public class EmpVo {
    private Emp all;
    private Emp male;
    private Emp female;
    private String college;
    private String major;
    private String cls;
    private String teacher;
    private String year;
}
