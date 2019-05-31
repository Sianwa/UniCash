package com.example.unicash;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private final LinkedList<String> mExpenses;

    ExpenseAdapter(Context context, LinkedList<String> expenses){
        this.mExpenses =expenses;
    }
    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense_item,viewGroup,false);
        return new ExpenseViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder expenseViewHolder, int i) {
        String mCurrent = mExpenses.get(i);
        expenseViewHolder.wordExpense.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mExpenses.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView wordExpense;
        final ExpenseAdapter mAdapter;

        public ExpenseViewHolder(View view, ExpenseAdapter expenseAdapter) {
            super(view);
            wordExpense =itemView.findViewById(R.id.expense);
            this.mAdapter = expenseAdapter;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList.
            String element = mExpenses.get(mPosition);
            // Change the word in the mWordList.
            mExpenses.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }

    }
}

