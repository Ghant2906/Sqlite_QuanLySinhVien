package com.example.quanlysinhvienwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME_335 = "QuanLySinhVien";
    private static final int DATABASE_VERSION_335 = 2;

    public DBHandler(Context context){
        super(context,DATABASE_NAME_335,null,DATABASE_VERSION_335);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "Create table SinhVien";
        createTable += "(idSV int not null primary key,";
        createTable += "nameSV text NULL,";
        createTable += "gioiTinhSV text null,";
        createTable += "namSinhSV int null)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS SinhVien";
        db.execSQL(dropTable);

        onCreate(db);
    }
}
