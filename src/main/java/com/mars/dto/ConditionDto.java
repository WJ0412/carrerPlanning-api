package com.mars.dto;

import lombok.Data;

/**
 * 查询统计条件
 */
@Data
public class ConditionDto {
    private String college;
    private String major;
    private String cls;
    private String year;
    private String teacher;
    private String xueqi;
}
