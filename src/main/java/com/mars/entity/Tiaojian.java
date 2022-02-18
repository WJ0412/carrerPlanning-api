package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * 具备条件统计
 */
@Data
@Entity
public class Tiaojian {
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
    private  String  yijingjubei;

    @Column
    private  String  mubiao;

    @Column
    private  String  jubeitiaojian;

    @Column
    private  String  buzu;
}
