package com.mars.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Emp {
    @Id
    private String category;//男女总数分类
    @Column
    private String total;
    @Column
    private String finish;//
    @Column
    private String xds;//
    @Column
    private String sydw;//
    @Column
    private String gwy;//
    @Column
    private String szyf;//
    @Column
    private String xbjh;//
    @Column
    private String tgjs;//
    @Column
    private String cjrw;//
    @Column
    private String dxqy;//
    @Column
    private String yjs;//

}
