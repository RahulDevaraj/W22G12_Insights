package com.example.insights.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.insights.model.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertUser(User user);

    @Query("SELECT * FROM User WHERE userid = :UsrId")
    User findUserById(String UsrId);

    @Query("SELECT * FROM User WHERE emailid = :EmailId")
    User findUserByEmail(String EmailId);

    @Query("UPDATE User SET password = :Password WHERE emailid=:Email")
    int UpdatePassword(String Password,String Email);

    @Query("UPDATE User SET amtlimit = :AmtLimit WHERE emailid=:Email")
    int SetLimit(Double AmtLimit,String Email);

    @Query("UPDATE User SET amtlimit = :AmtLimit, password = :password WHERE emailid=:Email")
    int changeAccountDetails(String password,Double AmtLimit,String Email);

    @Query("DELETE FROM User WHERE emailid = :emailId")
    int deleteAccount(String emailId);
}
