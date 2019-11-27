package com.example.calendartest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter {

    private final int HEADER_TYPE = 0;
    private final int EMPTY_TYPE = 1;
    private final int DAY_TYPE = 2;

    private List<Object> mCalendarList;

    public CalendarAdapter(List<Object> mCalendarList) {
        this.mCalendarList = mCalendarList;
    }

    public void setCalendarList(List<Object> mCalendarList) {
        this.mCalendarList = mCalendarList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mCalendarList.get(position);
        if(item instanceof Long) {
            return HEADER_TYPE;
        } else if (item instanceof String) {
            return EMPTY_TYPE;
        } else {
            return DAY_TYPE;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType == HEADER_TYPE) {
            HeaderViewHolder viewHolder = new HeaderViewHolder(inflater.inflate(R.layout.item_calendar_header, parent, false));
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams();
            params.setFullSpan(true);
            viewHolder.itemView.setLayoutParams(params);
            return viewHolder;

        } else if(viewType == EMPTY_TYPE) {
            return new EmptyViewHolder(inflater.inflate(R.layout.item_day_empty, parent, false));
        } else {
            return new DayViewHolder(inflater.inflate(R.layout.item_day, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        int viewType = getItemViewType(position);

        if(viewType == HEADER_TYPE) {
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            CalendarHeader model = new CalendarHeader();

            if(item instanceof Long) {
                model.setHeader((Long)item);
            }

            holder.bind(model);

        } else if (viewType == EMPTY_TYPE) {
            EmptyViewHolder holder = (EmptyViewHolder) viewHolder;
            EmptyDay model = new EmptyDay();
            holder.bind(model);

        } else if (viewType == DAY_TYPE) {
            DayViewHolder holder = (DayViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            Day model = new Day();

            if(item instanceof Calendar) {
                model.setCalendar((Calendar) item);
            }
            holder.bind(model);
        }

    }

    @Override
    public int getItemCount() {
        if(mCalendarList != null) {
            return mCalendarList.size();
        }
        return 0;
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder{
        TextView itemHeaderTitle;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            itemHeaderTitle = (TextView) itemView.findViewById(R.id.item_header_title);
        }
        public void bind(ViewModel model) {
            String header = ((CalendarHeader) model).getHeader();
            itemHeaderTitle.setText(header);
        }
    }
    private class EmptyViewHolder extends RecyclerView.ViewHolder { // 비어있는 요일 타입 ViewHolder


        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);

            initView(itemView);
        }

        public void initView(View v){

        }

        public void bind(ViewModel model){


        }
    }
    private class DayViewHolder extends RecyclerView.ViewHolder {// 요일 입 ViewHolder

        TextView itemDay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);

            initView(itemView);

        }

        public void initView(View v){

            itemDay = (TextView)v.findViewById(R.id.item_day);

        }

        public void bind(ViewModel model){

            // 일자 값 가져오기
            String day = ((Day)model).getDay();

            // 일자 값 View에 보이게하기
            itemDay.setText(day);

        };
    }


}
