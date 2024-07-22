package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.inventory.PhuongTien;

import java.util.List;

public class PhuongTienAdapter extends BaseAdapter {
    private Context context;
    private List<PhuongTien> phuongTienList;

    public PhuongTienAdapter(Context context, List<PhuongTien> phuongTienList) {
        this.context = context;
        this.phuongTienList = phuongTienList;
    }

    @Override
    public int getCount() {
        return phuongTienList!=null ?phuongTienList.size() : 0;
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
    public View getView(int i, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_spinner, parent, false);

        TextView txtName = rootView.findViewById(R.id.name);
        ImageView image = rootView.findViewById(R.id.image);

        txtName.setText(phuongTienList.get(i).getName());
        image.setImageResource(phuongTienList.get(i).getImage());

        return rootView;
    }
}
