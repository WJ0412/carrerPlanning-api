package com.mars.common.enumeration;

public enum UserTypeEnum {

    STUDENT("student"),

    TEACHER("teacher"),

    ADMIN("admin");

    private String value;

    UserTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
