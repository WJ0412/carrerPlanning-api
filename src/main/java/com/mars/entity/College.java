package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * college实体类
 * @author awen
 */
@Data
@Entity(name = "college")
public class College {
    @Id
    @Column
    private String name;
    @Column
    private String xuhao;
}
