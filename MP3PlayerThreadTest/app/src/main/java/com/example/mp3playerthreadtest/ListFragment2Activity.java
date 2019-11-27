package com.example.mp3playerthreadtest;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class ListFragment2Activity extends Fragment {

    private RecyclerView recyclerView;
    private View view;

    public static ArrayList<MyData> likeList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter myAdapter;

    public ListFragment2Activity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.list_fragment2, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        myAdapter = new MyAdapter(likeList, R.layout.holder);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

        return view;
    }

}
