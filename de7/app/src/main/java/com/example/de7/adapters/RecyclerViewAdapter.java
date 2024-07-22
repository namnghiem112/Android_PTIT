package com.example.de7.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.de7.R;
import com.example.de7.models.CongViec;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CongViecViewHolder>{
    public interface ItemClickListener{
        void onItemClick(CongViec congviec);
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setListCongViec(List<CongViec> listCongViec) {
        this.listCongViec = listCongViec;
        notifyDataSetChanged();
    }

    public List<CongViec> getListCognViec() {
        return listCongViec;
    }

    private List<CongViec> listCongViec;

    public RecyclerViewAdapter(List<CongViec> listCongViec) {
        this.listCongViec = listCongViec;
    }

    public class CongViecViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvhocphi, tvPublishDate, tvchuyennganh, tvkichhoat;
        public CongViecViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvhocphi = itemView.findViewById(R.id.tvhocphi);
            tvPublishDate = itemView.findViewById(R.id.tvPublishDate);
            tvchuyennganh = itemView.findViewById(R.id.tvchuyennganh);
            tvkichhoat = itemView.findViewById(R.id.tvkichoat);
        }
    }
    @NonNull
    @Override
    public CongViecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new CongViecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CongViecViewHolder CongViecViewHolder, int position) {
//        CongViecViewHolder CongViecViewHolder = (CongViecViewHolder) holder;
        CongViec congviec = listCongViec.get(position);
        CongViecViewHolder.tvName.setText(congviec.getTen());
        CongViecViewHolder.tvhocphi.setText(congviec.getHocphi());
        CongViecViewHolder.tvPublishDate.setText(congviec.getNgaybatdau());
        CongViecViewHolder.tvchuyennganh.setText(congviec.getChuyennganh());
        CongViecViewHolder.tvkichhoat.setText(congviec.getKichhoat());

        CongViecViewHolder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(congviec);
        });
    }

    @Override
    public int getItemCount() {
        return listCongViec!=null ? listCongViec.size() : 0;
    }

}
