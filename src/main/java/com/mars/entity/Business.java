package com.mars.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity(name = "business")
public class Business {
    @Id
    @Column
    private String studentid;
    @Column
    private String mubiao;
    @Column
    private String jubeitiaojian;
    @Column
    private String yijingjubei;
    @Column
    private String buzu;
    @Column
    private String cuoshi;
    @Column
    private String note;
    @Column
    private String status;


}
