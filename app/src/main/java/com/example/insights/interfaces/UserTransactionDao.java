package com.example.insights.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.insights.helpers.ChartHelper;
import com.example.insights.model.UserTransaction;

import java.util.List;

@Dao
public interface UserTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTransaction(UserTransaction userTransaction);

    @Query("SELECT * FROM USERTRANSACTION WHERE (emailid = :emailId and txndate LIKE :date)")
    List<UserTransaction> getMonthlyExpenses(String emailId, String date);

    @Query("SELECT SUM(amount)  as sum, category  FROM USERTRANSACTION WHERE (emailid = :emailId and txndate LIKE :date) GROUP BY category ")
    List<ChartHelper> getDataForChart(String emailId, String date);

    @Query("SELECT SUM(amount) FROM USERTRANSACTION WHERE (emailid = :emailId and txndate LIKE :date)")
    Double getMonthlySpending(String emailId, String date);

    @Query("DELETE FROM USERTRANSACTION WHERE (transactionId =:transactionid and emailid=:emailid)")
    int deleteExpense(int transactionid , String emailid);
}
