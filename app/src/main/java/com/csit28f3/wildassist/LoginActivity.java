package com.csit28f3.wildassist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private EditText txtLoginEmail;
    private EditText txtLoginPassword;
    private Button btnLoginSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLoginEmail = (EditText) findViewById(R.id.txtLoginEmail);
        txtLoginPassword = (EditText) findViewById(R.id.txtLoginPassword);

        btnLoginSubmit = (Button)  findViewById(R.id.btnLoginSubmit);

        txtLoginEmail.setOnFocusChangeListener(LoginActivity.this);
        txtLoginPassword.setOnFocusChangeListener(LoginActivity.this);

        btnLoginSubmit.setOnClickListener(LoginActivity.this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLoginSubmit) {
            String email = String.valueOf(txtLoginEmail.getText());
            String password = String.valueOf(txtLoginPassword.getText());

            if (!(email.length() > 0) || !(password.length() > 0)) {
                Toast.makeText(LoginActivity.this, "Email or Password must not be blank!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Session.isValidCredentials(email, password)) {
                Toast.makeText(LoginActivity.this, "Invalid user!", Toast.LENGTH_SHORT).show();
                return;
            }

            Session.login(email, password);
            Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            switch (v.getId()) {
                case R.id.txtLoginEmail:
                    txtLoginEmail.setText("");
                    break;
                case R.id.txtLoginPassword:
                    txtLoginPassword.setText("");
                    break;
            }
    }
}