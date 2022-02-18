package com.mars.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Xueye {
    @Id
    @Column
    private String studentid;
    @Column
    private String target;
    @Column
    private String protarget;
    @Column
    private String gentarget;
    @Column
    private String inntarget;
    @Column
    private String proshijian;
    @Column
    private String genshijian;
    @Column
    private String innshijian;
    @Column
    private String profangfa;
    @Column
    private String genfangfa;
    @Column
    private String innfangfa;
    @Column
    private String profenxi;
    @Column
    private String genfenxi;
    @Column
    private String innfenxi;
    @Column
    private String status;
    @Column
    private String note;
    @Column
    private String procuoshi;
    @Column
    private String gencuoshi;
    @Column
    private String inncuoshi;


}
