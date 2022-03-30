package com.example.insights.helpers;

import androidx.room.ColumnInfo;

public class ChartHelper {
    @ColumnInfo(name = "sum")
    Double sum;
    @ColumnInfo(name = "category")
    String category;

    public ChartHelper(Double sum, String category) {
        this.sum = sum;
        this.category = category;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}