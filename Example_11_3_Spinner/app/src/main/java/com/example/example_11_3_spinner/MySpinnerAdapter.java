package com.example.example_11_3_spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import static com.example.example_11_3_spinner.MainActivity.mainImageView;

public class MySpinnerAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<MySpinnerData> list;
    private LayoutInflater layoutInflater;
    public static int movieIdIndex;

    public MySpinnerAdapter(Context context, int layout, ArrayList<MySpinnerData> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public ArrayList<MySpinnerData> getList() {
        return list;
    }

    public void setList(ArrayList<MySpinnerData> list) {
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
            convertView = layoutInflater.inflate(R.layout.my_spinner, null);
        }
        TextView spinnerTextView = convertView.findViewById(R.id.spinnerTextView);
        final MySpinnerData mySpinnerData = list.get(position);
        spinnerTextView.setText(mySpinnerData.getMovieName().trim());

        spinnerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImageView.setImageResource(mySpinnerData.getMovieImageID());
            }
        });


        return convertView;
    }
}
