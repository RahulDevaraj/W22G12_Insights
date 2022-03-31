package com.example.insights;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insights.model.UserTransaction;

import java.util.List;

public class ViewTransactionAdapter extends RecyclerView.Adapter <ViewTransactionAdapter.ViewExpenseHolder>{

    List<UserTransaction> AllTransactions;

    public ViewTransactionAdapter(List<UserTransaction> allTransactions) {
        AllTransactions = allTransactions;
    }


    @NonNull
    @Override
    public ViewExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recycler_row,parent,false);

       ViewExpenseHolder viewExpenseHolder = new ViewExpenseHolder(view);

       viewExpenseHolder.txtViewDescription = view.findViewById(R.id.textViewDescription);
       viewExpenseHolder.txtViewAmount = view.findViewById(R.id.textViewAmount);
       viewExpenseHolder.txtViewDate = view.findViewById(R.id.textViewDate);

       return viewExpenseHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewExpenseHolder holder, int position) {

        holder.txtViewDescription.setText(AllTransactions.get(position).getDescription());
        holder.txtViewAmount.setText(AllTransactions.get(position).getAmount().toString());
        holder.txtViewDate.setText(AllTransactions.get(position).getTxndate());

    }

    @Override
    public int getItemCount() {
        return AllTransactions.size();
    }

    public class ViewExpenseHolder extends RecyclerView.ViewHolder{
        TextView txtViewDescription,txtViewAmount,txtViewDate;
        ImageView imgViewEdit,imgViewDelete;

        public ViewExpenseHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
