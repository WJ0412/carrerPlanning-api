package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "admin")
public class Admin {
    @Id
    @Column
    private String name;
    @Column
    private String pwd;

}
