package com.example.unicash;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private ExpenseAdapter mAdapter;
    private final LinkedList<String> mExpenseList = new LinkedList<>();
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 20; i++) {
            mExpenseList.addLast("Word " + mCount++);
            Log.d("WordList", mExpenseList.getLast());
        }
        // Get a handle to the RecyclerView.
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ExpenseAdapter(this, mExpenseList);
        // Connect the adapter with the RecyclerView.
        mRecyclerview.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        // Add a floating action click handler for creating new entries.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int wordListSize = mExpenseList.size();
                // Add a new word to the end of the wordList.
                mExpenseList.addLast("+ Word " + wordListSize);
                // Notify the adapter, that the data has changed so it can
                // update the RecyclerView to display the data.
                mRecyclerview.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom.
                mRecyclerview.smoothScrollToPosition(wordListSize);
            }
        });

    }
}
