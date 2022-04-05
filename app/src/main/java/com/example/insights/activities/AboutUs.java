package com.example.insights.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.insights.R;
import com.example.insights.databinding.ActivityAboutUsBinding;

public class AboutUs extends AppCompatActivity {

    private ActivityAboutUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        TextView txtViewAboutUs = binding.TxtViewAboutApp;

        txtViewAboutUs.setText(" Insights in an Expense Tracker , that will help you monitor your day-to-day expenses.The App allows " +
                "you to set a monthly budget and then maps these expenses to various categories to help the user analyze their expenses.");


        TextView txtViewDevelopers = binding.txtViewDevelopers;

        txtViewDevelopers.setText("The App is developed by"+"\n Asha Anthony \n Basil Benny \n Rahul Devaraj");
    }
}