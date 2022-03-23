package com.example.insights.ui.bills;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;;

import com.example.insights.databinding.PayBillsFragmentBinding;

public class PayBills_Fragment extends Fragment {


    private PayBillsFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = PayBillsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.textPayBills;
        textView.setText("Pay Bills Fragment");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}