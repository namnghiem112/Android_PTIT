package com.example.btlandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlandroid.adapters.RecyclerViewAdapter;
import com.example.btlandroid.adapters.RecyclerViewAdapter2;
import com.example.btlandroid.db.SQLiteHelper;
import com.example.btlandroid.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private EditText edtensinhvien;
    private Button btnSearch, btnStatistic;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerViewAdapter2 recyclerViewAdapter2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtensinhvien = view.findViewById(R.id.edtensinhviensearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnStatistic = view.findViewById(R.id.btnGetStatistic);
        recyclerView = view.findViewById(R.id.recyclerViewFragmentSearch);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
//        recyclerView.setAdapter(recyclerViewAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerViewAdapter2 = new RecyclerViewAdapter2(new ArrayList<>());

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                List<SinhVien> sinhVienListByName = sqLiteHelper.findSinhVienByName(edtensinhvien.getText().toString().trim());
                Log.v("dong56",sinhVienListByName.toString());
                recyclerViewAdapter.setListSinhVien(sinhVienListByName);
            }
        });

        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(recyclerViewAdapter2);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                List<SinhVien> statistic = sqLiteHelper.getStatistic();
                Log.v("kiemtrahihi",String.valueOf(statistic.size()));
                recyclerViewAdapter2.setlistSinhVien(statistic);
            }
        });

        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(SinhVien sinhvien) {
                Intent intentOpenUpdateDeleteActivity = new Intent(getActivity(),UpdateDeleteActivity.class);
                intentOpenUpdateDeleteActivity.putExtra("sinhvien", sinhvien);
                startActivity(intentOpenUpdateDeleteActivity);
            }
        });

    }
}
