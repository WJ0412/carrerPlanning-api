package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "options")
public class Options {
    @Id
    @Column
    private String id;
    @Column
    private String bh_name;
    @Column
    private String name;
    @Column
    private String bh_value;
    @Column
    private String value;

}
