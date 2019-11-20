package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextMember, editText1, editText2;
    private Button btInit, btInsert, btEdit, btDelete, btSelect, btSort;
    private SQLiteDatabase sqLiteDatabase;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextMember= findViewById(R.id.editTextMember);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        btInit = findViewById(R.id.btInit);
        btInsert = findViewById(R.id.btInsert);
        btEdit = findViewById(R.id.btEdit);
        btDelete = findViewById(R.id.btDelete);
        btSelect = findViewById(R.id.btSelect);
        btSort = findViewById(R.id.btSort);

        myDatabaseHelper = new MyDatabaseHelper(this);

        btInit.setOnClickListener(this);
        btInsert.setOnClickListener(this);
        btEdit.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btSelect.setOnClickListener(this);
        btSort.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btInit :
                buttonInitAction();
                break;
            case R.id.btInsert :
                buttonInsertAction();
                break;
            case R.id.btEdit :
                buttonEditAction();
                break;
            case R.id.btDelete :
                buttonDeleteAction();
                break;
            case R.id.btSelect :
                buttonSelectAction();
                break;
            case R.id.btSort :
                buttonSortAction();
                break;
        }
    }

    private void buttonInitAction() {

        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        myDatabaseHelper.onUpgrade(sqLiteDatabase, 1, 2);
        sqLiteDatabase.close();

        editTextName.setText("");
        editTextMember.setText("");
        editText1.setText("");
        editText2.setText("");

        toastMessage("초기화 하였습니다.");

    }

    private void buttonInsertAction() {
        String name = editTextName.getText().toString().trim();
        String member = editTextMember.getText().toString().trim();
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO groupTBL VALUES ('" + name +"', " + member + ");");
        sqLiteDatabase.close();
        toastMessage("입력 하였습니다.");
        buttonSelectAction();
    }

    private void buttonEditAction() {

        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        Cursor cursor;
        if(!(editTextName.getText().toString().equals(""))) {
            cursor = sqLiteDatabase.rawQuery("SELECT name FROM groupTBL WHERE name ='" + editTextName.getText().toString() +"';", null);
            if(cursor.getCount() != 0) {
                sqLiteDatabase.execSQL("UPDATE groupTBL SET member =" + editTextMember.getText().toString() + " WHERE name = '"+editTextName.getText().toString()+"';");
                toastMessage("수정 성공");
            } else {
                toastMessage("수정 실패");
            }
            cursor.close();
        }else {
            toastMessage("그룹이 없습니다. 다시 시도해주세요");
        }
        sqLiteDatabase.close();
        buttonSelectAction();
    }


    private void buttonDeleteAction() {

        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        if(editTextName.getText().toString().trim().isEmpty()) {
            toastMessage("그룹을 입력해 주세요");
        } else {
            sqLiteDatabase.execSQL("DELETE FROM groupTBL WHERE name = '" +editTextName.getText().toString()+"';");
            sqLiteDatabase.close();
            toastMessage("그룹을 삭제 하였습니다.");
            buttonSelectAction();
        }

    }

    private void buttonSelectAction() {

        sqLiteDatabase = myDatabaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL;", null);
        String name = "";
        String member = "";

        while(cursor.moveToNext()) {
            name += cursor.getString(0) + "\n";
            member += cursor.getString(1) + "\n";
        }

        editText1.setText(name);
        editText2.setText(member);

        cursor.close();
        sqLiteDatabase.close();
    }

    private void buttonSortAction() {
        sqLiteDatabase = myDatabaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL ORDER BY name;", null);

        String name = "";
        String member = "";

        while(cursor.moveToNext()) {
            name += cursor.getString(0) + "\n";
            member += cursor.getString(1) + "\n";
        }
        editText1.setText(name);
        editText2.setText(member);

        cursor.close();
        sqLiteDatabase.close();
    }

    private void toastMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
