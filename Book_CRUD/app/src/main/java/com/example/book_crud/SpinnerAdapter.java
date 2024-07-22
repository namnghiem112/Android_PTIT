package com.example.book_crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SpinnerAdapter extends BaseAdapter {
    private Context context;

    public SpinnerAdapter(Context context, int[] spinnerList) {
        this.context = context;
        this.spinnerList = spinnerList;
    }

    int[] spinnerList ={R.drawable.img,R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4,R.drawable.img_5};
    @Override
    public int getCount() {
        return spinnerList != null ? spinnerList.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.spinner,parent,false);
        ImageView imageView = view.findViewById(R.id.item_spinner);
        imageView.setImageResource(spinnerList[position]);
        return view;
    }
}
