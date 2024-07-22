package com.example.btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btlandroid.db.SQLiteHelper;
import com.example.btlandroid.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText userName,password;
    private Button btnLogin, googleLoginButton;

    private TextView txtSigup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        txtSigup = findViewById(R.id.txtSignup);
        googleLoginButton = findViewById(R.id.googleLoginButton);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = userName.getText().toString();
                String mPassword = password.getText().toString();

                User u = new User(mUsername,mPassword);
                SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                if(db.checkUser(u)){
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    Log.v("kiemtralis",db.getUserByUserName(mUsername).toString());
                    intent.putExtra("user", db.getUserByUserName(mUsername));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sai tai khoan hoac mat khau!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        txtSigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}