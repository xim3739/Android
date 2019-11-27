package com.example.mp3playerthreadtest;

public class MyData {

    private String singName;
    private String singer;
    private String singGerne;

    public MyData(String singName, String singer, String singGerne) {
        this.singName = singName;
        this.singer = singer;
        this.singGerne = singGerne;
    }

    public String getSingName() {
        return singName;
    }

    public void setSingName(String singName) {
        this.singName = singName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSingGerne() {
        return singGerne;
    }

    public void setSingGerne(String singGerne) {
        this.singGerne = singGerne;
    }
}
