package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewEX extends AppCompatActivity
        implements CatAdapter.CatItemListener
{
    private RecyclerView recyclerView;
    private CatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.rview);
        adapter = new CatAdapter(getList());
        adapter.setCatItemListener(this);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
    private List<Cat>getList(){
        List<Cat>list = new ArrayList<>();
        list.add(new Cat(R.drawable.img,"Meo Con 1"));
        list.add(new Cat(R.drawable.img_1,"Meo Con 2"));
        list.add(new Cat(R.drawable.img_2,"Meo Con 3"));
        list.add(new Cat(R.drawable.img_3,"Meo Con 4"));
        list.add(new Cat(R.drawable.img_4,"Meo Con 5"));
        list.add(new Cat(R.drawable.img_5,"Meo Con 6"));
        return list;
    }

    @Override
    public void onItemLick(View view, int postion) {
        Cat c = getList().get(postion);
        Toast.makeText(this,c.getName(),Toast.LENGTH_SHORT).show();
    }
}