package com.example.btlandroid;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


import com.example.btlandroid.db.SQLiteHelper;
import com.example.btlandroid.model.SinhVien;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private EditText edtensinhvien, edemail, edngaysinh, edsodienthoai, edquaquan;
    private Spinner spkythuctap;
    private Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();

        // Bấm vào ed publisher thì hiện dialog chọn ngày
        edngaysinh.setOnClickListener(view ->{
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    m++;
                    String date = d+"/"+m+"/"+y;
                    edngaysinh.setText(date);
                }
            },year, month, day);
            datePickerDialog.show();
        });

        // Bấm nút update để đóng gói thông tin trên form vào 1 đối tượng Book rồi lưu xuống cơ sở dữ liệu
        // Và trở về main activity
        btnAdd.setOnClickListener(view -> {
            String tensinhvien = edtensinhvien.getText().toString();
            String email = edemail.getText().toString();
            String ngaysinh = edngaysinh.getText().toString();
            String kythuctap = spkythuctap.getSelectedItem().toString();
            String sodienthoai = edsodienthoai.getText().toString();
            String quequan = edquaquan.getText().toString();
            SinhVien sinhvien = new SinhVien(tensinhvien,email,sodienthoai,ngaysinh,quequan, kythuctap);

            SQLiteHelper sqLiteHelper = new SQLiteHelper(AddActivity.this);
            sqLiteHelper.addSinhVien(sinhvien);

            Intent intent = new Intent(AddActivity.this, HomeActivity.class);
            startActivity(intent);
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void initView() {
        edtensinhvien = findViewById(R.id.edtensinhvien);
        edemail = findViewById(R.id.edemail);
        edngaysinh = findViewById(R.id.edngaysinh);
        edsodienthoai = findViewById(R.id.edsodienthoai);
        spkythuctap = findViewById(R.id.spkythuctap);
        edquaquan = findViewById(R.id.edquequan);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        spkythuctap.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.kythuctap)));
    }
}