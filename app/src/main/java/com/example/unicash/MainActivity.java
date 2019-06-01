package com.example.unicash;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private ExpenseAdapter mAdapter;
    private final LinkedList<String> mExpenseList = new LinkedList<>();
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ExpenseModel> call =api.getExpense();

        call.enqueue(new Callback<ExpenseModel>() {
            @Override
            public void onResponse(Call<ExpenseModel>call, Response<ExpenseModel> response) {
               Log.d("ResponseString",response.body().toString());

                // TODO set adapter to display info in recyclerview

                Toast.makeText(getApplicationContext(),"Its still working",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ExpenseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        mRecyclerview = findViewById(R.id.recyclerview);
        mAdapter = new ExpenseAdapter(this, mExpenseList);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               //TODO intent to go to add new record page
            }
        });

    }
}
