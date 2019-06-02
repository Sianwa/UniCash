package com.example.unicash;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private ExpenseAdapter mAdapter;
    private ArrayList<ExpenseModel> mExpenseList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setHasFixedSize(true);
        loadJson();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               //TODO intent to go to add new record page
            }
        });

    }

    private void loadJson() {
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<JSONResponse> call =api.getExpense();

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse>call, Response<JSONResponse> response) {
/*

*/

  if(response.isSuccessful()){
    JSONResponse jsonResponse= response.body();
      //JSONResponse jsonResponse = response.body();
      mExpenseList= new ArrayList<>(Arrays.asList(jsonResponse.getExpenses()));
      mAdapter = new ExpenseAdapter(mExpenseList);
      mRecyclerview.setAdapter(mAdapter);
    Toast.makeText(getApplicationContext(),"Its still working",Toast.LENGTH_LONG).show();

}
           }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());
            }
        });

    }
}
