package com.example.ex2;

import com.example.ex2.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{
    private List<Cat> mlist;
    private Context context;
    private CatItemListener catItemListener;

    public CatAdapter(List<Cat> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    public CatAdapter(List<Cat> mlist){
        this.mlist = mlist;
    }
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mlist.get(position);
        if(cat==null) return;
        holder.img.setImageResource(cat.getImg());
        holder.tv.setText(cat.getName());
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context.getApplicationContext(),cat.getName(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(mlist!=null) return mlist.size();
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        private ImageView img;
        private TextView tv;
        private CardView cardView;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            tv = itemView.findViewById(R.id.tname);
//            cardView = itemView.findViewById(R.id.cview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(catItemListener!=null) {
                catItemListener.onItemLick(itemView,getAdapterPosition());
            }
        }
    }
    public interface CatItemListener {
        public void onItemLick (View view , int postion);
    }
}
