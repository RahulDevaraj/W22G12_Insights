package com.example.insights.ui.viewexpense;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.insights.ViewTransactionAdapter;
import com.example.insights.databases.UserDatabase;
import com.example.insights.databinding.ViewExpenseFragmentBinding;
import com.example.insights.model.UserTransaction;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewExpense_Fragment extends Fragment {

    private ViewExpenseFragmentBinding binding;
    UserDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {;

        binding = ViewExpenseFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);

        String dateRegex = month+"-%-"+year;


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String emailId = sharedPreferences.getString("USEREMAIL",null);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            try{
                db = Room.databaseBuilder(getContext(), UserDatabase.class,"User.db").build();
                List<UserTransaction> AllTransactions = db.userTransactionDao().getMonthlyExpenses(emailId,dateRegex);

                getActivity().runOnUiThread(()->{
                    RecyclerView item_recycler_view = binding.recyclerViewExpenses;
                    LinearLayoutManager lm = new LinearLayoutManager(getActivity());//,LinearLayoutManager.HORIZONTAL, false);

                    item_recycler_view.setLayoutManager(lm);
                    item_recycler_view.setAdapter(new ViewTransactionAdapter(AllTransactions,getActivity()));
                });


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