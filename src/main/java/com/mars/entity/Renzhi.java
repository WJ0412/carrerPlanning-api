package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "renzhi")
public class Renzhi {
    @Column
    private String shenti;
    @Column
    private String xinli;
    @Column
    private String xingqu;
    @Column
    private String xingge;
    @Column
    private String youshi;
    @Column
    private String buzu;

    @Column
    private String fazhan;
    @Id
    @Column
    private String studentid;

    @Column
    private String status;


    @Column
    private String note;


}
