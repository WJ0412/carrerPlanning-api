package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "major")
public class Major {
    @Id
    @Column
    private String name;
    @Column
    private String collegeName;
    @Column
    private String xuhao;
}
