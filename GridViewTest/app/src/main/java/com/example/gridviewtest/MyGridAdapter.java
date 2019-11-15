package com.example.gridviewtest;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyGridAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private LayoutInflater layoutInflater;
    private ArrayList<MyData> list = new ArrayList<MyData>();
    View dialog;

    public MyGridAdapter(Context context, int layout, ArrayList<MyData> list) {
        this.context = context;
        this.layout = layout;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<MyData> getList() {
        return list;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<MyData> list) {
        this.list = list;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.image_view_layout, null);
        }

        ImageView imageViewAdapter = convertView.findViewById(R.id.adapterImageView);
        final MyData myData = list.get(position);
        imageViewAdapter.setImageResource(myData.getPostID());

        imageViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = View.inflate(context, R.layout.dialog, null);
                ImageView imageView = dialog.findViewById(R.id.imageView);
                MyData myData1 = list.get(position);
                imageView.setImageResource(myData1.getPostID());
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("포스터어어어어");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setView(dialog);
                alertDialog.setPositiveButton("닫기", null);
                alertDialog.show();
            }
        });

        return convertView;
    }
}
