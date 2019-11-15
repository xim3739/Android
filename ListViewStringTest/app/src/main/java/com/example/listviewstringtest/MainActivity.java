package com.example.listviewstringtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private Button btClick;
    private EditText editText;
    private ArrayList<String> arrayData = new ArrayList<String>();
    private ArrayAdapter<String> adapterList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayDataInput();

        adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayData);

        listView = findViewById(R.id.listView);
        btClick = findViewById(R.id.btClick);
        editText = findViewById(R.id.editText);

        btClick.setOnClickListener(this);

        listView.setAdapter(adapterList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemLongClickListener(this);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView textView = view.findViewById(android.R.id.text1);

        Toast.makeText(MainActivity.this, position + "///" + id + "////" + textView.getText().toString(), Toast.LENGTH_SHORT).show();

    }

    private void arrayDataInput() {
        String[] animal = {"사자", "호랑이", "뱀", "소", "말", "양", "원숭이", "닭", "개", "돼지", "쥐", "토끼", "용"};
        for(int i = 0; i < animal.length; i++) {
            arrayData.add(animal[i]);
        }
    }

    @Override
    public void onClick(View v) {
        String saveText = editText.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btClick :
                arrayData.add(saveText);
                listView.invalidate();
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        arrayData.remove(position);
        adapterList.notifyDataSetChanged();
        return false;
    }
}
