package com.example.insights.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.insights.model.UserTransaction;

import java.util.List;

@Dao
public interface UserTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTransaction(UserTransaction userTransaction);

    @Query("SELECT * FROM USERTRANSACTION WHERE (emailid = :emailId and txndate LIKE :date)")
    List<UserTransaction> getMonthlyExpenses(String emailId, String date);
}
