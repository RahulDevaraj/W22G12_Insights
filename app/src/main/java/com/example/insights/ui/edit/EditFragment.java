package com.example.insights.ui.edit;

import static android.preference.PreferenceManager.getDefaultSharedPreferencesName;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.insights.R;
import com.example.insights.databinding.EditFragmentBinding;
import com.example.insights.model.UserTransaction;
import com.google.gson.Gson;


public class EditFragment extends Fragment {

  ;

    private EditFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = EditFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("USERTRANSACTION", "");
        UserTransaction userTransaction = gson.fromJson(json, UserTransaction.class);

        EditText editTxtDescription = binding.editTxtDescription;
        EditText editTxtAmount = binding.editTxtAmount;
        Spinner spinnerCategoryTypes = binding.spinnerCategoryTypes;
        DatePicker datePicker = binding.datePicker;
        Button btnSave = binding.btnSave;
        Button btnCancel = binding.btnSave;

        editTxtDescription.setText(""+userTransaction.getDescription());
        editTxtAmount.setText(""+userTransaction.getAmount());
        String Category = userTransaction.getCategory();
        int spinnerPosition = -1;
        switch (Category){
            case "Shopping":
                spinnerPosition = 0;
                break;
            case"Pets":
                spinnerPosition = 1;
                break;
            case"Travel":
                spinnerPosition = 2;
                break;
            case"Personal":
                spinnerPosition = 3;
                break;
        }
        spinnerCategoryTypes.setSelection(spinnerPosition);

        String date[] = userTransaction.getTxndate().split("-");

        datePicker.updateDate(Integer.parseInt(date[2]),Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}