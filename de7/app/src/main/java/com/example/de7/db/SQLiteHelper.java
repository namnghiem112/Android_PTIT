package com.example.de7.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.de7.models.CongViec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String dbName = "de7";

    public SQLiteHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE khoahocs(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT," +
                "ngaybatdau TEXT," +
                "chuyennganh TEXT," +
                "kichhoat TEXT," +
                "hocphi TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long addCongViec(CongViec congviec){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", congviec.getTen());
        contentValues.put("ngaybatdau", congviec.getNgaybatdau());
        contentValues.put("chuyennganh", congviec.getChuyennganh());
        contentValues.put("kichhoat", congviec.getKichhoat());
        contentValues.put("hocphi", congviec.getHocphi());
        long result = sqLiteDatabase.insert("khoahocs", null,contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<CongViec> getAllCongViec(){
        List<CongViec> congViecList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("khoahocs",null,null,
                null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String ngaybatdau = cursor.getString(2);
            String chuyennganh = cursor.getString(3);
            String kichhoat = cursor.getString(4);
            String hocphi = cursor.getString(5);
            CongViec congviec = new CongViec(id,ten,ngaybatdau,chuyennganh,kichhoat,hocphi);
            congViecList.add(congviec);
        }
        return congViecList;
    }

    public long updateCongViec(CongViec congviec){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", congviec.getTen());
        contentValues.put("ngaybatdau", congviec.getNgaybatdau());
        contentValues.put("chuyennganh", congviec.getChuyennganh());
        contentValues.put("kichhoat", congviec.getKichhoat());
        contentValues.put("hocphi", congviec.getHocphi());
        String[] whereArgs = {Integer.toString(congviec.getId())};
        long result = sqLiteDatabase.update("khoahocs",
                contentValues,"_id=?",
                whereArgs);
        return result;
    }

    public long deleteCongViec(int congViecId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.delete("khoahocs", "_id=?",
                new String[]{congViecId+""});
        sqLiteDatabase.close();
        return result;
    }

    public List<CongViec> findCongViecByNgayHoanThanh(String tencongviec) {
        List<CongViec> congViecList = new ArrayList<>();

        // Xác định các cột bạn muốn trả về
        String[] projection = {
                "_id",
                "ten",
                "noidung",
                "ngayht",
                "tinhtrang",
                "congtac"
        };

        // Tạo điều kiện WHERE để tìm kiếm theo tên công việc
        String selection = "ten LIKE ?";
        String[] selectionArgs = { "%" + tencongviec + "%" };

        // Sắp xếp kết quả theo ngày hoàn thành từ sớm đến muộn
        String orderBy = "ngayht ASC";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        // Thực hiện truy vấn
        Cursor cursor = sqLiteDatabase.query(
                "khoahocs", projection, selection, selectionArgs, null, null, orderBy
        );

        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            String publishDate = cursor.getString(3);
            String publisher = cursor.getString(4);
            String price = cursor.getString(5);
            CongViec congviec = new CongViec(id,name,author,publishDate,publisher,price);
            congViecList.add(congviec);
        }
        return congViecList;
    }


//    public List<CongViec> getStatistic(){
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        List<CongViec> congViecList = new ArrayList<>();
////        String[] projection = {
////                "_id",
////                "ten",
////                "noidung",
////                "ngayht",
////                "tinhtrang",
////                "COUNT(*) AS soLuongCongViec",
////                "congtac"
////        };
//        String[] projection = {
//                "tinhtrang",
//                "COUNT(*) AS soLuongCongViec"
//        };
//        String orderBy = "soLuongCongViec DESC"; // Sắp xếp kết quả theo số lượng công việc giảm dần
//
//        Cursor cursor = sqLiteDatabase.query(
//                "khoahocs",   // Tên bảng
//                projection,    // Các cột cần trả về
//                null,          // Điều kiện WHERE (không có điều kiện)
//                null,          // Giá trị của điều kiện WHERE
//                "tinhtrang",          // Không cần GROUP BY vì chúng ta chỉ có một hàm tổng hợp
//                null,          // HAVING (không có điều kiện)
//                orderBy        // Sắp xếp kết quả
//        );
//        while(cursor!=null && cursor.moveToNext()){
////            int id = cursor.getInt(0);
////            String name = cursor.getString(1);
////            String author = cursor.getString(2);
////            String publishDate = cursor.getString(3);
////            int soluong = cursor.getInt(5);
////            String publisher = cursor.getString(4) + " có " + String.valueOf(soluong) +" công việc";
////            String congtac = cursor.getString(6);
////            CongViec congviec = new CongViec(id,name,author,publishDate,publisher,congtac);
//            String tinhtang = cursor.getString(0);
//            int soluong = cursor.getInt(1);
//            CongViec congviec = new CongViec(tinhtang,soluong);
//            congViecList.add(congviec);
//        }
//        return congViecList;
//    }
}
