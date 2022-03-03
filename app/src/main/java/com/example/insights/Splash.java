package com.example.insights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class Splash extends AppCompatActivity {

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ImageView imgViewLogo = findViewById(R.id.imgView_Logo);
        TextView txtViewAppName = findViewById(R.id.txtViewAppName);

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Splash.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(Splash.this, "Authentication Error: "+errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(Splash.this,MainActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(Splash.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("FingerPrint Authentication")
                .setSubtitle("Unlock Application using FingerPrint")
                .setDeviceCredentialAllowed(true)
                .build();

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                txtViewAppName.setText(R.string.app_name);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                BiometricManager biometricManager = BiometricManager.from(Splash.this);
                switch (biometricManager.canAuthenticate()){
                    case BiometricManager.BIOMETRIC_SUCCESS:
                        biometricPrompt.authenticate(promptInfo);
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                        Toast.makeText(Splash.this, "This device don't have a FingerPrint Sensor", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Splash.this,MainActivity.class));
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        Toast.makeText(Splash.this, "FingerPrint Sensor is currently unavailable", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                        Toast.makeText(Splash.this, "No FingerPrint Saved in this device", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Splash.this,MainActivity.class));
                        break;
                }

                //biometricPrompt.authenticate(promptInfo);
               // startActivity(new Intent(Splash.this,MainActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imgViewLogo.startAnimation(anim);

    }
}