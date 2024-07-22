package com.example.baitap3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitap3.model.Account;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvUser, tvPass;
    private Button btCancel, btRegiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        btCancel.setOnClickListener(this);
        btRegiter.setOnClickListener(this);
    }
    private void initView() {
        tvUser = findViewById(R.id.editText);
        tvPass = findViewById(R.id.editTextText);
        btCancel = findViewById(R.id.buttonCancel);
        btRegiter = findViewById(R.id.btnRegiter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.btnRegiter ){
            Intent intent = new Intent();
            Account account = new Account(tvUser.getText().toString(), tvPass.getText().toString());
            intent.putExtra("data",account);
            setResult(RESULT_OK,intent);
            finish();
        }
        else {
            setResult(RESULT_CANCELED,null);
            finish();
        }
    }
}