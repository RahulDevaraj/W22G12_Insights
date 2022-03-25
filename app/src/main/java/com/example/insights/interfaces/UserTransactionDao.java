package com.example.insights.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.insights.model.UserTransaction;

@Dao
public interface UserTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTransaction(UserTransaction userTransaction);
}
