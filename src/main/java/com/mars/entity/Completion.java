package com.mars.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Completion implements Serializable {
    @Id
    private String category;//男女总数分类
    @Column
    private String total;
    @Column
    private String finish;//
    @Column
    private String  _100;
    @Column
    private String  _9099;
    @Column
    private String  _8089;
    @Column
    private String _7079 ;
    @Column
    private String _6069 ;
    @Column
    private String _5059 ;
    @Column
    private String _4049 ;
    @Column
    private String _3039 ;
    @Column
    private String _2029 ;
    @Column
    private String _1019 ;
    @Column
    private String _09 ;

    /*private String xueqi;
    private String college;
    private String cls ;*/


}
