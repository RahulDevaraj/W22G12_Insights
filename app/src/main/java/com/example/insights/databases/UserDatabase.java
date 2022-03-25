package com.example.insights.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.insights.interfaces.UserDao;
import com.example.insights.interfaces.UserTransactionDao;
import com.example.insights.model.User;
import com.example.insights.model.UserTransaction;

@Database(entities = {User.class, UserTransaction.class},version=2,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract UserTransactionDao userTransactionDao();
}
