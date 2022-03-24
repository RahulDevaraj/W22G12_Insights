package com.example.insights;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ImageView imgViewLogo = findViewById(R.id.imgView_Logo);
        TextView txtViewAppName = findViewById(R.id.txtViewAppName);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                txtViewAppName.setText(R.string.app_name);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startActivity(new Intent(Splash.this,MainActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imgViewLogo.startAnimation(anim);

    }
}