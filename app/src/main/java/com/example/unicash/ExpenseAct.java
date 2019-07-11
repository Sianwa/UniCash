package com.example.unicash;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unicash.models.ExpenseModel;
import com.example.unicash.models.NewExp;
import com.example.unicash.network.Api;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpenseAct extends AppCompatActivity {
    private TextView mDate;
    private EditText mDescription,mAmount;
    private Button mSave;
    private String selectedCategory;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Api api = retrofit.create(Api.class);
    NewExp newExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        //remove shadow from action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setElevation(0);

        //views
        mDescription=findViewById(R.id.expenseText);
        mAmount=findViewById(R.id.amount);
        mSave=findViewById(R.id.saveBtn);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newExpense();
            }
        });
        mDate =findViewById(R.id.date);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        //back navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String[] data = {"Salaries & Wages", "Supplies", "Rent", "Utilities", "Advertising", "Food & Drinks"};

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_selected, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedCategory =parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //create fragments
    @TargetApi(Build.VERSION_CODES.N)
    public void showDatePickerDialog() {

        // Use the current date as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        mDate.setText(day + "/" +
                                month + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

  public void newExpense(){
       String description=mDescription.getText().toString();
       String date=mDate.getText().toString();
       String category=selectedCategory;
       String amount=mAmount.getText().toString();
       int am =Integer.parseInt(amount);

      if(TextUtils.isEmpty(description)) {
          Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
          return;
      }

        newExp = new NewExp(description,date,category,am);
      Call<ExpenseModel>call =api.newExpense(newExp);
      call.enqueue(new Callback<ExpenseModel>() {
          @Override
          public void onResponse(Call<ExpenseModel> call, Response<ExpenseModel> response) {
              if(response.isSuccessful()){
                  Intent intent= new Intent(ExpenseAct.this, MainActivity.class);
                  startActivity(intent);
              }
          }

          @Override
          public void onFailure(Call<ExpenseModel> call, Throwable t) {
              Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
              Log.d("Error",t.getMessage());
          }
      });
  }
}
