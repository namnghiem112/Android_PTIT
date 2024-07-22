    package com.example.baitap3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitap3.model.Account;

    public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvUser, tvPass;
    private Button btLogin, btRegiter;
    private final static int REQEST_CODE = 1000;
    private Account user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btRegiter.setOnClickListener(this);
        btLogin.setOnClickListener(this);
    }

        private void initView() {
            tvUser = findViewById(R.id.editText);
            tvPass = findViewById(R.id.editTextText);
            btLogin = findViewById(R.id.button);
            btRegiter = findViewById(R.id.button_dky);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id==R.id.button ){
                Intent logIntent = new Intent(LoginActivity.this, MainActivity.class);
                Account account = new Account(tvUser.getText().toString(), tvPass.getText().toString());
                logIntent.putExtra("account",account);
                logIntent.putExtra("user",user);
                startActivity(logIntent);
            }
            else {
                Intent reIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(reIntent, REQEST_CODE);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == REQEST_CODE && resultCode == RESULT_OK){
                if(data==null) {
                    Toast.makeText(this,"Nguoi dung huy dang ky",Toast.LENGTH_SHORT).show();
                }
                else {
                    user = (Account) data.getSerializableExtra("data");
                    tvUser.setText(user.getUsername());
                    tvPass.setText(user.getPassword());
                }
            }
            else user = null;
        }
    }