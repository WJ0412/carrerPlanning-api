package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "score")
public class Score {
    @Id
    @Column
    private String studentid;
    @Column
    private String pro1;
    @Column
    private String gen1;
    @Column
    private String inn1;
    @Column
    private String teacher1;
    @Column
    private String pro2;
    @Column
    private String gen2;
    @Column
    private String inn2;
    @Column
    private String teacher2;
    @Column
    private String pro3;
    @Column
    private String gen3;
    @Column
    private String inn3;
    @Column
    private String teacher3;
    @Column
    private String pro4;
    @Column
    private String gen4;
    @Column
    private String inn4;
    @Column
    private String teacher4;
    @Column
    private String pro5;
    @Column
    private String gen5;
    @Column
    private String inn5;
    @Column
    private String teacher5;
    @Column
    private String pro6;
    @Column
    private String gen6;
    @Column
    private String inn6;
    @Column
    private String teacher6;
    @Column
    private String pro7;
    @Column
    private String gen7;
    @Column
    private String inn7;
    @Column
    private String teacher7;
    @Column
    private String pro8;
    @Column
    private String gen8;
    @Column
    private String inn8;
    @Column
    private String teacher8;

}
