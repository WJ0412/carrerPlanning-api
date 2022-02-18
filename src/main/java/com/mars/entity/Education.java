package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 升学统计
 */
@Data
@Entity
public class Education {
    @Id
    @Column
    private  String  sid;

    @Column
    private  String  clgname;

    @Column
    private  String  mname;

    @Column
    private  String  cname;

    @Column
    private  String  sname;
    @Column
    private String fangxiang;
    @Column
    private String mubiao;
}
