package com.mars.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "family")
@IdClass(FamilyPk.class)
public class Family  {
    @Id
    @Column
    private String name;
    @Id
    @Column
    private String hiscall;
    @Column
    private String tel;
    @Column
    private String education;
    @Column
    private String work;
    @Column
    private String wechat;

    @Column
    private String studentid;
    @Column
    private String status;

    @Column
    private String note;
}
