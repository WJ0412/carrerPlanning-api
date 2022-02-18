package com.mars.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Business {
    @TableId
    private String StudentId;
    private String target;
    private String requirement;
    private String qualified;
    private String insufficient;
    private String measure;
    private String status;
    private String note;
}
