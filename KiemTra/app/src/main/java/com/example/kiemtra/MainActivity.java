package com.example.kiemtra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kiemtra.model.Bao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private EditText edtenbaibao, edtentacgia, edgioduatin;
    private CheckBox cbphephan, cbsuthat, cbchambiem;
    private Button btnAdd, btnUpdate;
    private RecyclerView recyclerView;
    private BaoAdapter baoAdapter;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        edgioduatin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hh = c.get(Calendar.HOUR_OF_DAY);
                int mm = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edgioduatin.setText(hourOfDay+":"+minute);
                    }
                },hh,mm,false);
                timePickerDialog.show();
            }
        });
        baoAdapter = new BaoAdapter(getList());
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(baoAdapter);
        btnAdd.setOnClickListener(v -> {
            String tenbaibao = edtenbaibao.getText().toString();
            String tentacgia = edtentacgia.getText().toString();
            String gioaduatin = edgioduatin.getText().toString();
            String kieubai = "";
            if(cbchambiem.isChecked()){
                kieubai += "chambiem" +" ";
            }
            if(cbsuthat.isChecked()){
                kieubai += "suthat" +" ";
            }
            if(cbphephan.isChecked()){
                kieubai += "phephan" +" ";
            }
            Bao bao = new Bao(tenbaibao,tentacgia,gioaduatin,kieubai);
            baoAdapter.addItem(bao);
            edtentacgia.setText("");
            edtenbaibao.setText("");
            edgioduatin.setText("");
            cbphephan.setChecked(false);
            cbsuthat.setChecked(false);
            cbchambiem.setChecked(false);
        });
        baoAdapter.setMyItemClick(new BaoAdapter.myItemClick() {
            @Override
            public void doSomething(int i) {
                Bao bao = baoAdapter.list.get(i);
                edtenbaibao.setText(bao.getTenbaibao());
                edtentacgia.setText(bao.getTentacgia());
                edgioduatin.setText(bao.getGioduatin());
                String kieubai = bao.getKieubai();
                if(kieubai.contains("suthat")) {
                    cbsuthat.setChecked(true);
                }
                else cbsuthat.setChecked(false);
                if(kieubai.contains("phephan")) {
                    cbphephan.setChecked(true);
                }
                else cbphephan.setChecked(false);
                if(kieubai.contains("chambiem")) {
                    cbchambiem.setChecked(true);
                }
                else cbchambiem.setChecked(false);
                btnAdd.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnUpdate.setOnClickListener(v -> {
                    String tenbaibao = edtenbaibao.getText().toString();
                    String tentacgia = edtentacgia.getText().toString();
                    String gioaduatin = edgioduatin.getText().toString();
                    String kieubai2 = "";
                    if(cbchambiem.isChecked()){
                        kieubai2 += "chambiem" +" ";
                    }
                    if(cbsuthat.isChecked()){
                        kieubai2 += "suthat" +" ";
                    }
                    if(cbphephan.isChecked()){
                        kieubai2 += "phephan" +" ";
                    }
                    bao.setTenbaibao(tenbaibao);
                    bao.setTentacgia(tentacgia);
                    bao.setGioduatin(gioaduatin);
                    bao.setKieubai(kieubai2);
                    baoAdapter.updateItem(i,bao);
                    edtentacgia.setText("");
                    edtenbaibao.setText("");
                    edgioduatin.setText("");
                    cbphephan.setChecked(false);
                    cbsuthat.setChecked(false);
                    cbchambiem.setChecked(false);
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                });
            }
        });
        searchView.setOnQueryTextListener(this);
    }
    private List<Bao> getList() {
        List<Bao> cauThuList = new ArrayList<>();
//        cauThuList.add(new Bao("Phá nát đình cổ 2000 năm","Văn Nam","16:02","suthat"));
//        cauThuList.add(new Bao("Phá nát đình cổ 2000 năm","Văn Nam","16:02","phephan"));
//        cauThuList.add(new Bao("Phá nát đình cổ 2000 năm","Văn Nam","16:02","chambiem"));
//        cauThuList.add(new Bao("Phá nát đình cổ 2000 năm","Văn Nam","16:02","phaphan"));
//        cauThuList.add(new Bao("Phá nát đình cổ 2000 năm","Văn Nam","16:02","suthat"));
        return cauThuList;
    }
    private void initView() {
        edtenbaibao = findViewById(R.id.edtenbaibao);
        edtentacgia = findViewById(R.id.edtentacgia);
        edgioduatin = findViewById(R.id.edgioduatin);
        cbphephan = findViewById(R.id.cbphephan);
        cbsuthat = findViewById(R.id.cbsuthat);
        cbchambiem = findViewById(R.id.cbchambiem);
        recyclerView = findViewById(R.id.recyview);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Bao> fillterList = new ArrayList<>();
        for (Bao i : baoAdapter.getBackUp()){
            if(i.getTenbaibao().toLowerCase().contains(newText.toLowerCase())){
                fillterList.add(i);
            }
        }
        if(fillterList.isEmpty()){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else {
            baoAdapter.fillterList(fillterList);
        }
        return false;
    }
}