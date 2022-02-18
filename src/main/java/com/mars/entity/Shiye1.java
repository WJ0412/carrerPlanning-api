package com.mars.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "shiye1")
public class Shiye1 {
    @Id
    @Column
    private String studentid;
    @Column
    private String mubiao;
    @Column
    private String jubeisuzhi;
    @Column
    private String buzu;
    @Column
    private String cuoshi;
    @Column
    private String note;
    @Column
    private String status;

}
