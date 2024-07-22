package com.example.chapter4and5.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter4and5.R;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{
    //private Context context;
    private List<Cat> list;

    public CatItemListener catItemListener;

    public CatAdapter(List<Cat> list) {
        //this.context = context;
        this.list = list;

    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // tra ve 1 view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position)  {
        Cat cat = list.get(position);
        if(cat == null){
            return;
        }
        holder.imageView.setImageResource(cat.getImg());
        holder.textView.setText(cat.getName());
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context.getApplicationContext(), cat.getName(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // khai bao nhung thu co trong item.xml
        private ImageView imageView;
        private TextView textView;
        //private CardView cardView;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.textView);
            imageView.setOnClickListener(this);
            //cardView = itemView.findViewById(R.id.cardView);

        }

        @Override
        public void onClick(View v) {
            if(catItemListener != null){
                catItemListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    // cach thuong dung de bat su kien trong recyler view
    public interface CatItemListener{
        public void onItemClick(View view, int position);
        // viet them phuong thuc neu can. vi du: add, delete, update, ...

    }
}
