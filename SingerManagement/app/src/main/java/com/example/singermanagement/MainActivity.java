package com.example.singermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyDataBaseHelper myDataBaseHelper;
    private EditText editTextName, editTextMember, editResultName, editResultMember;
    private Button btInit, btInsert, btSelect;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 이름 관리");

        editTextName = findViewById(R.id.editTextName);
        editTextMember = findViewById(R.id.editTextMember);
        editResultName = findViewById(R.id.editResultName);
        editResultMember = findViewById(R.id.editResultMember);
        btInit = findViewById(R.id.btInit);
        btInsert = findViewById(R.id.btInsert);
        btSelect = findViewById(R.id.btSelect);
        myDataBaseHelper = new MyDataBaseHelper(this);

        btInit.setOnClickListener(this);
        btInsert.setOnClickListener(this);
        btSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sqLiteDatabase = myDataBaseHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.btInit :
                myDataBaseHelper.onUpgrade(sqLiteDatabase, 1, 2);
                sqLiteDatabase.close();
                break;
            case R.id.btInsert :
                String str = "INSERT INTO groupTBL values('"+
                        editTextName.getText().toString().trim() + "' ," +
                        editTextMember.getText().toString() + ");";
                sqLiteDatabase.execSQL(str);
                sqLiteDatabase.close();
                Toast.makeText(MainActivity.this, editTextName.toString().trim() + " 이 저장되었습니다.", Toast.LENGTH_LONG).show();
                Log.d("MainActivity", editTextName.toString().trim() + " 이 저장되었습니다.");
                break;
            case R.id.btSelect :

                sqLiteDatabase = myDataBaseHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqLiteDatabase.rawQuery(
                        "SELECT * FROM groupTBL;",
                        null);

                String strName = "그룹 이름" + "\r\n" + "========" + "\r\n";
                String strMember = "인원" + "\r\n" + "=======" + "\r\n";

                while(cursor.moveToNext()) {
                    strName += cursor.getString(0) + "\r\n";
                    strMember += cursor.getString(1) + "\r\n";
                }

                editResultName.setText(strName);
                editResultMember.setText(strMember);

                cursor.close();
                sqLiteDatabase.close();

                break;
        }

    }
}
