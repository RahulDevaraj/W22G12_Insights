package com.example.insights.ui.addexpense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.insights.databinding.AddExpenseFragmentBinding;

import org.w3c.dom.Text;

public class AddExpense_Fragment extends Fragment {

    private AddExpenseFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = AddExpenseFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textViewDescription = binding.txtDesc;
        EditText editTxtDescription = binding.editTxtDescription;
        TextView textViewCategory = binding.txtCategory;
        Spinner spinnerCategoryTypes = binding.spinnerCategoryTypes;
        TextView textViewDate = binding.txtDate;
        DatePicker datePicker = binding.datePicker;
        Button btnAddExpense = binding.btnAddExpense;

        //float scalingFactor = 0.8f; // scale down to half the size view.
        //calendarViewDate.setScaleX(scalingFactor);
        //calendarViewDate.setScaleY(scalingFactor);


        //textViewDescription.setText("Add Expense Fragment");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

