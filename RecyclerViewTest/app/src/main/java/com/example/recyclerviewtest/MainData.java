package com.example.recyclerviewtest;

public class MainData {

    private int imageProfile;
    private String textViewName;
    private String textViewContents;

    public MainData(int imageProfile, String textViewName, String textViewContents) {
        this.imageProfile = imageProfile;
        this.textViewName = textViewName;
        this.textViewContents = textViewContents;
    }

    public int getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(int imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getTextViewName() {
        return textViewName;
    }

    public void setTextViewName(String textViewName) {
        this.textViewName = textViewName;
    }

    public String getTextViewContents() {
        return textViewContents;
    }

    public void setTextViewContents(String textViewContents) {
        this.textViewContents = textViewContents;
    }
}
