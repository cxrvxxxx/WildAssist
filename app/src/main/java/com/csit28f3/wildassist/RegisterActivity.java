package com.csit28f3.wildassist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private CheckBox cbTerms;
    private Button btnRegisterSubmit;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName = (EditText) findViewById(R.id.txtRegName);
        txtEmail = (EditText) findViewById(R.id.txtRegEmail);
        txtPassword = (EditText) findViewById(R.id.txtRegPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtRegConfirmPassword);
        cbTerms = (CheckBox) findViewById(R.id.cbTerms);

        btnRegisterSubmit = (Button) findViewById(R.id.btnRegisterSubmit);
        btnClear = (Button) findViewById(R.id.btnClear);

        txtName.setOnFocusChangeListener(RegisterActivity.this);
        txtEmail.setOnFocusChangeListener(RegisterActivity.this);
        txtPassword.setOnFocusChangeListener(RegisterActivity.this);
        txtConfirmPassword.setOnFocusChangeListener(RegisterActivity.this);
        cbTerms.setOnCheckedChangeListener(RegisterActivity.this);

        btnRegisterSubmit.setOnClickListener(RegisterActivity.this);
        btnClear.setOnClickListener(RegisterActivity.this);

        btnRegisterSubmit.setEnabled(false);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            switch (v.getId()) {
                case R.id.txtRegName:
                case R.id.txtRegEmail:
                case R.id.txtRegPassword:
                case R.id.txtRegConfirmPassword:
                    ((EditText) v).setText("");
                    break;
            }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegisterSubmit:
                String name = String.valueOf(txtName.getText());
                String email = String.valueOf(txtEmail.getText());
                String password = String.valueOf(txtPassword.getText());
                String confirmPassword = String.valueOf(txtConfirmPassword.getText());

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                User u = new User(name, email, password);

                if (!Session.isUniqueUser(u)) {
                    Toast.makeText(RegisterActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Session.addUser(u);

                Toast.makeText(RegisterActivity.this, "Account created!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnClear:
                clearFields();
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
        switch (cb.getId()) {
            case R.id.cbTerms:
                btnRegisterSubmit.setEnabled(isChecked);
                break;
        }
    }

    public void clearFields() {
        txtName.setText(R.string.txtName_label);
        txtEmail.setText(R.string.txtEmail_label);
        txtPassword.setText(R.string.txtPassword_label);
        txtConfirmPassword.setText(R.string.txtPassword_label);
        cbTerms.setChecked(false);
    }
}