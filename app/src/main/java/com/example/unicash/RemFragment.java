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

import com.example.unicash.models.ReminderModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemFragment extends Fragment {
    private RecyclerView mRecycler;
    private DatabaseReference mRef;
    private FirebaseDatabase mFire;

    View v;
    public RemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.reminder_frag,container,false);

        //recyclerview
        mRecycler =v.findViewById(R.id.recycler);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO intent to go to add new reminder page
                Intent intent=new Intent(getContext(),RemindersAct.class);
                startActivity(intent);

            }
        });

        //send query to firebasedb
        mFire = FirebaseDatabase.getInstance();
        mRef=mFire.getReference().child("Reminders");

        FirebaseRecyclerOptions<ReminderModel> options=
                new FirebaseRecyclerOptions.Builder<ReminderModel>()
                    .setQuery(mRef,ReminderModel.class)
                    .build();
        FirebaseRecyclerAdapter<ReminderModel,RemFragment.ViewHolder>firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<ReminderModel, RemFragment.ViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull RemFragment.ViewHolder holder, int position, @NonNull ReminderModel model) {
                        holder.mTitle.setText(model.getTitle());
                        holder.mDate.setText(model.getDate());
                    }

                    @NonNull
                    @Override
                    public RemFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reminder_item,viewGroup,false);
                        RemFragment.ViewHolder viewHolder = new RemFragment.ViewHolder(view);
                        return viewHolder;
                    }
                };
        mRecycler.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
        return v;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle,mDate,mDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //views
            mTitle=itemView.findViewById(R.id.Title);
            mDate=itemView.findViewById(R.id.Date);
        }
    }
}
