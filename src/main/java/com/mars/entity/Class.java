package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "class")
public class Class {
    @Id
    @Column
    private String name;
    @Column
    private String teacherNo;
    @Column
    private String majorName;
    @Column
    private String yearName;
}
