package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class tinhtoan extends AppCompatActivity {
    private Spinner sp1;
    private Button btnrun;
    private TextView textView;
    private EditText nhapso1, nhapso2;
    private String kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinhtoan);
        sp1 = findViewById(R.id.spinner);
        String[] list={"+","-","x",":"};
        nhapso1 = findViewById(R.id.editTextText5);
        nhapso2 = findViewById(R.id.editTextText6);
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list);
        sp1.setAdapter(adapter1);
        btnrun = findViewById(R.id.button16);
        btnrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = nhapso1.getText().toString();
                String b = nhapso2.getText().toString();
                String phenacetin = sp1.getSelectedItem().toString();
                Log.d("KetQuaPhepTinh", phenacetin);
                if(phenacetin.equals("x")){
                    int c = Integer.parseInt(a)*Integer.parseInt(b);
                    kq = String.valueOf(c);
                }
                else if(phenacetin.equals("+")){
                    int c = Integer.parseInt(a)+Integer.parseInt(b);
                    kq = String.valueOf(c);
                }
                else if(phenacetin.equals("-")){
                    int c = Integer.parseInt(a)-Integer.parseInt(b);
                    kq = String.valueOf(c);
                }
                else {
                    int c = Integer.parseInt(a)/Integer.parseInt(b);
                    kq = String.valueOf(c);
                }
                textView = findViewById(R.id.textView13);
                textView.setText("Kết quả là "+ kq);
            }
        });
    }
}