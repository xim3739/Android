package com.example.myobjectsendintenttest;

import java.io.Serializable;

public class Data implements Serializable {

    private static final long serialVersionUID = 1l;

    private String name;
    private int code;

    public Data(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
