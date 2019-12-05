package com.example.bundletest;

import java.io.Serializable;

public class Data implements Serializable {

    private static final int CODE = 1;

    private int code;
    private String name;

    public Data(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
