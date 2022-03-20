package com.example.insights.ui.account;

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
import com.example.insights.ui.account.AccountDetailsViewModel;
import com.example.insights.databinding.AccountDetailsFragmentBinding;



public class AccountDetails_Fragment extends Fragment {

    private AccountDetailsViewModel accountDetailsViewModel;
    private AccountDetailsFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountDetailsViewModel = new ViewModelProvider(this).get(AccountDetailsViewModel.class);

        binding = AccountDetailsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAccountDetails;
        accountDetailsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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