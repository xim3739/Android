package com.example.mycustomcalendartest;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

class CalendarAdapter extends BaseAdapter {

    public static final String TAG = "MonthAdapter";

    private Context context;

    public static int oddColor = Color.WHITE;
    public static int headColor = Color.DKGRAY;

    private int selectedPosition = -1;
    public MonthItem[] items;
    private int countColumn = 7;

    private int mStartDay;
    private int startDay;
    private int currentYear;
    private int currentMonth;
    private int firstDay;
    private int lastDay;

    private Calendar mCalendar;
    private boolean recreateItems = false;

    public CalendarAdapter() {
    }

    public CalendarAdapter(Context context) {
        this.context = context;
        init();
    }

    public CalendarAdapter(Context context, AttributeSet attrs) {
        this.context = context;
        init();
    }

    @Override
    public int getCount() {
        return 42;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        MonthItemView itemView;
        if(convertView == null) {
            itemView = new MonthItemView(context);
        } else {
            itemView = (MonthItemView) convertView;
        }
        GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, 250);
        int rowIndex = position / countColumn;
        int columnIndex = position % countColumn;
        
        itemView.setMonthItem(items[position]);
        itemView.setLayoutParams(params);
        itemView.setPadding(2,2,2,2);
        itemView.setGravity(Gravity.LEFT);
        
        if(columnIndex == 0) {
            itemView.setTextColor(Color.RED);
        } else if (columnIndex == 6){
            itemView.setTextColor(Color.BLUE);
        } else {
            itemView.setTextColor(Color.BLACK);
        }
        
        if(position == getSeletedPosition()) {
            itemView.setBackgroundColor(Color.DKGRAY);
        } else {
            itemView.setBackgroundColor(Color.WHITE);
        }

        return itemView;
    }

    private void init() {

        items = new MonthItem[42];
        mCalendar = Calendar.getInstance();
        reCalcuate();
        resetDayNumbers();

    }

    private void reCalcuate() {

        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);

        firstDay = getFirstDay(dayOfWeek);

        mStartDay = mCalendar.getFirstDayOfWeek();
        currentYear = mCalendar.get(Calendar.YEAR);
        currentMonth = mCalendar.get(Calendar.MONTH);

        lastDay = getMonthLastDay(currentYear, currentMonth);

        int diff = mStartDay - Calendar.SUNDAY - 1;

        startDay = getFirstDayOfWeek();

    }

    private void resetDayNumbers() {

        for(int i = 0; i < 42; i++) {

            int dayNumber = (i + 1) - firstDay;

            if(dayNumber < 1 || dayNumber > lastDay) {
                dayNumber = 0;
            }

            items[i] = new MonthItem(dayNumber);

        }

    }

    private int getFirstDay(int dayOfWeek) {
        int result = 0;
        switch (dayOfWeek) {

            case Calendar.SUNDAY:
                result = 0;
                break;
            case Calendar.MONDAY:
                result = 1;
                break;
            case Calendar.TUESDAY:
                result = 2;
                break;
            case Calendar.WEDNESDAY:
                result = 3;
                break;
            case Calendar.THURSDAY:
                result = 4;
                break;
            case Calendar.FRIDAY :
                result = 5;
                break;
            case Calendar.SATURDAY:
                result = 6;
                break;

        }

        return result;

    }

    private int getMonthLastDay(int currentYear, int currentMonth) {
        switch (currentMonth + 1) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;

            case 4:
            case 6:
            case 9:
            case 11:
                return 30;

            default:

                if(((currentYear % 4 == 0) && (currentYear % 100 != 0)) || (currentYear % 400 == 0)) {

                    return 29;

                } else {

                    return 28;

                }

        }
    }

    private int getFirstDayOfWeek() {

        int startDay = Calendar.getInstance().getFirstDayOfWeek();

        if(startDay == Calendar.SATURDAY) {
            return Time.SATURDAY;

        } else if (startDay == Calendar.MONDAY) {
            return Time.MONDAY;

        } else {
          return Time.SUNDAY;

        }

    }

    public void setPreviousMonth() {
        mCalendar.add(Calendar.MONTH, -1);
        reCalcuate();
        resetDayNumbers();
        selectedPosition = -1;
    }
    public void setNextMonth() {
        mCalendar.add(Calendar.MONTH, 1);
        reCalcuate();
        resetDayNumbers();
        selectedPosition = -1;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    private int getSeletedPosition() {
        return selectedPosition;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }
}
