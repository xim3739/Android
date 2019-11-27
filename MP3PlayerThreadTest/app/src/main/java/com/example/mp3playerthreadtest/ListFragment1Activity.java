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

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListFragment1Activity extends Fragment {

    private ListView listViewMP3;
    private View view;


    private ArrayList<String> list = new ArrayList<String>();
    private static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    public static String selectedMP3;
    public ListFragment1Activity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.list_fragment1, container, false);
        listViewMP3 = view.findViewById(R.id.listViewMP3);

        getSdCardList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice, list);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setItemChecked(0, true);
        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMP3 = list.get(position);
            }
        });

        return view;
    }

    private void getSdCardList() {
        File[] fileList = new File(MP3_PATH).listFiles();

        for(File file : fileList) {
            String fileName = file.getName();
            String subStrFileName = fileName.substring(fileName.length() - 3);

            if(subStrFileName.equals("mp3")) {
                list.add(fileName);
            }
        }
    }

}
