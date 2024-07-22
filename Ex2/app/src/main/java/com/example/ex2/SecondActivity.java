package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {
    private Button btnBack;
    private TextView textView;
    private TextView textViewSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnBack = findViewById(R.id.back);
        textView = findViewById(R.id.second_text);
        Intent  t = getIntent();
        String name  = t.getStringExtra("name");
        textView.setText(name);
        textViewSub = findViewById(R.id.second_sub);
        String[] sub = t.getStringArrayExtra("sub");
        textViewSub.setText(Arrays.toString(sub));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}