package com.example.insights.ui.addexpense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.insights.databinding.AddExpenseFragmentBinding;

public class AddExpense_Fragment extends Fragment {

    private AddExpenseFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = AddExpenseFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.textAddExpense;
        textView.setText("Add Expense Fragment");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

