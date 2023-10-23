package com.example.log_in;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    public static final String DBNAME = "travels";
    public static final String TABLENAME = "categories";
    public static final int VER = 1;


    public DBManager(Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TABLENAME + "(id integer primary key, picPath blob, title text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "drop table if exists " + TABLENAME + "";
        sqLiteDatabase.execSQL(query);
    }

}