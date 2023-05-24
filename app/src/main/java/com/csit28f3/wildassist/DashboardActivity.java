package com.csit28f3.wildassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Fragment manager
        fragmentManager = getSupportFragmentManager();

        TextView lblSessionUser = findViewById(R.id.lblSessionUser);
        lblSessionUser.setText(Session.getActiveUser().getName());

        Button btnEditProfile = findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(DashboardActivity.this);

        Button btnStartFocusMode = findViewById(R.id.btnStartFocusMode);
        btnStartFocusMode.setOnClickListener(DashboardActivity.this);

        initializeContent();
    }

    private void initializeContent() {
        // Default fragment if no bookings are present
        if (Session.getAllBookings().size() == 0) {
            BookingFragment bookingFragment = new BookingFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainerLayout, bookingFragment)
                    .commit();
        } else {
            ShowBookingsFragment showBookingsFragment = new ShowBookingsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainerLayout, showBookingsFragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeContent();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditProfile:
                Intent profileIntent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.btnStartFocusMode:
                Intent focusIntent = new Intent(DashboardActivity.this, com.example.acadzone.SplashScreen.class);
                startActivity(focusIntent);
                break;
        }
    }
}