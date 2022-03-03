package com.example.insights;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar a  = getSupportActionBar();
        a.setDefaultDisplayHomeAsUpEnabled(true);
        a.setDisplayUseLogoEnabled(true);
        a.setLogo(R.mipmap.ic_launcher_logo);
    }
}
