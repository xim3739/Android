package com.example.mylistviewcreatertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MyData> list;
    private int layout;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, ArrayList<MyData> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = layoutInflater.inflate(layout, null);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView = convertView.findViewById(R.id.imageName);
        MyData myData = list.get(position);
        imageView.setImageResource(myData.getImageID());
        textView.setText(myData.getName());

        return convertView;
    }
}
