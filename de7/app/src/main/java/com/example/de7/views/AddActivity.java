package com.example.de7.views;

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

import com.example.de7.MainActivity;
import com.example.de7.R;
import com.example.de7.db.SQLiteHelper;
import com.example.de7.models.CongViec;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private EditText edName, edhocphi, edPublishDate;
    private CheckBox cbkichhoat;
    private Spinner spPublisher;
    private Button btnUpdate, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();

        // Bấm vào ed publisher thì hiện dialog chọn ngày
        edPublishDate.setOnClickListener(view ->{
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    m++;
                    String date = d+"/"+m+"/"+y;
                    edPublishDate.setText(date);
                }
            },year, month, day);
            datePickerDialog.show();
        });

        // Bấm nút update để đóng gói thông tin trên form vào 1 đối tượng Book rồi lưu xuống cơ sở dữ liệu
        // Và trở về main activity
        btnUpdate.setOnClickListener(view -> {
            String name = edName.getText().toString();
            String hocphi = edhocphi.getText().toString();
            String publishDate = edPublishDate.getText().toString();
            String chuyennganh = spPublisher.getSelectedItem().toString();
            String congtac ="";
            if (cbkichhoat.isChecked()){
                congtac = "da kich hoat";
            }
            else congtac = "chua";
            CongViec congviecAdd = new CongViec(name,publishDate,chuyennganh,congtac,hocphi);

            SQLiteHelper sqLiteHelper = new SQLiteHelper(AddActivity.this);
            sqLiteHelper.addCongViec(congviecAdd);

            Intent intent = new Intent(AddActivity.this, MainActivity.class);
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
        edName = findViewById(R.id.edName);
        edhocphi = findViewById(R.id.edhocphi);
        edPublishDate = findViewById(R.id.edPublishDate);
        cbkichhoat = findViewById(R.id.cbkichhoat);
        spPublisher = findViewById(R.id.spinnerPublisher);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        spPublisher.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.chuyennganh)));
    }
}