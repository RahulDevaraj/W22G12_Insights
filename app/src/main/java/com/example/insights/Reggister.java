package com.example.insights;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.insights.databases.UserDatabase;
import com.example.insights.interfaces.UserDao;
import com.example.insights.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Reggister extends AppCompatActivity {

    Button btnRegister;
    TextView txtViewLogin ;
    EditText editTxtEmail, edtTextPassword,edtTextPasswordConfirm, edtTxtUser,edtTextSetLimit;
    UserDatabase db;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reggister);

        btnRegister = findViewById(R.id.BtnLogin);
        txtViewLogin = findViewById(R.id.txtViewLogin);
        editTxtEmail = findViewById(R.id.edtTxtUserId);
        edtTextPassword = findViewById(R.id.edtTextPassword);
        edtTextPasswordConfirm = findViewById(R.id.edtTextPasswordConfirm);
        edtTxtUser = findViewById(R.id.edtTxtUser);
        edtTextSetLimit = findViewById(R.id.edtTextSetLimit);

        db = Room.databaseBuilder(getApplicationContext(),UserDatabase.class,"User.db").build();
        UserDao userDao = db.userDao();

        btnRegister.setOnClickListener((View view)-> {

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(editTxtEmail.getText().toString()).matches())
                Toast.makeText(Reggister.this, "Invalid Email", Toast.LENGTH_SHORT).show();
            else if( !edtTextPassword.getText().toString().equals(edtTextPasswordConfirm.getText().toString())){
                Toast.makeText(Reggister.this, "Entered Passwords do not match. Try again", Toast.LENGTH_SHORT).show();
            }
            else if(edtTextSetLimit.getText().toString().isEmpty()){
                Toast.makeText(Reggister.this, "Please enter the Monthly Spend Limit", Toast.LENGTH_SHORT).show();
            }
            else{
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() -> {
                    try{
                        user = userDao.findUserByEmail(editTxtEmail.getText().toString());

                            if(user == null){
                                user = new User(editTxtEmail.getText().toString(),edtTxtUser.getText().toString(),edtTextPassword.getText().toString(),Double.parseDouble(edtTxtUser.getText().toString()));
                                userDao.InsertUser(user);
                                Toast.makeText(this, "Account has been created", Toast.LENGTH_SHORT).show();
                                runOnUiThread(()-> {
                                    startActivity(new Intent(Reggister.this,MainActivity.class));
                                });

                            }
                            else{
                                Toast.makeText(this, "An Account with this Email Id already exists. ", Toast.LENGTH_SHORT).show();
                            }


                    }
                    catch (Exception e){
                        Log.d("USERDB",e.getMessage());
                    }

                });

                Toast.makeText(this, "Account has been created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Reggister.this,MainActivity.class));
            }
        });

        txtViewLogin.setOnClickListener((View view)-> {

            startActivity(new Intent(Reggister.this,MainActivity.class));
        });
    }
}