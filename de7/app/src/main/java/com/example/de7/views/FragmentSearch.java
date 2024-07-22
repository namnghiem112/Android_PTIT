package com.example.de7.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.de7.R;
import com.example.de7.adapters.RecyclerViewAdapter;
import com.example.de7.db.SQLiteHelper;
import com.example.de7.models.CongViec;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private RadioButton radioButtonda, radioButtonchua;
    private Button btnSearch, btnStatistic;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private TextView thongke;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thongke = view.findViewById(R.id.edthongke);
        radioButtonchua = view.findViewById(R.id.radiochua);
        radioButtonda = view.findViewById(R.id.radioda);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnStatistic = view.findViewById(R.id.btnGetStatistic);
        recyclerView = view.findViewById(R.id.recyclerViewFragmentSearch);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
//        recyclerView.setAdapter(recyclerViewAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                List<CongViec> congViecList = sqLiteHelper.getAllCongViec();
                List<CongViec> congViecs = new ArrayList<>();
                String kiemtra ="";
                if(radioButtonchua.isChecked()){
                    kiemtra = "chua";
                }
                else kiemtra="da kich hoat";
                for(CongViec c : congViecList){
                    if(c.getKichhoat().equals(kiemtra)){
                        congViecs.add(c);
                    }
                }
                recyclerViewAdapter.setListCongViec(congViecs);
            }
        });

        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                List<CongViec> tmp = sqLiteHelper.getAllCongViec();
                String res=  "";
                int t1 = 0 ;
                int t3 = 0 ;
                int t2 = 0 ;
                int t4 = 0 ;
                int t5 = 0 ;
                int t6 = 0 ;
                int t7 = 0 ;
                int t8 = 0 ;
                int t9 = 0 ;
                int t10 = 0 ;
                int t11 = 0 ;
                int t12 = 0 ;
                for(CongViec i : tmp){

                    if(i.getNgaybatdau().substring(3,5).equals("1")){
                        t1++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("2")){
                        t2++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("3")){
                        t3++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("4")){
                        t4++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("5")){
                        t5++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("6")){
                        t6++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("7")){
                        t7++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("8")){
                        t8++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("9")){
                        t9++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("10")){
                        t10++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("11")){
                        t11++;
                    }
                    if(i.getNgaybatdau().substring(3,5).equals("12")){
                        t12++;
                    }
                }
                res = "Thang 1 co:"+t1+"\n Thang 2 co:"+t2+"\nThang 3 co: "+ t3+"\nThang 4 co:"+t4+"\n Thang 5 co:"+t5+"\nThang 6 co: "+t6+
                        "\nThang 7 co:"+t7+"\n Thang 8 co:"+t8+"\nThang 9 co: " + t9+"\nThang 10 co:"+t10+"\nThang 11 co"+t11+"\nThang 12 co: "+ t12;
                thongke.setText(res);
            }
        });

        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(CongViec congviec) {
                Intent intentOpenUpdateDeleteActivity = new Intent(getActivity(),UpdateDeleteActivity.class);
                intentOpenUpdateDeleteActivity.putExtra("congviec", congviec);
                startActivity(intentOpenUpdateDeleteActivity);
            }
        });

    }
}
