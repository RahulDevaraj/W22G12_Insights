package com.example.insights.ui.addexpense;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddExpenseViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AddExpenseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Add Expense fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}