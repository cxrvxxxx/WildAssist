package com.csit28f3.wildassist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtEditEmail;
    private TextView txtEditName;
    private TextView txtEditPassword;
    private TextView txtEditPasswordConfirm;
    private TextView txtEditOldPassword;
    private Button btnEditProfileSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtEditEmail = findViewById(R.id.txtEditEmail);
        txtEditName = findViewById(R.id.txtEditName);
        txtEditPassword = findViewById(R.id.txtEditPassword);
        txtEditPasswordConfirm = findViewById(R.id.txtEditConfirmPassword);
        txtEditOldPassword = findViewById(R.id.txtEditOldPassword);

        btnEditProfileSubmit = findViewById(R.id.btnEditProfileSubmit);

        txtEditEmail.setText(Session.getActiveUser().getEmail());
        txtEditName.setText(Session.getActiveUser().getName());

        btnEditProfileSubmit.setOnClickListener(ProfileActivity.this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditProfileSubmit:
                if (!String.valueOf(txtEditOldPassword.getText()).equals(Session.getActiveUser().getPassword())) {
                    Toast.makeText(ProfileActivity.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Session.getActiveUser().setName(String.valueOf(txtEditName.getText()));

                if (txtEditPassword.getText().length() > 0 || txtEditPasswordConfirm.getText().length() > 0) {
                    String newPass = String.valueOf(txtEditPassword.getText());
                    String newPassConf = String.valueOf(txtEditPasswordConfirm.getText());

                    if (!newPass.equals(newPassConf)) {
                        Toast.makeText(ProfileActivity.this, "New passwords do not match!", Toast.LENGTH_SHORT).show();
                        return;
                    } else
                        Session.getActiveUser().setPassword(String.valueOf(txtEditPassword.getText()));
                }

                Toast.makeText(ProfileActivity.this, "Changes saved!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
                break;
        }
    }
}