package com.example.baitap3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.baitap3.model.Account;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.text_main);
        Intent intent = getIntent();
        if(intent.getSerializableExtra("account")!=null && intent.getSerializableExtra("user")!=null){
            Account log = (Account) intent.getSerializableExtra("account");
            Account user = (Account) intent.getSerializableExtra("user");
            if(log.getUsername().equals(user.getUsername()) && log.getPassword().equals(user.getPassword())){
                txt.setText("Thanh cong");
            }
            else txt.setText("That Bai");
        }
    }
}