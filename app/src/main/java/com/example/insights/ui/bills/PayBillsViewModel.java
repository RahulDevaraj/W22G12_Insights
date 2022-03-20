package com.example.insights.ui.bills;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PayBillsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public PayBillsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Pay Bills fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}