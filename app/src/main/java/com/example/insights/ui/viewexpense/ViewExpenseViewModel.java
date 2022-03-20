package com.example.insights.ui.viewexpense;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewExpenseViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ViewExpenseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is View Expense fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}