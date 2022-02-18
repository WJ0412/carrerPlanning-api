package com.mars.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Admin {
    @TableId(value = "name")
    private String name;
    private String password;
    private String role;
}
