package com.example.ex4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner sp1, sp2;
    private CheckBox android, iphone, windown;
    private RadioGroup idgr;
    private RatingBar ratingBar;
    private CheckBox tennis, running, swimming, sleeping, reading;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp2= findViewById(R.id.spinner1);
        sp1= findViewById(R.id.spinner2);
        String[] list={"PTIT","HUST","NEU","FTU"};
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
        sp2.setAdapter(adapter2);
        String[] list1={"Viet Nam","Lao","Nhat Ban","Trung Quoc"};
        ArrayAdapter<String>adapter1=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list1);
        sp1.setAdapter(adapter1);
        android = findViewById(R.id.checkBox);
        iphone = findViewById(R.id.checkBox2);
        windown = findViewById(R.id.checkBox3);
        ratingBar = findViewById(R.id.ratingBar);
        tennis = findViewById(R.id.checkBox4);
        running = findViewById(R.id.checkBox5);
        swimming = findViewById(R.id.checkBox6);
        sleeping = findViewById(R.id.checkBox7);
        reading = findViewById(R.id.checkBox8);
        idgr = findViewById(R.id.idgr);
        btn = findViewById(R.id.button15);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String platform ="";
                if(android.isChecked()){
                    platform += android.getText().toString()+"-";
                }
                if(iphone.isChecked()){
                    platform += iphone.getText().toString()+"-";
                }
                if(windown.isChecked()){
                    platform+= windown.getText().toString();
                }
                // gender
                String gender = "";
                int select = idgr.getCheckedRadioButtonId();
                RadioButton radiobtn = findViewById(select);
                gender = radiobtn.getText().toString();
                String raingbar = String.valueOf(ratingBar.getRating());
                String country = sp1.getSelectedItem().toString() + " "+ sp2.getSelectedItem().toString();
                String fav = "";
                if(tennis.isChecked()){
                    fav += tennis.getText().toString()+"-";
                }
                if(running.isChecked()){
                    fav += running.getText().toString()+"-";
                }
                if(swimming.isChecked()){
                    fav += swimming.getText().toString()+"-";
                }
                if(sleeping.isChecked()){
                    fav += sleeping.getText().toString()+"-";
                }
                if(reading.isChecked()){
                    fav += reading.getText().toString()+"-";
                }
                String mess = platform + "\n"+ gender+"\n"+ raingbar+"\n"+country+"\n"+fav;
                AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this);
                mydialog.setMessage(mess);
                mydialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                mydialog.create().show();
            }
        });
    }
}