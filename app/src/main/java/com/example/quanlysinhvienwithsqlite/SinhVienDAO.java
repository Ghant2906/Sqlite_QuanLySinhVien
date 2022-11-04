package com.example.quanlysinhvienwithsqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {
    SQLiteDatabase db_335;

    public SinhVienDAO(Context context) {
        DBHandler databaseHandler = new DBHandler(context);

        db_335 = databaseHandler.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<SinhVien> get(String sql, String ...selectArgs){
        List<SinhVien> listSV = new ArrayList<>();
        Cursor cursor = db_335.rawQuery(sql,selectArgs);

        while(cursor.moveToNext()){
            SinhVien sinhVien = new SinhVien();
            sinhVien.setIdSV(cursor.getInt(cursor.getColumnIndex("idSV")));
            sinhVien.setNameSV(cursor.getString(cursor.getColumnIndex("nameSV")));
            sinhVien.setGioiTinhSV(cursor.getString(cursor.getColumnIndex(("gioiTinhSV"))));
            sinhVien.setNamSinhSV(cursor.getInt(cursor.getColumnIndex(("namSinhSV"))));

            listSV.add(sinhVien);
        }
        return listSV;
    }

    public List<SinhVien> getAll(){
        String sql = "Select * from SinhVien";
        return get(sql);
    }

    public SinhVien getSVbyId(int id){
        String sql = "select * from SinhVien where idSV = ?";
        List<SinhVien> list = get(sql, String.valueOf(id));
        return list.get(0);
    }

    public long insert(SinhVien sinhVien){
        ContentValues values = new ContentValues();
        values.put("idSV",sinhVien.getIdSV());
        values.put("nameSV", sinhVien.getNameSV());
        values.put("gioiTinhSV", sinhVien.getGioiTinhSV());
        values.put("namSinhSV", sinhVien.getNamSinhSV());

        return db_335.insert("SinhVien", null, values);
    }

    public long update(SinhVien sinhVien){
        ContentValues values = new ContentValues();
        values.put("nameSV", sinhVien.getNameSV());

        return db_335.update("SinhVien", values,"idSV=?",new String[]{String.valueOf(sinhVien.getIdSV())});
    }

    public int delete(int id){
        return db_335.delete("SinhVien", "idSV=?", new String[] {String.valueOf(id)});
    }
}
