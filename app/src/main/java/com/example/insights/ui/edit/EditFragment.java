package com.example.insights.ui.edit;

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
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.insights.R;
import com.example.insights.databases.UserDatabase;
import com.example.insights.databinding.EditFragmentBinding;
import com.example.insights.model.UserTransaction;
import com.example.insights.ui.viewexpense.ViewExpense_Fragment;
import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EditFragment extends Fragment {

    Double amount = null;

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
        Button btnCancel = binding.btnCancel;

        editTxtDescription.setText(""+userTransaction.getDescription());
        editTxtAmount.setText(""+userTransaction.getAmount());
        String Category = userTransaction.getCategory();
        int spinnerPosition = -1;
        switch (Category){
            case "Shopping":
                spinnerPosition = 0;
                break;
            case"Travel":
                spinnerPosition = 1;
                break;
            case"Personal Care":
                spinnerPosition = 2;
                break;
            case"Pets":
                spinnerPosition = 3;
                break;
        }
        spinnerCategoryTypes.setSelection(spinnerPosition);

        String date[] = userTransaction.getTxndate().split("-");

        datePicker.updateDate(Integer.parseInt(date[2]),Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));

        btnSave.setOnClickListener((View view)-> {
            if(editTxtDescription.getText().toString().isEmpty() || editTxtAmount.getText().toString().isEmpty())
            {
                Toast.makeText(getContext(), "Please fill out empty Fields", Toast.LENGTH_SHORT).show();
            }
            else
            {

                boolean isValid = false;
                try{
                    amount = Double.parseDouble(editTxtAmount.getText().toString());
                    isValid = true;
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(), "Please Enter Valid Data in Amount", Toast.LENGTH_SHORT).show();
                }
                if(isValid)
                {
                    String category = spinnerCategoryTypes.getSelectedItem().toString();
                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth()+1;
                    int year =  datePicker.getYear();

                    String newDate = (month)+"-"+day+"-"+year;

                    UserDatabase database = Room.databaseBuilder(getContext(),UserDatabase.class,"User.db").build();
                    ExecutorService executorService = Executors.newSingleThreadExecutor();


                    executorService.execute(()->{
                        try {
                            database.userTransactionDao().updateExpense(editTxtDescription.getText().toString(),amount,category,newDate,userTransaction.getTransactionId(),userTransaction.getEmailid());

                            getActivity().runOnUiThread(()-> {
//                                Toast.makeText(getContext(), "Entry Successfully Added", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getActivity(), HomePage.class));
                                Toast.makeText(getContext(), "Expense Successfully Updated", Toast.LENGTH_SHORT).show();
                                ViewExpense_Fragment viewExpense_fragment = new ViewExpense_Fragment();
                                btnCancel.setEnabled(false);
                                btnSave.setEnabled(false);

                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.editexp, viewExpense_fragment).commit();

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

        btnCancel.setOnClickListener((View view)-> {
            try{

                ViewExpense_Fragment viewExpense_fragment = new ViewExpense_Fragment();
                btnCancel.setEnabled(false);
                btnSave.setEnabled(false);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.editexp, viewExpense_fragment).commit();




            }
            catch(Exception e){
                e.printStackTrace();
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