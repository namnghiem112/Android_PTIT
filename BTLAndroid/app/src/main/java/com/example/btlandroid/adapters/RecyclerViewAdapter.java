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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SinhVienViewHolder>{
    public interface ItemClickListener{
        void onItemClick(SinhVien sinhvien);
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setListSinhVien(List<SinhVien> listCongViec) {
        this.listSinhVien = listCongViec;
        notifyDataSetChanged();
    }

    public List<SinhVien> getListSinhVien() {
        return listSinhVien;
    }

    private List<SinhVien> listSinhVien;

    public RecyclerViewAdapter(List<SinhVien> listSinhVien) {
        this.listSinhVien = listSinhVien;
    }

    public class SinhVienViewHolder extends RecyclerView.ViewHolder{
        private TextView tvtensinhvien, tvemail, tvsodienthoai, tvquequan, tvngaysinh, tvkythuctap;
        public SinhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtensinhvien = itemView.findViewById(R.id.tvtensinhvien);
            tvemail = itemView.findViewById(R.id.tvemail);
            tvsodienthoai = itemView.findViewById(R.id.tvsodienthoai);
            tvquequan = itemView.findViewById(R.id.tvquequan);
            tvngaysinh = itemView.findViewById(R.id.tvngaysinh);
            tvkythuctap = itemView.findViewById(R.id.tvkythuctap);
        }
    }
    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new SinhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder SinhVienViewHolder, int position) {
        SinhVien sinhvien = listSinhVien.get(position);
        SinhVienViewHolder.tvtensinhvien.setText(sinhvien.getTensinhvien());
        SinhVienViewHolder.tvemail.setText(sinhvien.getEmail());
        SinhVienViewHolder.tvsodienthoai.setText(sinhvien.getSodienthoai());
        SinhVienViewHolder.tvquequan.setText(sinhvien.getQuequan());
        SinhVienViewHolder.tvngaysinh.setText(sinhvien.getNgaysinh());
        SinhVienViewHolder.tvkythuctap.setText(sinhvien.getKythuctap());

        SinhVienViewHolder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(sinhvien);
        });
    }

    @Override
    public int getItemCount() {
        return listSinhVien!=null ? listSinhVien.size() : 0;
    }

}
