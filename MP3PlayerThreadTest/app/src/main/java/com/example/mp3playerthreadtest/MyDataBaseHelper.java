package com.example.mp3playerthreadtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "singDB";
    public static final String TBL_NAME = "singTBL";
    public static final int DB_VERSION = 1;

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TBL_NAME+" (name CHAR(20) PRIMARY KEY, singer CHAR(20), genre CHAR(20));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TBL_NAME);
        onCreate(db);
    }
}
