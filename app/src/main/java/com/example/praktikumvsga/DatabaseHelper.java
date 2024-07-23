package com.example.praktikumvsga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "daftarpeserta.db";
    private static final String TABLE_NAME = "mahasiswa";
    private static final String COL_1 = "idMahasiswa";
    private static final String COL_2 = "nama";
    private static final String COL_3 = "tanggal_lahir";
    private static final String COL_4 = "jenis_kelamin";
    private static final String COL_5 = "alamat";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (idMahasiswa INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, tanggal_lahir TEXT, jenis_kelamin TEXT, alamat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData(String nama, String tanggal_lahir, String jenis_kelamin, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, tanggal_lahir);
        contentValues.put(COL_4, jenis_kelamin);
        contentValues.put(COL_5, alamat);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getDataByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_2 + "=?", new String[]{name});
        return res;
    }

    public boolean updateData(String nomor, String nama, String tanggalLahir, String jenisKelamin, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, tanggalLahir);
        contentValues.put(COL_4, jenisKelamin);
        contentValues.put(COL_5, alamat);

        // Updating data
        long result = db.update(TABLE_NAME, contentValues, "idMahasiswa=?", new String[]{nomor});
        return result != -1;
    }

    public int deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1 + " = ?", new String[]{String.valueOf(id)});
    }

}
