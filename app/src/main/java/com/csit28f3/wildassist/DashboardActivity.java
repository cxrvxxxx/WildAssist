package com.csit28f3.wildassist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView lblSessionUser;
    private Button btnEditProfile;
    private Button btnNewBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        lblSessionUser = findViewById(R.id.lblSessionUser);

        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnNewBooking = findViewById(R.id.btnNewBooking);

        lblSessionUser.setText(Session.getActiveUser().getName());

        btnEditProfile.setOnClickListener(DashboardActivity.this);
        btnNewBooking.setOnClickListener(DashboardActivity.this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditProfile:
                Intent profileIntent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.btnNewBooking:
                Intent bookingIntent = new Intent(DashboardActivity.this, NewBookingActivity.class);
                startActivity(bookingIntent);
        }
    }
}