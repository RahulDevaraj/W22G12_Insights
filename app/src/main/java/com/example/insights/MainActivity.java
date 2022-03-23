package com.example.insights;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox show_hide_password;
    EditText edtTextPassword;
    TextView txtViewFrgtPass,txtViewCreateAccount;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar a  = getSupportActionBar();
        a.setDisplayShowHomeEnabled(true);
        a.setDisplayUseLogoEnabled(true);
        a.setLogo(R.mipmap.ic_launcher_logo);

        show_hide_password = findViewById(R.id.show_hide_password);
        edtTextPassword = findViewById(R.id.edtTextPassword);
        txtViewFrgtPass = findViewById(R.id.forgot_password);
        txtViewCreateAccount = findViewById(R.id.txtViewCreateAccount);
        btnLogin = findViewById(R.id.BtnLogin);

        show_hide_password.setOnCheckedChangeListener((CompoundButton compoundButton, boolean isChecked)-> {

            if (!isChecked) {
                // hide password
                edtTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                // show password
                edtTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                edtTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        txtViewFrgtPass.setOnClickListener((View view)-> {
            Intent myIntent = new Intent(MainActivity.this,ForgotPassword.class);
            startActivity(myIntent);
        });

        txtViewCreateAccount.setOnClickListener((View view)-> {
            startActivity(new Intent(MainActivity.this,Reggister.class));
        });

        btnLogin.setOnClickListener((View view) ->{

            //after validating usrid and pass from db

            startActivity(new Intent(MainActivity.this,HomePage.class));
        });
    }
}
