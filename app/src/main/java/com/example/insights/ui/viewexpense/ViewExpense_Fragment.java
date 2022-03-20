package com.example.insights.ui.viewexpense;

import androidx.lifecycle.ViewModelProvider;

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
import com.example.insights.ui.viewexpense.ViewExpenseViewModel;
import com.example.insights.databinding.ViewExpenseFragmentBinding;

public class ViewExpense_Fragment extends Fragment {

    private ViewExpenseViewModel viewExpenseViewModel;
    private ViewExpenseFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewExpenseViewModel = new ViewModelProvider(this).get(ViewExpenseViewModel.class);

        binding = ViewExpenseFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewExpense;
        viewExpenseViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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