package com.example.insights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reggister extends AppCompatActivity {

    Button btnRegister;
    TextView txtViewLogin ;
    EditText editTxtEmail, edtTextPassword,edtTextPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reggister);

        btnRegister = findViewById(R.id.BtnRegister);
        txtViewLogin = findViewById(R.id.txtViewLogin);
        editTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTextPassword = findViewById(R.id.edtTextPassword);
        edtTextPasswordConfirm = findViewById(R.id.edtTextPasswordConfirm);
        btnRegister.setOnClickListener((View view)-> {

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(editTxtEmail.getText().toString()).matches())
                Toast.makeText(Reggister.this, "Invalid Email", Toast.LENGTH_SHORT).show();
            else if( !edtTextPassword.getText().toString().equals(edtTextPasswordConfirm.getText().toString())){
                Toast.makeText(Reggister.this, "Entered Passwords do not match. Try again", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Account has been created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Reggister.this,MainActivity.class));
            }
        });

        txtViewLogin.setOnClickListener((View view)-> {

            startActivity(new Intent(Reggister.this,MainActivity.class));
        });
    }
}