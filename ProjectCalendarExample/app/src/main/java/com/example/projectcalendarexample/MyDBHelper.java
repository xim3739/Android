package com.example.projectcalendarexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "calendarDB";
    public static final String TBL_NAME = "calendarTBL";
    public static final int DB_VERSION = 1;
    public MyDBHelper(@Nullable Context context) {
        super(context, "calendarDB", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE calendarTBL (year int NOT NULL, month int NOT NULL, dayvalue int NOT NULL, text CHAR(100), PRIMARY KEY (year, month, dayvalue));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS calendarTBL;");
        onCreate(db);
    }
}
