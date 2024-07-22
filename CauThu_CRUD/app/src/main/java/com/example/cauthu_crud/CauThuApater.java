package com.example.cauthu_crud;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cauthu_crud.model.CauThu;

import java.util.ArrayList;
import java.util.List;

public class CauThuApater extends RecyclerView.Adapter<CauThuApater.CauThuViewHolder> {
    public List<CauThu> cauThuList;
    private Context context;
    List<CauThu> cauthuBackUp;
    public CauThuApater(List<CauThu> cauThuList) {
        this.cauThuList = cauThuList;
        this.cauthuBackUp = new ArrayList<>();
    }
    public void fillterList(List<CauThu> fillter){
        this.cauThuList = fillter;
        notifyDataSetChanged();
    }

    public List<CauThu> getCauthuBackUp() {
        return cauthuBackUp;
    }

    public void setCauthuBackUp(List<CauThu> cauthuBackUp) {
        this.cauthuBackUp = cauthuBackUp;
    }

    public CauThuApater(List<CauThu> cauThuList, Context context) {
        this.cauThuList = cauThuList;
        this.context = context;
    }
    public interface myItemClick {
        void doSomething(int i);
    }
    private myItemClick myItemClick;

    public void setMyItemClick(CauThuApater.myItemClick myItemClick) {
        this.myItemClick = myItemClick;
    }

    @NonNull
    @Override
    public CauThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new CauThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CauThuViewHolder holder, int position) {
        CauThu cauThu = cauThuList.get(position);
        int imgId = cauThu.getGioitinh().equals("nam") ? R.drawable.male : R.drawable.famale;
        holder.imageView.setImageResource(imgId);
        holder.textViewTenCauThu.setText(cauThu.getTencauthu());
        if(cauThu.getVitrida().contains("tienve")){
            holder.checkBoxTienVe.setChecked(true);
            holder.checkBoxTienVe.setEnabled(true);
        }
        else {
            holder.checkBoxTienVe.setChecked(false);
            holder.checkBoxTienVe.setEnabled(false);
        }
        if(cauThu.getVitrida().contains("tiendao")){
            holder.checkBoxTienDao.setChecked(true);
            holder.checkBoxTienDao.setEnabled(true);
        }
        else {
            holder.checkBoxTienDao.setChecked(false);
            holder.checkBoxTienDao.setEnabled(false);
        }
        if(cauThu.getVitrida().contains("hauve")){
            holder.checkBoxHauVe.setEnabled(true);
            holder.checkBoxHauVe.setChecked(true);
        }
        else {
            holder.checkBoxHauVe.setChecked(false);
            holder.checkBoxHauVe.setEnabled(false);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myItemClick.doSomething(position);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                Log.v("kiemtranua2","1");
                builder.setTitle("Thông báo xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa "+cauThu.getTencauthu()+" hay không");
                builder.setIcon(R.drawable.img_1);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(cauThu);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void addItem(CauThu cauThu) {
        cauThuList.add(cauThu);
        cauthuBackUp.add(cauThu);
        notifyDataSetChanged();
    }
    private void deleteItem(CauThu cauThu) {
        cauThuList.remove(cauThu);
        cauthuBackUp.remove(cauThu);
        notifyDataSetChanged();
    }
    public void updateItem(int i, CauThu cauThu) {
        cauThuList.set(i,cauThu);
        cauthuBackUp.set(i,cauThu);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cauThuList!=null ?cauThuList.size() :0;
    }

    public class CauThuViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTenCauThu;
        private ImageView imageView;
        private CheckBox checkBoxHauVe, checkBoxTienVe, checkBoxTienDao;
        private Button btnDelete;
        private CardView cardView;
        public CauThuViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            textViewTenCauThu = itemView.findViewById(R.id.item_text);
            imageView = itemView.findViewById(R.id.item_img_view);
            checkBoxHauVe = itemView.findViewById(R.id.item_cbhauve);
            checkBoxTienVe = itemView.findViewById(R.id.item_cbtienve);
            checkBoxTienDao = itemView.findViewById(R.id.item_cbtiendao);
            btnDelete = itemView.findViewById(R.id.item_btn_delete);
        }
    }
}
