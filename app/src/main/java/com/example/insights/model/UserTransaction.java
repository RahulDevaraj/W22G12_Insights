package com.example.insights.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "USERTRANSACTION",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "emailid",
        childColumns = "emailid",onDelete = ForeignKey.CASCADE))
public class UserTransaction {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transactionId")
    @NonNull
   private int transactionId;

    @ColumnInfo(name = "emailid")
    @NonNull
    private String emailid;

    @ColumnInfo(name = "description")
    @NonNull
    private String description;

    @ColumnInfo(name = "amount")
    @NonNull
    private Double amount;

    @ColumnInfo(name = "category")
    @NonNull
    private String category;

    @ColumnInfo(name = "txndate")
    @NonNull
    private String txndate;

    public UserTransaction(@NonNull String emailid, @NonNull String description, @NonNull Double amount, @NonNull String category, @NonNull String txndate) {
        this.emailid = emailid;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.txndate = txndate;
    }

    public UserTransaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @NonNull
    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(@NonNull String emailid) {
        this.emailid = emailid;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public Double getAmount() {
        return amount;
    }

    public void setAmount(@NonNull Double amount) {
        this.amount = amount;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }

    @NonNull
    public String getTxndate() {
        return txndate;
    }

    public void setTxndate(@NonNull String txndate) {
        this.txndate = txndate;
    }
}
