package com.example.demo.inventory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private Context context;
    public List<Tour> tourList;
    // EDIT Bước 1
    public interface onMyItemClickListener {
        void doSomething(int position);
    }
    private onMyItemClickListener myItemClickListener;

    public void setMyItemClickListener(onMyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }
    ///
    public TourAdapter() {
    }

    public TourAdapter(Context context, List<Tour> tourList) {
        this.context = context;
        this.tourList = tourList;
    }

    public TourAdapter(List<Tour> tourList) {
        this.tourList = tourList;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tour tour = tourList.get(position);
        int imgId = 0;
        if(tour.getPhgtien()==Tour.OTO){
            imgId = R.drawable.oto;
        }
        else if(tour.getPhgtien()==Tour.XEMAY){
            imgId = R.drawable.xemay;
        }
        else if(tour.getPhgtien()==Tour.MAYBAY){
            imgId = R.drawable.maybay;
        }
        holder.img.setImageResource(imgId);
        holder.textView.setText(tour.getLichtrinh()+" - "+tour.getThoigian());
        ///Bước 2
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myItemClickListener.doSomething(position);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(tour);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tourList!=null ? tourList.size() :0;
    }
    public void addItem(Tour tour){
        tourList.add(tour);
        notifyDataSetChanged();
    }
    public void deleteItem(Tour tour){
        tourList.remove(tour);
        notifyDataSetChanged();
    }
    public void upDateItem(int position ,Tour tour){
        tourList.set(position,tour);
        notifyDataSetChanged();
    }
    public class TourViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView textView;
        private Button btnDelete;
        private CardView cardView;
        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
            textView = itemView.findViewById(R.id.item_textview);
            btnDelete = itemView.findViewById(R.id.item_btn_delete);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
