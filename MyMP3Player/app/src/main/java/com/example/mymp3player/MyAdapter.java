package com.example.mymp3player;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private ArrayList<MainData> list;
    private int layoutID;

    public MyAdapter(ArrayList<MainData> list, int layoutID) {
        this.list = list;
        this.layoutID = layoutID;
    }

    @NonNull
    @Override
    public MyAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.CustomViewHolder holder, int position) {

        holder.textViewSingName.setText(list.get(position).getName());

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewSingName;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewSingName = itemView.findViewById(R.id.textViewSingName);
        }

    }

}
