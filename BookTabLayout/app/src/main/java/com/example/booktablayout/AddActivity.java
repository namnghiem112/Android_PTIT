package com.example.booktablayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booktablayout.dal.SQLiteHelper;
import com.example.booktablayout.model.Book;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eTen, ngayxuathien, soluongVn, soluongTg;
    private CheckBox cbarn,cbpros,cbpron,cbvacxin;

    private Button btUpdate,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        ngayxuathien.setOnClickListener(view ->{
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    m++;
                    String date = d+"/"+m+"/"+y;
                    ngayxuathien.setText(date);
                }
            },year, month, day);
            datePickerDialog.show();
        });
    }
    public void initView(){
        eTen = findViewById(R.id.tvTen);
        cbarn = findViewById(R.id.cb_Add_arn);
        cbpron = findViewById(R.id.cb_Add_pron);
        cbpros = findViewById(R.id.cb_Add_pros);
        ngayxuathien = findViewById(R.id.tvngayxuathien);
        soluongTg = findViewById(R.id.tvsoluongTG);
        soluongVn = findViewById(R.id.tvsoluongVN);
        cbvacxin = findViewById(R.id.cb_Add_covacxin);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
    }

    @Override
    public void onClick(View v) {
        if(v == btCancel){
            finish();
        }
        if(v == btUpdate){
            String ten = eTen.getText().toString();
            String cautruc ="";
            String ngayxuathien1 = ngayxuathien.getText().toString();
            if(cbarn.isChecked()){
                cautruc = cautruc+" ARN,";
            }
            if ((cbpros.isChecked())){
                cautruc = cautruc+" PRO_S,";
            }
            if(cbpron.isChecked()){
                cautruc = cautruc+" PRO_N,";
            }
            String vacxin="";
            if (cbvacxin.isChecked()){
                vacxin = "Co";
            }
            else{
                vacxin = "Chua";
            }
            String soluongVN1 = soluongVn.getText().toString();
            String soluongTG1 = soluongTg.getText().toString();
            Book i = new Book(ten,cautruc,ngayxuathien1,vacxin,soluongVN1,soluongTG1);
            SQLiteHelper db = new SQLiteHelper(this);
            db.addItem(i);
            Log.i("kiemtra",i.toString());
            finish();
        }
}
}
