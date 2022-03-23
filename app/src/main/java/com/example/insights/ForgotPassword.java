package com.example.insights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    Button btnSubmit;
    EditText edtTxtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnSubmit = findViewById(R.id.btnSubmit);
        edtTxtEmail = findViewById(R.id.edtTxtUserId);

        btnSubmit.setOnClickListener((View view)-> {
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(edtTxtEmail.getText().toString()).matches())
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            else{
                // check DB for valid Registered Email
                // update to random password
                // send email with new password
                //return to login

                Toast.makeText(this, "Email Sent Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ForgotPassword.this,MainActivity.class));
            }

        });

    }

}