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

import com.example.insights.R;
import com.example.insights.ui.bills.PayBillsViewModel;
import com.example.insights.databinding.PayBillsFragmentBinding;

public class PayBills_Fragment extends Fragment {

    private PayBillsViewModel payBillsViewModel;
    private PayBillsFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        payBillsViewModel = new ViewModelProvider(this).get(PayBillsViewModel.class);

        binding = PayBillsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPayBills;
        payBillsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}