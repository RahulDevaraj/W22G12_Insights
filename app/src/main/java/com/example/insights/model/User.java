package com.example.insights.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity(tableName = "User")
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="emailid")
    private String EmailId;

    @NonNull
    @ColumnInfo(name ="userid")
    private String UserId;

    @NonNull
    @ColumnInfo(name ="password")
    private String Password;

    @ColumnInfo(name="amtlimit")
    private Double AmtLimit;

    public User() {
    }

    public User(@NonNull String emailId, @NonNull String userId, @NonNull String password, Double amtLimit) {
        EmailId = emailId;
        UserId = userId;
        Password = password;
        AmtLimit = amtLimit;
    }

    @NonNull
    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(@NonNull String emailId) {
        EmailId = emailId;
    }

    @NonNull
    public String getUserId() {
        return UserId;
    }

    public void setUserId(@NonNull String userId) {
        UserId = userId;
    }

    @NonNull
    public String getPassword() {
        return Password;
    }

    public void setPassword(@NonNull String password) {
        Password = password;
    }

    public Double getAmtLimit() {
        return AmtLimit;
    }

    public void setAmtLimit(Double amtLimit) {
        AmtLimit = amtLimit;
    }
}
