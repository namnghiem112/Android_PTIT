package com.example.booktablayout.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booktablayout.R;
import com.example.booktablayout.model.Book;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Book> list;
    private ItemListener itemListener;
    public RecycleViewAdapter(){
        list = new ArrayList<>();
    }
    public  void  setList(List<Book> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public Book getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Book item = list.get(position);
        holder.ten.setText(item.getTen());
        holder.cautruc.setText(item.getCautruc());
        holder.ngay.setText(item.getNgayxuathien());
        holder.dacovacxin.setText(item.getVacxin());
        holder.soluongtg.setText(item.getSoluongTG());
        holder.soluongvn.setText(item.getSoluongVN());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten,cautruc,ngay,dacovacxin,soluongvn, soluongtg;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvTen);
            cautruc = itemView.findViewById(R.id.tvCautruc);
            ngay = itemView.findViewById(R.id.tvngay);
            dacovacxin = itemView.findViewById(R.id.tvvacxin);
            soluongtg = itemView.findViewById(R.id.tvsoluongTG);
            soluongvn = itemView.findViewById(R.id.tvsoluongVN);
                itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemListener!=null){
                itemListener.onItemClick(v,getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view,int position);
    }
}
