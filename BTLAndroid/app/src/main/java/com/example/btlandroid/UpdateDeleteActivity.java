package com.example.btlandroid;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class UpdateDeleteActivity extends AppCompatActivity {
    private EditText edtensinhvien, edemail, edngaysinh, edsodienthoai, edquaquan;
    private Spinner spkythuctap;
    private Button btnUpdate, btnCancel, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        Intent intent = getIntent();
        SinhVien sinhvien = (SinhVien) intent.getSerializableExtra("sinhvien");
        edtensinhvien.setText(sinhvien.getTensinhvien());
        edemail.setText(sinhvien.getEmail());
        edngaysinh.setText(sinhvien.getNgaysinh());
        edsodienthoai.setText(sinhvien.getSodienthoai());
        edquaquan.setText(sinhvien.getQuequan());
        for(int i=0;i<spkythuctap.getCount();i++){
            if(sinhvien.getKythuctap().equals(spkythuctap.getItemAtPosition(i))){
                spkythuctap.setSelection(i);
                break;
            }
        }
        edngaysinh.setOnClickListener(view ->{
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    m++;
                    String date = d+"/"+m+"/"+y;
                    edngaysinh.setText(date);
                }
            },year, month, day);
            datePickerDialog.show();
        });

        btnUpdate.setOnClickListener(view -> {
            String tensinhvien = edtensinhvien.getText().toString();
            String email = edemail.getText().toString();
            String ngaysinh = edngaysinh.getText().toString();
            String kythuctap = spkythuctap.getSelectedItem().toString();
            String sodienthoai = edsodienthoai.getText().toString();
            String quequan = edquaquan.getText().toString();

            SinhVien sinhVienUpdate = new SinhVien(sinhvien.getId(),tensinhvien,email,sodienthoai,ngaysinh,quequan, kythuctap);

            SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateDeleteActivity.this);
            sqLiteHelper.updateSinhVien(sinhVienUpdate);

            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, HomeActivity.class);
            startActivity(intentToMainActivity);
        });

        btnDelete.setOnClickListener(view -> {
            SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateDeleteActivity.this);
            sqLiteHelper.deleteSinhVien(sinhvien.getId());

            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, HomeActivity.class);
            startActivity(intentToMainActivity);
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initView() {
        edtensinhvien = findViewById(R.id.edtensinhvien1);
        edemail = findViewById(R.id.edemail1);
        edngaysinh = findViewById(R.id.edngaysinh1);
        edsodienthoai = findViewById(R.id.edsodienthoai1);
        spkythuctap = findViewById(R.id.spkythuctap1);
        edquaquan = findViewById(R.id.edquequan1);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel1);
        btnDelete = findViewById(R.id.btnDelete);
        spkythuctap.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.kythuctap)));
    }
}