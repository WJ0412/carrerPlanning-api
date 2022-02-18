package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "teacher")
public class Teacher {
    @Id
    @Column
    private String no;
    @Column
    private String name;
    @Column
    private String pwd;
    @Column
    private String tel;
    @Column
    private String collegeName;
}
