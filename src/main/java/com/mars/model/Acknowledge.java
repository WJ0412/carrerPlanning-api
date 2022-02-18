package com.mars.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Acknowledge {
    @TableId
    private String StudentId;
    private String body;
    private String mental;
    private String interest;
    private String disposition;
    private String advantage;
    private String insufficient;
    private String vision;
    private String status;
    private String note;
}
