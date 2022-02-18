package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "student")
public class Student {
    @Id
    @Column
    private String id;
    @Column
    private String pwd;
    @Column
    private String className;
    @Column
    private String xuezhi;
}
