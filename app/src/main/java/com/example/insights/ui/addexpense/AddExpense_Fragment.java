package com.example.insights.ui.addexpense;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.insights.R;
import com.example.insights.ui.addexpense.AddExpenseViewModel;
import com.example.insights.databinding.AddExpenseFragmentBinding;

public class AddExpense_Fragment extends Fragment {

    private AddExpenseViewModel addExpenseViewModel;
    private AddExpenseFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addExpenseViewModel = new ViewModelProvider(this).get(AddExpenseViewModel.class);

        binding = AddExpenseFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddExpense;
        addExpenseViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

