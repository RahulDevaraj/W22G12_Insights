package com.example.insights.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.insights.HomePage;
import com.example.insights.R;
import com.example.insights.databinding.AccountDetailsFragmentBinding;
import com.example.insights.ui.home.HomeFragment;

import java.util.Objects;
import java.util.concurrent.Executor;


public class AccountDetails_Fragment extends Fragment {

    private AccountDetailsFragmentBinding binding;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = AccountDetailsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        executor = ContextCompat.getMainExecutor(getActivity());
        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);

                startActivity(new Intent(getActivity(),HomePage.class));
                Toast.makeText(getActivity(), "Authentication Error: "+errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //startActivity(new Intent(getActivity(),MainActivity.class));
                //If fingerprint is OK
                //Write Rest of the code inside

                        TextView textView = binding.textAccountDetails;
                        textView.setText("Account Details Fragment");
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("FingerPrint Authentication")
                .setSubtitle("Unlock Application using FingerPrint")
                .setDeviceCredentialAllowed(true)
                .build();
        BiometricManager biometricManager = BiometricManager.from(getContext());
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:
                biometricPrompt.authenticate(promptInfo);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getActivity(), "This device don't have a FingerPrint Sensor", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(Splash.this,MainActivity.class));
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(getActivity(), "FingerPrint Sensor is currently unavailable", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(getActivity(), "No FingerPrint Saved in this device", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(Splash.this,MainActivity.class));
                biometricPrompt.authenticate(promptInfo);
                break;
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}