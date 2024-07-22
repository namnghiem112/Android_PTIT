package com.example.menutest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);

        // By ID get the AutoCompleteTextView
        // which id is assign in xml file
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        // Create the string array
        // and store the values.
        String[] colors
                = { "Red", "Green", "Black",
                "Orange", "Blue", "Pink",
                "Blush", "Brown", "Yellow" };

        // Create the object of ArrayAdapter with String
        // which hold the data as the list item.
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_item,
                colors);

        // Give the suggestion after 1 words.
        autoCompleteTextView.setThreshold(1);

        // Set the adapter for data as a list
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setTextColor(Color.BLACK);
    }
}