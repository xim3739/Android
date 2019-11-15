package com.example.customlistviewstringtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter {

    private Context context;
    private int layoutId;
    private ArrayList<ListItemData> arrayData;
    private LayoutInflater layoutInflater;

    public ListItemAdapter(Context context, int layoutId, ArrayList<ListItemData> arrayData) {
        this.context = context;
        this.layoutId = layoutId;
        this.arrayData = arrayData;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = layoutInflater.inflate(layoutId, null);
        }

        final TextView textView = convertView.findViewById(R.id.textView);
        ListItemData listItemData = arrayData.get(position);

        textView.setText(listItemData.getData());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

}
