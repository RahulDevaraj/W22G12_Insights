package com.example.insights.ui.logout;

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

import com.example.insights.databinding.LogoutFragmentBinding;

public class Logout_Fragment extends Fragment {

    private LogoutFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = LogoutFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.textLogout;
        textView.setText("Logout Fragment");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}