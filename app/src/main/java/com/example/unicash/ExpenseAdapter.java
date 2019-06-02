package com.example.unicash;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private ArrayList<ExpenseModel> mExpenses;

    public ExpenseAdapter(ArrayList<ExpenseModel> expenses)
    {
        this.mExpenses =expenses;
    }
    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense_item,viewGroup,false);
        ExpenseViewHolder expenseViewHolder = new ExpenseViewHolder(view);
        return expenseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ExpenseViewHolder expenseViewHolder, int i) {

        expenseViewHolder.mDescription.setText(mExpenses.get(i).getDescription());
        expenseViewHolder.mCategory.setText(mExpenses.get(i).getCategory());
        expenseViewHolder.mAmount.setText(mExpenses.get(i).getAmount());
    }

    @Override
    public int getItemCount() {

        return mExpenses.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        View mview;
        TextView mCategory, mDescription,mAmount;

        public ExpenseViewHolder(View view ) {
            super(view);
            mview=view;
            //views
            mAmount= mview.findViewById(R.id.Amount);
            mCategory=mview.findViewById(R.id.Category);
            mDescription=mview.findViewById(R.id.Description);
        }

    }
}

