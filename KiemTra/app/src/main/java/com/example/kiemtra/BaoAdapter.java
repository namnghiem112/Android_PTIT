package com.example.kiemtra;

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

import com.example.kiemtra.model.Bao;

import java.util.ArrayList;
import java.util.List;

public class BaoAdapter extends RecyclerView.Adapter<BaoAdapter.BaoViewHolder>{
    public List<Bao> list;
    private Context context;
    public List<Bao> backUp;
    public BaoAdapter(List<Bao> list) {
        this.list = list;
        this.backUp = new ArrayList<>();
    }
    public void fillterList(List<Bao> fillter){
        this.list = fillter;
        notifyDataSetChanged();
    }

    public List<Bao> getBackUp() {
        return backUp;
    }

    public BaoAdapter(List<Bao> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public interface myItemClick {
        void doSomething(int i);
    }
    private myItemClick myItemClick;

    public void setMyItemClick(BaoAdapter.myItemClick myItemClick) {
        this.myItemClick = myItemClick;
    }

    @NonNull
    @Override
    public BaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new BaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaoViewHolder holder, int position) {
        Bao bao = list.get(position);
        holder.textViewTenBaiBao.setText(bao.getTenbaibao());
        holder.textViewGio.setText(bao.getGioduatin());
        if(bao.getKieubai().contains("phephan")){
            holder.checkBoxPhePhan.setChecked(true);
        }
        else holder.checkBoxPhePhan.setChecked(false);
        if(bao.getKieubai().contains("suthat")){
            holder.checkBoxSuThat.setChecked(true);
        }
        else holder.checkBoxSuThat.setChecked(false);
        if(bao.getKieubai().contains("chambiem")){
            holder.checkBoxChamBiem.setChecked(true);
        }
        else holder.checkBoxChamBiem.setChecked(false);
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
                builder.setMessage("Bạn có chắc chắn muốn xóa "+bao.getTenbaibao()+" hay không");
                builder.setIcon(R.drawable.img_1);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(bao);
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

    @Override
    public int getItemCount() {
        return list!=null ? list.size() : 0;
    }
    public void addItem(Bao bao) {
        list.add(bao);
        backUp.add(bao);
        notifyDataSetChanged();
    }
    private void deleteItem(Bao bao) {
        list.remove(bao);
        backUp.remove(bao);
        notifyDataSetChanged();
    }
    public void updateItem(int i, Bao bao) {
        list.set(i,bao);
        backUp.set(i,bao);
        notifyDataSetChanged();
    }
    public class BaoViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTenBaiBao, textViewGio;
        private CheckBox checkBoxPhePhan, checkBoxSuThat, checkBoxChamBiem;
        private Button btnDelete;
        private CardView cardView;
        public BaoViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            textViewGio = itemView.findViewById(R.id.item_text_gio);
            textViewTenBaiBao = itemView.findViewById(R.id.item_text);
            checkBoxPhePhan = itemView.findViewById(R.id.item_cbphephan);
            checkBoxSuThat = itemView.findViewById(R.id.item_cbsuthat);
            checkBoxChamBiem = itemView.findViewById(R.id.item_cbchambiem);
            btnDelete = itemView.findViewById(R.id.item_btn_delete);
        }
    }
}
