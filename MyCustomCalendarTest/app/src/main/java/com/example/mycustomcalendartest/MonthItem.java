package com.example.mycustomcalendartest;

public class MonthItem {

    private int dayValue;

    public MonthItem() {

    }

    public MonthItem(int dayValue) {
        this.dayValue = dayValue;
    }

    public int getDayValue() {
        return dayValue;
    }

    public void setDayValue(int dayValue) {
        this.dayValue = dayValue;
    }
}
