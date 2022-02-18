package com.mars.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FamilyPk implements Serializable {

    private String name;

    private String hiscall;
}
