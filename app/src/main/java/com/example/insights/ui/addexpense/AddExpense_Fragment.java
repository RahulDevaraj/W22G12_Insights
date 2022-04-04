package com.example.insights.ui.addexpense;

import android.content.Intent;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.insights.activities.HomePage;
import com.example.insights.databases.UserDatabase;
import com.example.insights.databinding.AddExpenseFragmentBinding;
import com.example.insights.model.UserTransaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddExpense_Fragment extends Fragment {

    private AddExpenseFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = AddExpenseFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        EditText editTxtDescription = binding.editTxtDescription;
        EditText editTxtAmount = binding.editTxtAmount;
        Spinner spinnerCategoryTypes = binding.spinnerCategoryTypes;
        DatePicker datePicker = binding.datePicker;
        Button btnAddExpense = binding.btnAddExpense;

        //float scalingFactor = 0.8f; // scale down to half the size view.
        //calendarViewDate.setScaleX(scalingFactor);
        //calendarViewDate.setScaleY(scalingFactor);


        //textViewDescription.setText("Add Expense Fragment");
        btnAddExpense.setOnClickListener((View view)-> {
            if(editTxtDescription.getText().toString().isEmpty() || editTxtAmount.getText().toString().isEmpty())
            {
                Toast.makeText(getActivity(), "Please fill out empty Fields", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Double amount = null;
                boolean isValid = false;
                try{
                    amount = Double.parseDouble(editTxtAmount.getText().toString());
                    isValid = true;
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Please Enter Valid Data in Amount", Toast.LENGTH_SHORT).show();
                }
                if(isValid)
                {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String emailId = sharedPreferences.getString("USEREMAIL",null);
                    String category = spinnerCategoryTypes.getSelectedItem().toString();
                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth()+1;
                    int year =  datePicker.getYear();

                    String newDate = (month)+"-"+day+"-"+year;

                    UserDatabase database = Room.databaseBuilder(getContext(),UserDatabase.class,"User.db").build();
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    UserTransaction userTransaction = new UserTransaction(emailId,
                            editTxtDescription.getText().toString(),
                            amount,
                            category,
                            newDate);

                    executorService.execute(()->{
                        try {
                            database.userTransactionDao().addTransaction(userTransaction);

                            getActivity().runOnUiThread(()-> {
                                Toast.makeText(getActivity(), "Entry Successfully Added", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(),HomePage.class));
                            });


                        }

                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }


                    });
                }
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

