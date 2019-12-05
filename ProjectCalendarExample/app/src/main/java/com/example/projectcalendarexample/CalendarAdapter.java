package com.example.projectcalendarexample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.zip.Inflater;

public class CalendarAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private int selectedPosition = -1;
    private ItemData[] itemData;
    private int countColumn = 7;

    private int mounthStartDay;
    private int startDay;
    private int currentYear;
    private int currentMonth;
    private int firstDay;
    private int lastDay;

    private Calendar calendar;

    public CalendarAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return itemData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        if(convertView == null) {

            gridView = layoutInflater.inflate(R.layout.month_item_activity, parent, false);

        } else {
            gridView = (View)convertView;
        }

        int columnIndex = position % countColumn;

        TextView textViewItemDate = gridView.findViewById(R.id.textViewItemDate);
        TextView textViewItemText = gridView.findViewById(R.id.textViewItemText);
        ImageView imageViewItem = gridView.findViewById(R.id.imageViewItem);

        if(itemData[position].getDayValue() == 0) {

            textViewItemDate.setText("");
            imageViewItem.setVisibility(View.INVISIBLE);

        } else {

            textViewItemDate.setText(String.valueOf(itemData[position].getDayValue()));
            textViewItemText.setText(itemData[position].getText());

        }

        if(columnIndex == 0) {
            textViewItemDate.setTextColor(Color.RED);
        } else if (columnIndex == 6) {
            textViewItemDate.setTextColor(Color.BLUE);
        } else {
            textViewItemDate.setTextColor(Color.BLACK);
        }

        if(position == getSelectedPosition()) {
            gridView.setBackgroundColor(Color.BLUE);
        } else {
            gridView.setBackgroundColor(Color.LTGRAY);
        }

        return gridView;
    }

    private void init() {

        itemData = new ItemData[42];
        calendar = Calendar.getInstance();
        calculateData();
        resetDayNumber();

    }

    private void resetDayNumber() {
        for(int i = 0; i < 42; i++) {

            int dayNumber = (i + 1) - firstDay;

            if(dayNumber < 1 || dayNumber > lastDay) {
                dayNumber = 0;
            }

            itemData[i] = new ItemData(dayNumber);
        }
    }

    private void calculateData() {

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        firstDay = getFirstDay(dayOfWeek);

        mounthStartDay = calendar.getFirstDayOfWeek();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);

        lastDay = getMonthLastDay(currentYear, currentMonth);

        startDay = getFirstDayOfWeek();

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

    private int getMonthLastDay(int currentYear, int currentMonth) {
        switch (currentMonth +1) {
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

    private int getFirstDay(int dayOfWeek) {
        int result = 0;

        switch (dayOfWeek) {
            case Calendar.SUNDAY :
                result = 0;
                break;
            case Calendar.MONDAY :
                result = 1;
                break;
            case Calendar.TUESDAY :
                result = 2;
                break;
            case Calendar.WEDNESDAY :
                result = 3;
                break;
            case Calendar.THURSDAY :
                result = 4;
                break;
            case Calendar.FRIDAY :
                result = 5;
                break;
            case Calendar.SATURDAY :
                result = 6;
                break;
        }
        return result;
    }

    public void setPreviousMonth() {
        calendar.add(Calendar.MONTH, -1);
        calculateData();
        resetDayNumber();
        selectedPosition = -1;
    }
    public void setNextMonth() {
        calendar.add(Calendar.MONTH, 1);
        calculateData();
        resetDayNumber();
        selectedPosition = -1;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public ItemData[] getItemData() {
        return itemData;
    }

    public void setItemData(ItemData[] itemData) {
        this.itemData = itemData;
    }

    public int getCountColumn() {
        return countColumn;
    }

    public void setCountColumn(int countColumn) {
        this.countColumn = countColumn;
    }

    public int getMounthStartDay() {
        return mounthStartDay;
    }

    public void setMounthStartDay(int mounthStartDay) {
        this.mounthStartDay = mounthStartDay;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
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

    public int getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(int firstDay) {
        this.firstDay = firstDay;
    }

    public int getLastDay() {
        return lastDay;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
