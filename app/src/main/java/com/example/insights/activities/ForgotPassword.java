package com.example.insights.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.insights.R;
import com.example.insights.databases.UserDatabase;
import com.example.insights.helpers.MailHelper;
import com.example.insights.interfaces.UserDao;
import com.example.insights.model.User;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;

public class ForgotPassword extends AppCompatActivity {

    Button btnSubmit;
    EditText edtTxtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnSubmit = findViewById(R.id.btnSubmit);
        edtTxtEmail = findViewById(R.id.edtTxtUserId);


        btnSubmit.setOnClickListener((View view)-> {
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(edtTxtEmail.getText().toString()).matches())
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            else{


                UserDatabase database;
                database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class,"User.db").build();

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(()-> {
                    UserDao userDao = database.userDao();
                    // check DB for valid Registered Email
                    User user = userDao.findUserByEmail(edtTxtEmail.getText().toString());

                    runOnUiThread(()-> {
                        if(user==null){
                            Toast.makeText(this, "This user does not exist", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            // check Generate password and Reset
                            String newPassword = generatePassword();
                            String subject = "Password Reset Mail";
                            String message = "Please use the following credentials to login to the account \n UserID : "+user.getUserId()+"\n Password : "+newPassword;
                            try{
                                sendMail(user.getEmailId(),subject,message);
                                executorService.execute(()->{
                                    userDao.UpdatePassword(newPassword,user.getEmailId());
                                });
                                runOnUiThread(()->{
                                    Toast.makeText(this, "Email Sent Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ForgotPassword.this,MainActivity.class));
                                });
                            }
                            catch (MessagingException e){
                                e.printStackTrace();
                                Toast.makeText(this, "Failed to send Email", Toast.LENGTH_SHORT).show();
                            }

                        }

                    });
                });

            }



        });

    }
    private void sendMail(String senderAddress,String subject,String message) throws MessagingException {
        MailHelper mailHelper = new MailHelper(ForgotPassword.this, senderAddress, subject, message);

        mailHelper.execute();
    }

    private String generatePassword(){
        String password="";
        Random random = new Random();
        for(int i=0;i<6;i++){
            int digit = random.nextInt(10);
            password += digit;
        }
        return password;
    }


}