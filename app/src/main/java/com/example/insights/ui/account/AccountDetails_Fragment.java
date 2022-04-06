package com.example.insights.ui.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.insights.activities.HomePage;
import com.example.insights.R;
import com.example.insights.activities.MainActivity;
import com.example.insights.databases.UserDatabase;
import com.example.insights.databinding.AccountDetailsFragmentBinding;
import com.example.insights.model.User;
import com.example.insights.ui.home.HomeFragment;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

                UserDatabase database = Room.databaseBuilder(getContext(),UserDatabase.class,"User.db").build();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String emailId = sharedPreferences.getString("USEREMAIL",null);

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(()-> {
                    User user = database.userDao().findUserByEmail(emailId);

                    getActivity().runOnUiThread(()->{
                        TextView textViewEmail = binding.edtTxtUserIdView;
                        TextView textViewUserName = binding.edtTxtUserNameView;
                        TextView editTextPasswordChange = binding.edtTextPasswordChange;
                        TextView editTextPasswordChangeConfirm = binding.edtTextPasswordChangeConfirm;
                        TextView edtTextSetLimitChange = binding.edtTextSetLimitChange;
                        Button BtnSaveSettings = binding.BtnSaveSettings;
                        Button btnDeleteUser = binding.deleteUser;

                        textViewEmail.setText(user.getEmailId());
                        textViewUserName.setText(user.getUserId());
                        editTextPasswordChange.setText(user.getPassword());
                        editTextPasswordChangeConfirm.setText(user.getPassword());
                        edtTextSetLimitChange.setText(user.getAmtLimit().toString());

                        btnDeleteUser.setOnClickListener((View view) -> {


                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            //Yes button clicked
                                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.clear();
                                            editor.apply();
                                            ExecutorService executorService = Executors.newSingleThreadExecutor();
                                            executorService.execute(()->{

                                                database.userDao().deleteAccount(user.getEmailId());

                                                getActivity().runOnUiThread(()->{
                                                    Toast.makeText(getActivity(), "User Deleted and Logged Out Successfully", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                                });

                                            });


                                            break;

                                        case DialogInterface.BUTTON_NEGATIVE:
                                            //No button clicked
                                            Toast.makeText(getActivity(), "Deletion Cancelled", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                            //startActivity(new Intent(getActivity(), HomePage.class));
                                            break;
                                    }
                                }
                            };

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setMessage("Are you sure to Delete Account and Logout?").setPositiveButton("Yes", dialogClickListener)
                                    .setNegativeButton("No", dialogClickListener).show();

                        });

                        BtnSaveSettings.setOnClickListener((View view)-> {
                            if(editTextPasswordChange.getText().toString().isEmpty() || editTextPasswordChangeConfirm.getText().toString().isEmpty()||
                            edtTextSetLimitChange.getText().toString().isEmpty())
                                Toast.makeText(getContext(), "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();
                            else if(!editTextPasswordChange.getText().toString().equals(editTextPasswordChangeConfirm.getText().toString()))
                                    Toast.makeText(getContext(), "Passwords Mismatch", Toast.LENGTH_SHORT).show();
                                else{
                                    double amtLimit=0.0;
                                    boolean isValid = true;
                                    try {
                                        amtLimit = Double.parseDouble(edtTextSetLimitChange.getText().toString());
                                    }
                                    catch (Exception e){
                                        isValid = false;
                                        Toast.makeText(getContext(), "Invalid Data in Limit Field", Toast.LENGTH_SHORT).show();
                                    }
                                    if(isValid)
                                    {
                                        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
                                        executorService1.execute(()->{
                                            try{
                                                database.userDao().changeAccountDetails(editTextPasswordChange.getText().toString(),
                                                        Double.parseDouble(edtTextSetLimitChange.getText().toString()),
                                                        emailId);
                                                Log.d("TAG","HEREEEEE");

                                                getActivity().runOnUiThread(()->{
                                                    Toast.makeText(getContext(), "Account Details Changed Successfully", Toast.LENGTH_SHORT).show();

                                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                                    editor.putString("USERLIMIT",edtTextSetLimitChange.getText().toString());
                                                    editor.commit();
                                                    startActivity(new Intent(getActivity(), HomePage.class));
                                                });

                                            }
                                            catch (Exception e){
                                                Toast.makeText(getContext(), "Error in Account Settings Changing", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                    }

                                }


                        });




                    });


                });
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