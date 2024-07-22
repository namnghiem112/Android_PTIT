package com.example.booktablayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eTen, ngayxuathien, soluongVn, soluongTg;
    private CheckBox cbarn,cbpros,cbpron,cbvacxin;
    private Button btUpdate,btBack,btRemove;
    Book item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Book)intent.getSerializableExtra("item");
        eTen.setText(item.getTen());
        ngayxuathien.setText(item.getNgayxuathien());
        String cautruc = item.getCautruc();
        if(cautruc.contains("ARN")){
            cbarn.setChecked(true);
        }
        if(cautruc.contains("PRO_S")){
            cbpros.setChecked(true);
        }
        if(cautruc.contains("PRO_N")){
            cbpron.setChecked(true);
        }
        if(item.getVacxin().equals("Co")){
            cbvacxin.setChecked(true);
        }
        else{
            cbvacxin.setChecked(false);
        }
        soluongTg.setText(item.getSoluongTG());
        soluongVn.setText(item.getSoluongVN());
    }

    public void initView(){
        eTen = findViewById(R.id.uptvTen);
        cbarn = findViewById(R.id.upcb_Add_arn);
        cbpron = findViewById(R.id.upcb_Add_pron);
        cbpros = findViewById(R.id.upcb_Add_pros);
        ngayxuathien = findViewById(R.id.uptvngayxuathien);
        soluongTg = findViewById(R.id.uptvsoluongTG);
        soluongVn = findViewById(R.id.uptvsoluongVN);
        cbvacxin = findViewById(R.id.upcb_Add_covacxin);
        btUpdate = findViewById(R.id.btupUpdate);
        btRemove = findViewById(R.id.btupDelete);
        btBack = findViewById(R.id.btupBack);
    }

    @Override
    public void onClick(View v) {
        if( v == btBack){
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
            Book i = new Book(item.getId(),ten,cautruc,ngayxuathien1,vacxin,soluongVN1,soluongTG1);
            SQLiteHelper db = new SQLiteHelper(this);
            db.update(i);
            finish();
        }
        if(v == btRemove){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa!");
            builder.setMessage("Ban co chac muon xoa "+item.getTen()+" khong?");
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                    db.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}