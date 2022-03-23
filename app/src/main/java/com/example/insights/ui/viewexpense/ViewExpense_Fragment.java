package com.example.insights.ui.viewexpense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.insights.databinding.ViewExpenseFragmentBinding;

public class ViewExpense_Fragment extends Fragment {

    private ViewExpenseFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {;

        binding = ViewExpenseFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.textViewExpense;
        textView.setText("View Expense Fragment");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}