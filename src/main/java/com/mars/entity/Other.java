package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Entity(name = "other")
@IdClass(OtherPk.class)

public class Other{
    @Id
    @Column
    private String hiscall;
    @Id
    @Column
    private String name;
    @Column
    private String tel;
    @Column
    private String work;
    @Column
    private String education;
    @Column
    private String wechat;
    @Column
    private String studentid;
    @Column
    private String status;

    @Column
    private String note;

}
