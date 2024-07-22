    package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.demo.inventory.PhuongTien;
import com.example.demo.inventory.Tour;
import com.example.demo.inventory.TourAdapter;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends AppCompatActivity {
        private EditText editText_lichtrinh, editText_thoigian;
        private Button btnAdd, btnUpdate;
        private Spinner spinner;
        private PhuongTienAdapter adapter;
        private TourAdapter tourAdapter;
        private RecyclerView recyclerView;
        private PhuongTien phuongTien;
        private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        spinner = findViewById(R.id.spinner);
        adapter = new PhuongTienAdapter(MainActivity.this, getPhuongTienList());
        spinner.setAdapter(adapter);
        tourAdapter = new TourAdapter(getTourList());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(tourAdapter);
        ///Bước 3
        tourAdapter.setMyItemClickListener(new TourAdapter.onMyItemClickListener() {
            @Override
            public void doSomething(int position) {
                currentPosition = position;
                editText_lichtrinh.setText(tourAdapter.tourList.get(position).getLichtrinh());
                editText_thoigian.setText(tourAdapter.tourList.get(position).getThoigian());
                String phuongtien = tourAdapter.tourList.get(position).getPhgtien();
                int i =0;
                if(phuongtien.equals("oto")){
                    i = 0;
                }
                else if(phuongtien.equals("maybay")){
                    i = 1;
                }
                else i =2;
                spinner.setSelection(i);
                btnAdd.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tour tour = new Tour();
                        String lichtrinh =editText_lichtrinh.getText().toString();
                        String thoigian = editText_thoigian.getText().toString();
                        spinner = findViewById(R.id.spinner);
                        String imgid = spinner.getSelectedItem().toString();
                        Log.v("kiem",imgid);
                        String phuongtien ="";
                        if(imgid.equals("0")){
                            phuongtien = "oto";
                        }
                        else if(imgid.equals("1")) {
                            phuongtien="maybay";
                        }
                        else phuongtien ="xemay";
                        tour.setLichtrinh(lichtrinh);
                        tour.setPhgtien(phuongtien);
                        tour.setThoigian(thoigian);
                        tourAdapter.upDateItem(position,tour);
                        editText_thoigian.setText("");
                        editText_lichtrinh.setText("");
                    }
                });
            }
        });
        btnAdd.setOnClickListener(v -> {
            Tour tour = new Tour();
            String lichtrinh =editText_lichtrinh.getText().toString();
            String thoigian = editText_thoigian.getText().toString();
            spinner = findViewById(R.id.spinner);
            String imgid = spinner.getSelectedItem().toString();
            Log.v("kiem",imgid);
            String phuongtien ="";
            if(imgid.equals("0")){
                phuongtien = "oto";
            }
            else if(imgid.equals("1")) {
                phuongtien="maybay";
            }
            else phuongtien ="xemay";
            tour.setLichtrinh(lichtrinh);
            tour.setPhgtien(phuongtien);
            tour.setThoigian(thoigian);
            tourAdapter.addItem(tour);
            editText_thoigian.setText("");
            editText_lichtrinh.setText("");
        });
    }

        private List<Tour> getTourList() {
            List<Tour> tourList = new ArrayList<>();
            tourList.add(new Tour("Hà Nội - SaPa","3 ngày 2 đêm","maybay"));
            tourList.add(new Tour("Hà Nội - SaPa","3 ngày 2 đêm","oto"));
            tourList.add(new Tour("Hà Nội - SaPa","3 ngày 2 đêm","xemay"));
            return tourList;
        }

        private List<PhuongTien> getPhuongTienList() {
            List<PhuongTien> phuongTienList = new ArrayList<>();
            phuongTienList.add(new PhuongTien("oto",R.drawable.oto));
            phuongTienList.add(new PhuongTien("maybay",R.drawable.maybay));
            phuongTienList.add(new PhuongTien("xemay",R.drawable.xemay));
            return phuongTienList;
        }
        private void initView() {
            recyclerView = findViewById(R.id.recy_view);
            editText_lichtrinh = findViewById(R.id.edit_lichtrinh);
            editText_thoigian = findViewById(R.id.edit_thoigian);
            btnAdd = findViewById(R.id.btn_add);
            btnUpdate = findViewById(R.id.btn_edit);
            btnUpdate.setEnabled(false);
        }
    }