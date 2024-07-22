package com.example.btlandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.btlandroid.R;
import com.example.btlandroid.model.SinhVien;

import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.SinhVienViewHolder2> {
    private List<SinhVien> listSinhVien;

    public RecyclerViewAdapter2(List<SinhVien> listSinhVien) {
        this.listSinhVien = listSinhVien;
    }

    public List<SinhVien> getlistSinhVien() {
        return listSinhVien;
    }

    public void setlistSinhVien(List<SinhVien> listSinhVien) {
        this.listSinhVien = listSinhVien;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SinhVienViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_adapter2,parent,false);
        return new SinhVienViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder2 holder, int position) {
        SinhVien sinhvien = listSinhVien.get(position);
        holder.txtChuaht.setText(sinhvien.getKythuctap());
        holder.textsoluong.setText(sinhvien.getSoluong() + "");
    }

    @Override
    public int getItemCount() {
         return listSinhVien!=null ? listSinhVien.size() : 0;
    }

    public class SinhVienViewHolder2 extends RecyclerView.ViewHolder {
        private TextView txtChuaht, textsoluong;
        public SinhVienViewHolder2(@NonNull View itemView) {
            super(itemView);
            txtChuaht = itemView.findViewById(R.id.textchuaht);
            textsoluong = itemView.findViewById(R.id.textsoluong);
        }
    }
}