package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "stuInfo")
public class StuInfo {
    @Id
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private String sex;
    @Column
    private String birth;
    @Column
    private String political;
    @Column
    private String nativePlace;
    @Column
    private String tel;
    @Column
    private String wechat;
    @Column
    private String qq;
    @Column
    private String code;
    @Column
    private String address;
    @Column
    private String status;
    @Column
    private String note;
}
