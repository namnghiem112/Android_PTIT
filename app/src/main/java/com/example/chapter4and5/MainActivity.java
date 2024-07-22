package com.example.chapter4and5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.chapter4and5.model.Cat;
import com.example.chapter4and5.model.CatAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener{
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        catAdapter = new CatAdapter(getList());
        catAdapter.setCatItemListener(this);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(catAdapter);
    }

    private List<Cat> getList(){
        List<Cat> list = new ArrayList<>();
        list.add(new Cat(R.drawable.img, "Cat 1"));
        list.add(new Cat(R.drawable.img_1, "Cat 2"));
        list.add(new Cat(R.drawable.img_2, "Cat 3"));
        list.add(new Cat(R.drawable.img_3, "Cat 4"));
        list.add(new Cat(R.drawable.img_4, "Cat 5"));
        list.add(new Cat(R.drawable.img_5, "Cat 6"));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(View view, int position) {
        Cat c = getList().get(position);
        Toast.makeText(this, c.getName(), Toast.LENGTH_SHORT).show();
    }
}