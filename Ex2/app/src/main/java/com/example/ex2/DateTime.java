package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateTime extends AppCompatActivity {
    private EditText et, ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        et = findViewById(R.id.editTextText7);
        ed = findViewById(R.id.editTextText8);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hh = c.get(Calendar.HOUR_OF_DAY);
                int mm = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(DateTime.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        et.setText(hourOfDay+"h:"+minute+"p");
                    }
                },hh,mm,false);
                timePickerDialog.show();
            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateTime.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },y,m,d);
                datePickerDialog.show();
            }
        });
    }

}