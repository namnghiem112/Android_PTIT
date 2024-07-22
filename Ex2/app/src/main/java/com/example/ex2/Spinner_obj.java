package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.ex2.inventory.Data;

public class Spinner_obj extends AppCompatActivity {
    private Spinner spinner;
    private FruitAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_obj);
        spinner = findViewById(R.id.spinner_fruits);
        adapter = new FruitAdapter(Spinner_obj.this, Data.getFruitList());
        spinner.setAdapter(adapter);
    }
}