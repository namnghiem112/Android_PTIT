package com.example.booktablayout.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.booktablayout.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "KT23.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE book2("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ten TEXT,cautruc TEXT,ngayxuathien TEXT, vacxin TEXT,soluongVN Text, soluongTG Text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long addItem(Book i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("cautruc",i.getCautruc());
        values.put("ngayxuathien",i.getNgayxuathien());
        values.put("vacxin",i.getVacxin());
        values.put("soluongVN",i.getSoluongVN());
        values.put("soluongTG",i.getSoluongTG());
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert("book2",null,values);
    }
    public List<Book> getAll(){
        List<Book> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "ten DESC";
        Cursor rs = st.query("book2",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String cautruc = rs.getString(2);
            String ngayxuathien = rs.getString(3);
            String vacxin = rs.getString(4);
            String soluongVN = rs.getString(5);
            String soluongTG = rs.getString(6);
            list.add(new Book(id,ten,cautruc,ngayxuathien,vacxin,soluongVN, soluongTG));
        }
        return list;
    }
    public int update(Book i){
        ContentValues values = new ContentValues();
        values.put("ten",i.getTen());
        values.put("cautruc",i.getCautruc());
        values.put("ngayxuathien",i.getNgayxuathien());
        values.put("vacxin",i.getVacxin());
        values.put("soluongVN",i.getSoluongVN());
        values.put("soluongTG",i.getSoluongTG());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClase = "id=?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("book2",values,whereClase,whereArgs);
    }

    //delete
    public int delete(int id){
        String whereClase = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("book2",whereClase,whereArgs);
    }

    public List<Book> getBookByTitlegetBookByTitle(String s){
        List<Book> list = new ArrayList<>();
//        SQLiteDatabase st = getReadableDatabase();
//        String order = "danhgia DESC";
//        Cursor rs = st.query("books",null,null,null,null,null,order);
//        while (rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String ten = rs.getString(1);
//            String tacgia = rs.getString(2);
//            String doituong = rs.getString(3);
//            int phamvi = rs.getInt(4);
//            int danhgia = rs.getInt(5);
//            if(ten.contains(s)){
//                list.add(new Book(id,ten,tacgia,doituong,phamvi,danhgia));
//            }
//        }
        return list;
    }
    public void deleteAllRowsFromTable(String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }
//    public List<Book> findTenSach(String tensach) {
//        List<Book> list = new ArrayList<>();
//
//        // Xác định các cột bạn muốn trả về
//        String[] projection = {
//                "id",
//                "ten",
//                "tacgia",
//                "phamvi",
//                "doituong",
//                "danhgia"
//        };
//
//        String selection = "ten LIKE ?";
//        String[] selectionArgs = { "%" + tensach + "%" };
//        String orderBy = "danhgia DESC";
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        // Thực hiện truy vấn
//        Cursor rs = sqLiteDatabase.query(
//                "book2", projection, selection, selectionArgs, null, null, orderBy
//        );
//
//        while (rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String ten = rs.getString(1);
//            String tacgia = rs.getString(2);
//            String phamvi = rs.getString(3);
//            String doituong = rs.getString(4);
//            String danhgia = rs.getString(5);
//            list.add(new Book(id,ten,tacgia,phamvi,doituong,danhgia));
//        }
//        return list;
//    }
//    public List<Book> thongke() {
//        List<Book> list = new ArrayList<>();
//
//        // Xác định các cột bạn muốn trả về
//        String[] projection = {
//                "id",
//                "ten",
//                "tacgia",
//                "phamvi",
//                "doituong",
//                "MAX(danhgia) as danhgia"
//        };
//
//        String selection = "1=1";
//        String groupBy = "tacgia";
//        String orderBy = "danhgia DESC";
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        // Thực hiện truy vấn
//        Cursor rs = sqLiteDatabase.query(
//                "book2", projection, selection, null, groupBy, null, orderBy
//        );
//
//        while (rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String ten = rs.getString(1);
//            String tacgia = rs.getString(2);
//            String phamvi = rs.getString(3);
//            String doituong = rs.getString(4);
//            String danhgia = rs.getString(5);
//            list.add(new Book(id,ten,tacgia,phamvi,doituong,danhgia));
//        }
//        return list;
//    }
}
