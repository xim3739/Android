package com.example.mycustomcalendartest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class MonthItemView extends AppCompatTextView {

    private MonthItem monthItem;

    public MonthItemView(Context context) {
        super(context);
    }

    public MonthItemView(Context context, AttributeSet attrs){
        super(context, attrs);

        init();
    }

    private void init() {
        setBackgroundColor(Color.WHITE);
    }

    public MonthItem getMonthItem() {
        return monthItem;
    }

    public void setMonthItem(MonthItem monthItem) {

        this.monthItem = monthItem;
        int day = monthItem.getDayValue();

        if(day != 0) {

            setText(String.valueOf(day));

        }else {

            setText("");

        }

    }
}
