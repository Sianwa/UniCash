package com.example.unicash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.unicash.models.ExpenseModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpFragment extends Fragment {
    private RecyclerView mRecyclerview;
    private DatabaseReference mRef;
    private FirebaseDatabase mFirebasedb;

    View v;
    public ExpFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.expense_frag,container,false);

        //recyclerview
        mRecyclerview = v.findViewById(R.id.recycler);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));



        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ExpenseAct.class);
                startActivity(intent);
            }
        });

        //send query to firebasedb
        mFirebasedb = FirebaseDatabase.getInstance();
        mRef= mFirebasedb.getReference().child("Expenses");

        FirebaseRecyclerOptions<ExpenseModel> options=
                new FirebaseRecyclerOptions.Builder<ExpenseModel>()
                        .setQuery(mRef,ExpenseModel.class)
                        .build();
        FirebaseRecyclerAdapter<ExpenseModel, ExpFragment.Viewholders> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ExpenseModel, ExpFragment.Viewholders>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ExpFragment.Viewholders holder, int position, @NonNull ExpenseModel model) {
                        holder.mAmount.setText(String.valueOf(model.getAmount()));
                        holder.mCategory.setText(model.getCategory());
                        holder.mDescription.setText(model.getDescription());
                    }

                    @NonNull
                    @Override
                    public ExpFragment.Viewholders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense_item,viewGroup,false);
                        ExpFragment.Viewholders viewholder = new ExpFragment.Viewholders(view);
                        return viewholder;
                    }
                };
        mRecyclerview.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();


        return v;

    }

    public static class Viewholders extends RecyclerView.ViewHolder{
        TextView mCategory, mDescription,mAmount;
        public Viewholders(@NonNull View itemView) {
            super(itemView);
            //views
            mAmount= itemView.findViewById(R.id.Amount);
            mCategory=itemView.findViewById(R.id.Category);
            mDescription=itemView.findViewById(R.id.Description);
        }
    }



}
