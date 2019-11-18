package com.example.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> list;
    private int layout;

    public MainAdapter(ArrayList<MainData> list, int layout) {

        this.list = list;
        this.layout = layout;

    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder holder, final int position) {

        holder.holderImageView.setImageResource(list.get(position).getImageProfile());
        holder.holderTextViewName.setText(list.get(position).getTextViewName());
        holder.holderTextViewContents.setText(list.get(position).getTextViewContents());

        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), holder.holderTextViewName.getText().toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                list.remove(position);
                notifyDataSetChanged();

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {

        return (list != null) ? list.size() : 0;

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public ImageView holderImageView;
        public TextView holderTextViewName;
        public TextView holderTextViewContents;

        public CustomViewHolder(@NonNull View itemView) {

            super(itemView);
            this.holderImageView = itemView.findViewById(R.id.holderImageView);
            this.holderTextViewName = itemView.findViewById(R.id.holderTextViewName);
            this.holderTextViewContents = itemView.findViewById(R.id.holderTextViewContents);

        }

    }

}
