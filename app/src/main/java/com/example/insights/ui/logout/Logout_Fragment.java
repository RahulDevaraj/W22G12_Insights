package com.example.insights.ui.logout;

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
import com.example.insights.ui.logout.LogoutViewModel;
import com.example.insights.databinding.LogoutFragmentBinding;

public class Logout_Fragment extends Fragment {

    private LogoutViewModel logoutViewModel;
    private LogoutFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logoutViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);

        binding = LogoutFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLogout;
        logoutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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