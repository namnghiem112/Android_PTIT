package com.example.btlandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.btlandroid.model.SinhVien;
import com.example.btlandroid.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "testdb";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE users("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "username TEXT,password TEXT, email TEXT)";
        db.execSQL(sql);
        String sql2 = "CREATE TABLE sinhviens(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensinhvien TEXT," +
                "email TEXT," +
                "sodienthoai TEXT," +
                "ngaysinh TEXT," +
                "quequan TEXT ,kythuctap TEXT)";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    public List<User> getAllUser(){
        List<User> list = new ArrayList<>();

        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("users",null,null,null,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String username = rs.getString(1);
            String password = rs.getString(2);
            String email = rs.getString(3);
            list.add(new User(id,username,password,email));
        }
        return list;
    }

    //add 1 user
    public long addUser(User u){

        ContentValues values = new ContentValues();
        values.put("username",u.getUsername());
        values.put("password",u.getPassword());
        values.put("email",u.getEmail());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        System.out.println("da add user"+u.getUsername()+" thanh cong");
        return sqLiteDatabase.insert("users", null, values);
    }

    //delete
    public int delete(int id){
        String whereClause = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("users",whereClause,whereArgs);
    }


    public boolean checkUserSigup(String username){
        List<User> list = getAllUser();
        for(User x:list){
            if(x.getUsername().equalsIgnoreCase(username)){
                return false;
            }
        }
        return true;
    }

    public boolean checkUser(User u){
        List<User> list = getAllUser();
        for(User x:list){
            if(x.getUsername().equalsIgnoreCase(u.getUsername()) && x.getPassword().equalsIgnoreCase(u.getPassword())){
                return true;
            }
        }
        return false;
    }
    public User getUserByUserName(String username) {
        String[] projection = {
                "id",
                "username",
                "email",
                "password",
        };

        String selection = "username LIKE ?";
        String[] selectionArgs = { "%" + username + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        // Thực hiện truy vấn
        Cursor cursor = sqLiteDatabase.query(
                "users", projection, selection, selectionArgs, null, null, null
        );

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setUsername(ten);
            user.setPassword(password);
        }

        // Đóng Cursor và SQLiteDatabase
        if (cursor != null) {
            cursor.close();
        }
        sqLiteDatabase.close();

        return user;
    }

    /// sinhvien
    public long addSinhVien(SinhVien sinhvien){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensinhvien", sinhvien.getTensinhvien());
        contentValues.put("email", sinhvien.getEmail());
        contentValues.put("sodienthoai", sinhvien.getSodienthoai());
        contentValues.put("ngaysinh", sinhvien.getNgaysinh());
        contentValues.put("quequan", sinhvien.getQuequan());
        contentValues.put("kythuctap", sinhvien.getKythuctap());
        long result = sqLiteDatabase.insert("sinhviens", null,contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<SinhVien> getAllSinhVien(){
        List<SinhVien> sinhVienList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("sinhviens",null,null,
                null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tensinhvien = cursor.getString(1);
            String email = cursor.getString(2);
            String sodienthoai = cursor.getString(3);
            String ngaysinh= cursor.getString(4);
            String quequan= cursor.getString(5);
            String kythuctap = cursor.getString(6);
            SinhVien sinhvien = new SinhVien(id,tensinhvien,email,sodienthoai,ngaysinh,quequan,kythuctap);
            sinhVienList.add(sinhvien);
        }
        return sinhVienList;
    }

    public long updateSinhVien(SinhVien sinhvien){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensinhvien", sinhvien.getTensinhvien());
        contentValues.put("email", sinhvien.getEmail());
        contentValues.put("sodienthoai", sinhvien.getSodienthoai());
        contentValues.put("ngaysinh", sinhvien.getNgaysinh());
        contentValues.put("quequan", sinhvien.getQuequan());
        contentValues.put("kythuctap", sinhvien.getKythuctap());
        long result = sqLiteDatabase.update("sinhviens",
                contentValues,"_id=?",
                new String[]{sinhvien.getId()+""});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteSinhVien(int sinhvienId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.delete("sinhviens", "_id=?",
                new String[]{sinhvienId+""});
        sqLiteDatabase.close();
        return result;
    }
    public List<SinhVien> findSinhVienByName(String tensinhvien) {
        List<SinhVien> sinhVienList = new ArrayList<>();

        String[] projection = {
                "_id",
                "tensinhvien",
                "email",
                "sodienthoai",
                "ngaysinh",
                "quequan",
                "kythuctap"
        };

        String selection = "tensinhvien LIKE ?";
        String[] selectionArgs = { "%" + tensinhvien + "%" };

        String orderBy = "ngaysinh ASC";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        // Thực hiện truy vấn
        Cursor cursor = sqLiteDatabase.query(
                "sinhviens", projection, selection, selectionArgs, null, null, orderBy
        );

        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String email = cursor.getString(2);
            String sodienthoai = cursor.getString(3);
            String ngaysinh= cursor.getString(4);
            String quequan= cursor.getString(5);
            String kythuctap = cursor.getString(6);
            SinhVien sinhvien = new SinhVien(id,ten,email,sodienthoai,ngaysinh,quequan,kythuctap);
            sinhVienList.add(sinhvien);
        }
        Collections.sort(sinhVienList, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien sv1, SinhVien sv2) {
                String[] sinhvien1 = sv1.getNgaysinh().split("/");
                String[] sinhvien2 = sv2.getNgaysinh().split("/");
                if(!sinhvien1[2].equals(sinhvien2[2])){
                    return sinhvien1[2].compareTo(sinhvien2[2]);
                }
                if(!sinhvien1[1].equals(sinhvien2[1])){
                    return sinhvien1[1].compareTo(sinhvien2[1]);
                }
                return sinhvien1[0].compareTo(sinhvien2[0]);
            }
        });
        return sinhVienList;
    }
    public List<SinhVien> getStatistic(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<SinhVien> sinhVienList = new ArrayList<>();
        String[] projection = {
                "kythuctap",
                "COUNT(*) AS soluong"
        };
        String orderBy = "soluong DESC";

        Cursor cursor = sqLiteDatabase.query(
                "sinhviens",   // Tên bảng
                projection,    // Các cột cần trả về
                null,          // Điều kiện WHERE (không có điều kiện)
                null,          // Giá trị của điều kiện WHERE
                "kythuctap",          // Không cần GROUP BY vì chúng ta chỉ có một hàm tổng hợp
                null,          // HAVING (không có điều kiện)
                orderBy        // Sắp xếp kết quả
        );
        while(cursor!=null && cursor.moveToNext()){
            String kythuctap = cursor.getString(0);
            int soluong = cursor.getInt(1);
            SinhVien sinhvien = new SinhVien(soluong,kythuctap);
            sinhVienList.add(sinhvien);
        }
        return sinhVienList;
    }
}
