package com.example.insights.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.insights.interfaces.UserDao;
import com.example.insights.model.User;

@Database(entities = {User.class},version=1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
