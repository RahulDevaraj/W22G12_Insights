package com.example.insights;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insights.model.UserTransaction;
import com.example.insights.ui.viewexpense.ViewExpense_Fragment;

import java.security.AccessController;
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
       viewExpenseHolder.txtViewAmount = view.findViewById(R.id.textViewAmount );
       viewExpenseHolder.imgCategory = view.findViewById(R.id.imgCategoryRow);
       viewExpenseHolder.imgViewDelete = view.findViewById(R.id.imageViewDeleteRow);
       viewExpenseHolder.imgViewEdit = view.findViewById(R.id.imageViewEditRow);


       viewExpenseHolder.imgViewEdit.setOnClickListener((View view1)-> {
                  viewExpenseHolder.imgViewEdit.setBackgroundColor(Color.BLUE);

       });

       viewExpenseHolder.imgViewDelete.setOnClickListener((View view2)-> {
           DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   switch (which){
                       case DialogInterface.BUTTON_POSITIVE:
                           //Yes button clicked

                           Toast.makeText(view2.getContext(), "Record Deleted successfully", Toast.LENGTH_SHORT).show();
                           break;

                       case DialogInterface.BUTTON_NEGATIVE:
                           dialog.cancel();
                           break;
                   }
               }
           };

           AlertDialog.Builder builder = new AlertDialog.Builder(view2.getContext());
           builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                   .setNegativeButton("No", dialogClickListener).show();


       });

       return viewExpenseHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewExpenseHolder holder, int position) {

        holder.txtViewDescription.setText(AllTransactions.get(position).getDescription());
        holder.txtViewAmount.setText(AllTransactions.get(position).getAmount().toString());



        switch (AllTransactions.get(position).getCategory()){
            case "Shopping":
                holder.imgCategory.setImageResource(R.drawable.ic_baseline_shopping_cart_24);
                break;
            case"Pets":
                holder.imgCategory.setImageResource(R.drawable.ic_baseline_pets_24);
                break;
            case"Travel":
                holder.imgCategory.setImageResource(R.drawable.ic_baseline_card_travel_24);
                break;
            case"Personal":
                holder.imgCategory.setImageResource(R.drawable.ic_baseline_person_24);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return AllTransactions.size();
    }

    public class ViewExpenseHolder extends RecyclerView.ViewHolder{
        TextView txtViewDescription,txtViewAmount;
        ImageView imgViewEdit,imgViewDelete ,imgCategory;

        public ViewExpenseHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
