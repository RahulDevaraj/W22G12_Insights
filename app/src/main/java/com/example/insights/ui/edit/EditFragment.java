package com.example.insights.ui.edit;

import static android.preference.PreferenceManager.getDefaultSharedPreferencesName;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.insights.databinding.EditFragmentBinding;
import com.example.insights.model.UserTransaction;
import com.google.gson.Gson;


public class EditFragment extends Fragment {

    TextView test ;

    private EditFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = EditFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("USERTRANSACTION", "");
        UserTransaction userTransaction = gson.fromJson(json, UserTransaction.class);
        test = binding.txtView;
        test.setText(""+userTransaction.getDescription());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}