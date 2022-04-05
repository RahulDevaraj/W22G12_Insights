package com.example.insights.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.insights.R;
import com.example.insights.databases.UserDatabase;
import com.example.insights.interfaces.UserDao;
import com.example.insights.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    CheckBox show_hide_password;
    EditText edtTextPassword , edtTextUserId;
    TextView txtViewFrgtPass,txtViewCreateAccount;
    Button btnLogin;
    UserDatabase db;
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
        edtTextUserId = findViewById(R.id.edtTxtUserId);
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

        db = Room.databaseBuilder(getApplicationContext(),UserDatabase.class,"User.db").build();
        UserDao userDao = db.userDao();
        btnLogin.setOnClickListener((View view) ->{
            if(edtTextUserId.getText().toString().isEmpty() || edtTextPassword.getText().toString().isEmpty())
            {
                Toast.makeText(this, "User Id and Passwords Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() ->{

                    User user = userDao.findUserById(edtTextUserId.getText().toString());
                    runOnUiThread(()-> {
                        if (user == null){
                            Toast.makeText(MainActivity.this,"Account does not exist.Register to Login",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (user.getPassword().equals(edtTextPassword.getText().toString()) ) {
                                runOnUiThread(() -> {
                                    //if successfull put the details inside a shared preference
                                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("USERID",user.getUserId());
                                    editor.putString("USEREMAIL",user.getEmailId());
                                    editor.putString("USERLIMIT",user.getAmtLimit().toString());
                                    editor.commit();

                                    //home activity called
                                    startActivity(new Intent(MainActivity.this, HomePage.class));
                                });
                            } else {
                                runOnUiThread(()-> {
                                    Toast.makeText(MainActivity.this, "Incorrect Password. Try Again", Toast.LENGTH_SHORT).show();
                                });

                            }
                        }
                    });
                });
            }
        });
    }
}