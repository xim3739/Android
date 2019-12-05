package com.example.projectcalendarexample;

public class ItemData {

    private int year;
    private int month;
    private int dayValue;
    private String text;
    private int image;

    public ItemData(int year, int month,  int dayValue, String text) {
        this.year = year;
        this.month = month;
        this.dayValue = dayValue;
        this.text = text;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public ItemData(){

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
