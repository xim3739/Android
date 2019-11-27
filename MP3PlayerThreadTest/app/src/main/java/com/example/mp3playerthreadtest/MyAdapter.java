package com.example.mp3playerthreadtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private ArrayList<MyData> list;
    private int layout;

    public MyAdapter(ArrayList<MyData> list, int layout) {
        this.list = list;
        this.layout = layout;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.textViewHolderSingName.setText(list.get(position).getSingName());
        holder.textViewHolderSinger.setText(list.get(position).getSinger());
        holder.textViewHolderGenre.setText(list.get(position).getSingGerne());

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHolderSingName, textViewHolderSinger, textViewHolderGenre;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textViewHolderSingName = itemView.findViewById(R.id.textViewHolderSingName);
            this.textViewHolderSinger = itemView.findViewById(R.id.textViewHolderSinger);
            this.textViewHolderGenre = itemView.findViewById(R.id.textViewHolderGenre);

        }
    }
}
