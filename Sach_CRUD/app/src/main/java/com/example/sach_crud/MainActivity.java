package com.example.sach_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.sach_crud.model.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText tensach, tacgia, ngayxuatban;
    private CheckBox cbTieuThuyet, cbKhoaHoc, cbThieuNhi;
    private Button btnAdd, btnUpdate;
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ngayxuatban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ngayxuatban.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
        bookAdapter = new BookAdapter(getBookList());
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(bookAdapter);
        btnAdd.setOnClickListener(v -> {
            String tensach2 = tensach.getText().toString();
            String tacgia2 = tacgia.getText().toString();
            String nxb = ngayxuatban.getText().toString();
            String theloai2 = "";
            if(cbTieuThuyet.isChecked()){
                theloai2 += "tieuthuyet" +" ";
            }
            if(cbThieuNhi.isChecked()){
                theloai2 += "thieunhi" +" ";
            }
            if(cbKhoaHoc.isChecked()){
                theloai2 += "khoahoc" +" ";
            }
            Book book = new Book(tensach2,tacgia2,nxb,theloai2);
            bookAdapter.addItem(book);
            tensach.setText("");
            tacgia.setText("");
            ngayxuatban.setText("");
        });
        bookAdapter.setMyItemClick(new BookAdapter.myItemClick() {
            @Override
            public void doSomething(int i) {
                Book book = bookAdapter.bookList.get(i);
                tensach.setText(book.getTensach());
                tacgia.setText(book.getTacgia());
                ngayxuatban.setText(book.getNgayxuatban());
                String tloai = book.getTheloai();
                if(tloai.contains("tieuthuyet")){
                    cbTieuThuyet.setChecked(true);
                }
                else cbTieuThuyet.setChecked(false);
                if(tloai.contains("thieunhi")){
                    cbThieuNhi.setChecked(true);
                }
                else cbThieuNhi.setChecked(false);
                if(tloai.contains("khoahoc")){
                    cbKhoaHoc.setChecked(true);
                }
                else cbKhoaHoc.setChecked(false);
                btnAdd.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnUpdate.setOnClickListener(v -> {
                    String ten = tensach.getText().toString();
                    String tacgia3 = tacgia.getText().toString();
                    String nxb2 = ngayxuatban.getText().toString();
                    String theloai2 = "";
                    if(cbTieuThuyet.isChecked()){
                        theloai2 += "tieuthuyet" +" ";
                    }
                    if(cbThieuNhi.isChecked()){
                        theloai2 += "thieunhi" +" ";
                    }
                    if(cbKhoaHoc.isChecked()){
                        theloai2 += "khoahoc" +" ";
                    }
                    book.setNgayxuatban(nxb2);
                    book.setTacgia(tacgia3);
                    book.setTensach(ten);
                    book.setTheloai(theloai2);
                    bookAdapter.updateItem(i,book);
                    tensach.setText("");
                    tacgia.setText("");
                    ngayxuatban.setText("");
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                });
            }
        });
    }
    private List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Đắc Nhân Tâm","Nam","15/3/2024","tieuthuyet"));
        bookList.add(new Book("Đắc Nhân Tâm","Nam","15/3/2024","thieunhi"));
        bookList.add(new Book("Đắc Nhân Tâm","Nam","15/3/2024","khoahoc"));
        bookList.add(new Book("Đắc Nhân Tâm","Nam","15/3/2024","tieuthuyet"));
        bookList.add(new Book("Đắc Nhân Tâm","Nam","15/3/2024","thieunhi"));
        bookList.add(new Book("Đắc Nhân Tâm","Nam","15/3/2024","tieuthuyet"));
        return bookList;
    }
    private void initView() {
        tensach = findViewById(R.id.edtensach);
        tacgia = findViewById(R.id.edtacgia);
        ngayxuatban = findViewById(R.id.edngayxuatban);
        cbKhoaHoc = findViewById(R.id.cbkhoahoc);
        cbThieuNhi = findViewById(R.id.cbthieunhi);
        cbTieuThuyet = findViewById(R.id.cbtieuthuyet);
        recyclerView = findViewById(R.id.recyview);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
    }
}