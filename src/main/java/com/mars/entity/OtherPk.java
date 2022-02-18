package com.mars.entity;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OtherPk implements Serializable {

    private String name;

    private String hiscall;
}
