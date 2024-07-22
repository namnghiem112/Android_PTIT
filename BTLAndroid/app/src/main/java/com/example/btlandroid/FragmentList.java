package com.example.btlandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.btlandroid.adapters.RecyclerViewAdapter;
import com.example.btlandroid.db.SQLiteHelper;
import com.example.btlandroid.model.SinhVien;

import java.util.List;

public class FragmentList extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewFragmentList);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        List<SinhVien> allSinhVien = sqLiteHelper.getAllSinhVien();
//        Log.v("kiemtrasinhvien2",String.valueOf(sqLiteHelper.addSinhVien(new SinhVien(1,"n","n","n","n","n","n"))));
////         Hiển thị list ra
        recyclerViewAdapter = new RecyclerViewAdapter(allSinhVien);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(SinhVien sinhvien) {
                Intent intentOpenUpdateDeleteActivity = new Intent(getActivity(),UpdateDeleteActivity.class);
                intentOpenUpdateDeleteActivity.putExtra("sinhvien", sinhvien);
                startActivity(intentOpenUpdateDeleteActivity);
            }
        });
    }
//     làm tươi lại list
    @Override
    public void onResume() {
        super.onResume();
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        List<SinhVien> allSinhVien = sqLiteHelper.getAllSinhVien();
        recyclerViewAdapter.setListSinhVien(allSinhVien);
    }
}
