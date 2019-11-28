package com.example.projectcalendarexample;

public class ItemData {

    private String date;
    private int dayValue;
    private String text;
    private int image;

    public ItemData(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ItemData(int dayValue) {
        this.dayValue = dayValue;
    }

    public ItemData(int dayValue, String text, int image) {
        this.dayValue = dayValue;
        this.text = text;
        this.image = image;
    }

    public int getDayValue() {
        return dayValue;
    }

    public void setDayValue(int dayValue) {
        this.dayValue = dayValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
