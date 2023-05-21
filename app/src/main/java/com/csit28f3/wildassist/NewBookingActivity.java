package com.csit28f3.wildassist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TimePicker;

import java.util.Locale;

public class NewBookingActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtNewBookingDate;
    private EditText txtNewBookingStartTime;
    private EditText txtNewBookingEndTime;
    private EditText txtNewBookingPurpose;
    private AutoCompleteTextView txtNewBookingDestination;
    private Button btnNewBookingSubmit;
    private Button btnSetBookingDate;
    private Button btnSetBookingStartTime;
    private Button btnSetBookingEndTime;
    private Button btnSetBookingDateSubmit;
    private Button btnSetBookingTimeSubmit;
    private LayoutInflater inflater;
    private PopupWindow datePickerWindow;
    private View datePickerView;
    private DatePicker datePicker;
    private PopupWindow timePickerWindow;
    private View timePickerView;
    private TimePicker timePicker;
    private boolean isEditingEndTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);

        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        // DatePicker
        txtNewBookingDate = findViewById(R.id.txtNewBookingDate);

        btnSetBookingDate = findViewById(R.id.btnSetBookingDate);
        btnSetBookingDate.setOnClickListener(NewBookingActivity.this);

        datePickerView = inflater.inflate(R.layout.popup_datepicker, null);
        datePickerWindow = new PopupWindow(datePickerView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        datePickerWindow.setFocusable(true);
        datePicker = datePickerView.findViewById(R.id.datePicker);

        btnSetBookingDateSubmit = datePickerView.findViewById(R.id.btnSetBookingDateSubmit);
        btnSetBookingDateSubmit.setOnClickListener(NewBookingActivity.this);

        // TimePicker
        timePickerView = inflater.inflate(R.layout.popup_timepicker, null);
        timePickerWindow = new PopupWindow(timePickerView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        timePickerWindow.setFocusable(true);
        timePicker = timePickerView.findViewById(R.id.timePicker);

        btnSetBookingTimeSubmit = timePickerView.findViewById(R.id.btnSetBookingTimeSubmit);
        btnSetBookingTimeSubmit.setOnClickListener(NewBookingActivity.this);

        // Start TimePicker
        txtNewBookingStartTime = findViewById(R.id.txtNewBookingStartTime);

        btnSetBookingStartTime = findViewById(R.id.btnSetBookingStartTime);
        btnSetBookingStartTime.setOnClickListener(NewBookingActivity.this);

        // End TimePicker
        txtNewBookingEndTime = findViewById(R.id.txtNewBookingEndTime);

        btnSetBookingEndTime = findViewById(R.id.btnSetBookingEndTime);
        btnSetBookingEndTime.setOnClickListener(NewBookingActivity.this);

        // Destination
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

        // Purpose
        txtNewBookingPurpose = findViewById(R.id.txtNewBookingPurpose);

        // Submit
        btnNewBookingSubmit = findViewById(R.id.btnNewBookingSubmit);
        btnNewBookingSubmit.setOnClickListener(NewBookingActivity.this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSetBookingDate:
                datePickerWindow.showAtLocation(datePickerView, Gravity.CENTER, 0, 0);
                break;
            case R.id.btnSetBookingStartTime:
                isEditingEndTime = false;
                timePickerWindow.showAtLocation(timePickerView, Gravity.CENTER, 0, 0);
                break;
            case R.id.btnSetBookingEndTime:
                isEditingEndTime = true;
                timePickerWindow.showAtLocation(timePickerView, Gravity.CENTER, 0, 0);
                break;
            case R.id.btnNewBookingSubmit:
                String date = String.valueOf(txtNewBookingDate.getText());
                String startTime = String.valueOf(txtNewBookingStartTime.getText());
                String endTime = String.valueOf(txtNewBookingEndTime.getText());
                String destination = String.valueOf(txtNewBookingDestination.getText());
                String purpose = String.valueOf(txtNewBookingPurpose.getText());

                Session.addBooking(
                        new Booking(date, startTime, endTime, destination, purpose)
                );

                finish();
                break;
            case R.id.btnSetBookingDateSubmit:
                datePickerWindow.dismiss();
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1; // Months are zero-based, so add 1
                int dayOfMonth = datePicker.getDayOfMonth();

                String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month, year);
                txtNewBookingDate.setText(formattedDate);
                break;
            case R.id.btnSetBookingTimeSubmit:
                timePickerWindow.dismiss();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                String time = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
                EditText toUpdate = isEditingEndTime ? txtNewBookingEndTime : txtNewBookingStartTime;
                toUpdate.setText(time);
                break;
        }
    }
}