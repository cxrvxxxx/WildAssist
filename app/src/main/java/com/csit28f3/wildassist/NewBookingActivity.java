package com.csit28f3.wildassist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class NewBookingActivity extends AppCompatActivity {
    private AutoCompleteTextView txtNewBookingDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);

        txtNewBookingDestination = findViewById(R.id.txtNewBookingDestination);

        String[] optionsArray = {
                "Main Bldg.",
                "ST Bldg.",
                "GLE Bldg.",
                "Academic Bldg.",
                "BED Bldg.",
                "Gymnasium",
                "Other"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(NewBookingActivity.this, android.R.layout.simple_dropdown_item_1line, optionsArray);
        txtNewBookingDestination.setAdapter(adapter);
    }
}