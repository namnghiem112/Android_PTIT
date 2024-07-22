package com.example.de7.views;

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

import com.example.de7.MainActivity;
import com.example.de7.R;
import com.example.de7.db.SQLiteHelper;
import com.example.de7.models.CongViec;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity {
    private EditText edName, edhocphi, edPublishDate;
    private CheckBox cbkichhoat;
    private Spinner spPublisher;
    private Button btnUpdate, btnCancel, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        Intent intent = getIntent();
        CongViec congviec = (CongViec) intent.getSerializableExtra("congviec");
        edName.setText(congviec.getTen());
        edhocphi.setText(congviec.getHocphi());
        edPublishDate.setText(congviec.getNgaybatdau());
        if(congviec.getKichhoat().equals("da kich hoat")){
            cbkichhoat.setChecked(true);
        }
        else cbkichhoat.setChecked(false);
        for(int i=0;i<spPublisher.getCount();i++){
            if(congviec.getChuyennganh().equals(spPublisher.getItemAtPosition(i))){
                spPublisher.setSelection(i);
                break;
            }
        }

        // Bấm vào ed publisher thì hiện dialog chọn ngày
        edPublishDate.setOnClickListener(view ->{
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            CongViec congviecAdd = new CongViec(congviec.getId(),name,publishDate,chuyennganh,congtac,hocphi);
            Log.i("kiemtra",congviecAdd.toString());
            SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext());
            sqLiteHelper.updateCongViec(congviecAdd);
            Log.i("test",String.valueOf(sqLiteHelper.updateCongViec(congviecAdd)));
            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intentToMainActivity);
        });

        btnDelete.setOnClickListener(view -> {
            SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateDeleteActivity.this);
            sqLiteHelper.deleteCongViec(congviec.getId());

            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, MainActivity.class);
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
        edName = findViewById(R.id.upedName);
        edhocphi = findViewById(R.id.upedhocphi);
        edPublishDate = findViewById(R.id.upedPublishDate);
        cbkichhoat = findViewById(R.id.upcbkichhoat);
        spPublisher = findViewById(R.id.upspinnerPublisher);
        btnUpdate = findViewById(R.id.btnupUpdate);
        btnCancel = findViewById(R.id.btnupCancel);
        btnDelete = findViewById(R.id.btnupDelete);
        spPublisher.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.chuyennganh)));
    }
}