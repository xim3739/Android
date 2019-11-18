package com.example.example_11_1_gridview;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private int layoutID;
    private ArrayList<MovieData> list = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public MovieAdapter(Context context, int layoutID, ArrayList<MovieData> list) {
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    public ArrayList<MovieData> getList() {
        return list;
    }

    public void setList(ArrayList<MovieData> list) {
        this.list = list;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = layoutInflater.inflate(layoutID, null);

        }

        ImageView gridImageView = convertView.findViewById(R.id.gridImageView);
        TextView textView = convertView.findViewById(R.id.gridTextView);

        final MovieData movieData = list.get(position);

        gridImageView.setImageResource(movieData.getMovieId());
        textView.setText(movieData.getMovieName());

        gridImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View viewDialog = View.inflate(context, R.layout.custom_dialog, null);
                ImageView dialogImageView = viewDialog.findViewById(R.id.dialogImageView);

                MovieData movieData1 = list.get(position);

                dialogImageView.setImageResource(movieData1.getMovieId());

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle(movieData1.getMovieName());
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setView(viewDialog);
                dialog.setPositiveButton("닫기", null);
                dialog.show();

            }
        });

        return convertView;
    }
}
