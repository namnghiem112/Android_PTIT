package com.example.cauthu_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cauthu_crud.model.CauThu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private EditText edtencauthu, edngaysinh, edvitrida;
    private EditText editText;
    private CheckBox checkBox1, checkBox2, checkBox3;
    private CheckBox cbTienVe, cbHauVe, cbTienDao;
    private RadioGroup radioGroup;
    private RadioButton radioButtonNam, radioButtonNu;
    private Button btnAdd, btnUpdate, btnSearch;
    private RecyclerView recyclerView;
    private CauThuApater cauThuApater;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        edngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edngaysinh.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
        btnAdd.setOnClickListener(v -> {
            String ten = edtencauthu.getText().toString();
            int radioId = radioGroup.getCheckedRadioButtonId();
            String gioitinh = radioId == R.id.radiobtnNam ? "nam" : "nu";
            Log.v("giotinh",gioitinh);
            String ngaysinh = edngaysinh.getText().toString();
            String vitri = "";
            if(cbTienVe.isChecked()){
                vitri += "tienve" +" ";
            }
            if(cbTienDao.isChecked()){
                vitri += "tiendao" +" ";
            }
            if(cbHauVe.isChecked()){
                vitri += "hauve" +" ";
            }
            CauThu cauThu = new CauThu(ten,ngaysinh,gioitinh,vitri);
            Log.v("kiemtra",cauThu.toString());
            cauThuApater.addItem(cauThu);
            edtencauthu.setText("");
            edngaysinh.setText("");
            radioButtonNam.setChecked(false);
            radioButtonNu.setChecked(false);
            cbHauVe.setChecked(false);
            cbTienVe.setChecked(false);
            cbTienDao.setChecked(false);
        });
        cauThuApater = new CauThuApater(getCauThuList());
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(cauThuApater);
        cauThuApater.setMyItemClick(new CauThuApater.myItemClick() {
            @Override
            public void doSomething(int i) {
                CauThu cauThu = cauThuApater.cauThuList.get(i);
                edtencauthu.setText(cauThu.getTencauthu());
                edngaysinh.setText(cauThu.getNgaysinh());
                if(cauThu.getGioitinh().equals("nam")){
                    radioButtonNam.setChecked(true);
                }
                else radioButtonNu.setChecked(true);
                String vitri = cauThu.getVitrida();
                if(vitri.contains("hauve")) {
                    cbHauVe.setChecked(true);
                }
                else cbHauVe.setChecked(false);
                if(vitri.contains("tienve")) {
                    cbTienVe.setChecked(true);
                }
                else cbTienVe.setChecked(false);
                if(vitri.contains("tiendao")) {
                    cbTienDao.setChecked(true);
                }
                else cbTienDao.setChecked(false);
                btnAdd.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnUpdate.setOnClickListener(v -> {
                    String ten = edtencauthu.getText().toString();
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    String gioitinh = radioId == R.id.radiobtnNam ? "nam" : "nu";
                    String ngaysinh = edngaysinh.getText().toString();
                    String vitri2 = "";
                    if(cbTienVe.isChecked()){
                        vitri2 += "tienve" +" ";
                    }
                    if(cbTienDao.isChecked()){
                        vitri2 += "tiendao" +" ";
                    }
                    if(cbHauVe.isChecked()){
                        vitri2 += "hauve" +" ";
                    }
                    cauThu.setTencauthu(ten);
                    cauThu.setGioitinh(gioitinh);
                    cauThu.setNgaysinh(ngaysinh);
                    cauThu.setVitrida(vitri2);
                    cauThuApater.updateItem(i,cauThu);
                    edtencauthu.setText("");
                    edngaysinh.setText("");
                    cbTienDao.setChecked(false);
                    cbHauVe.setChecked(false);
                    cbTienVe.setChecked(false);
                    radioButtonNam.setChecked(false);
                    radioButtonNu.setChecked(false);
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                });
            }
        });
        searchView.setOnQueryTextListener(this);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CauThu> fillterList = new ArrayList<>();
                String res = "";
                if(checkBox1.isChecked()) {
                    res+="tienve ";
                }
                if(checkBox2.isChecked()) {
                    res+="tiendao ";
                }
                if(checkBox3.isChecked()) {
                    res+="hauve ";
                }
                for (CauThu i : cauThuApater.getCauthuBackUp()){
                    if(i.getVitrida().toLowerCase().contains(res.toLowerCase())){
                        fillterList.add(i);
                    }else
                    if(res.toLowerCase().contains(i.getVitrida().toLowerCase())){
                        fillterList.add(i);
                    }
                }
                if(fillterList.isEmpty()){
                    Toast.makeText(MainActivity.this,"No data",Toast.LENGTH_SHORT).show();
                }
                else {
                    cauThuApater.fillterList(fillterList);
                }
            }
        });
    }
    private List<CauThu> getCauThuList() {
        List<CauThu> cauThuList = new ArrayList<>();
//        cauThuList.add(new CauThu("Văn Nam","15/3/2024","nam","hauve"));
//        cauThuList.add(new CauThu("Văn Nam","15/3/2024","nam","tienve"));
//        cauThuList.add(new CauThu("Văn Nam","15/3/2024","nam","tiendao"));
//        cauThuList.add(new CauThu("Văn Nam","15/3/2024","nam","hauve"));
//        cauThuList.add(new CauThu("Văn Nam","15/3/2024","nam","tienve"));
//        cauThuList.add(new CauThu("Văn Nam","15/3/2024","nam","tiendao"));
        return cauThuList;
    }
    private void initView() {
        edtencauthu = findViewById(R.id.edtencauthu);
        edngaysinh = findViewById(R.id.edngaysinh);
        radioButtonNam = findViewById(R.id.radiobtnNam);
        radioButtonNu = findViewById(R.id.radiobtnNu);
        radioGroup = findViewById(R.id.idradiogroud);
        cbTienDao = findViewById(R.id.cbtiendao);
        cbHauVe = findViewById(R.id.cbhauve);
        cbTienVe = findViewById(R.id.cbtienve);
        recyclerView = findViewById(R.id.recyview);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        btnSearch = findViewById(R.id.btn_search);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<CauThu> fillterList = new ArrayList<>();
        for (CauThu i : cauThuApater.getCauthuBackUp()){
            if(i.getVitrida().toLowerCase().contains(newText.toLowerCase())){
                fillterList.add(i);
            }
        }
        if(fillterList.isEmpty()){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else {
            cauThuApater.fillterList(fillterList);
        }
        return false;
    }
    private void showCheckboxes() {
        checkBox1.setVisibility(View.VISIBLE);
        checkBox2.setVisibility(View.VISIBLE);
        checkBox3.setVisibility(View.VISIBLE);
    }
}