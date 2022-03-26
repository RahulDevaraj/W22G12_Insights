package com.example.insights.ui.logout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.insights.MainActivity;
import com.example.insights.databinding.LogoutFragmentBinding;

public class Logout_Fragment extends Fragment {

    private LogoutFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = LogoutFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.textLogout;
        textView.setText("Logout Fragment");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(getActivity(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), MainActivity.class));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}