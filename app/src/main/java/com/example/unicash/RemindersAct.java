package com.example.unicash;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.unicash.models.NewRem;
import com.example.unicash.models.ReminderModel;
import com.example.unicash.network.Api;
import com.example.unicash.notifications.NotificationReceiver;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemindersAct extends AppCompatActivity {
private TextView mDate, mTime;
private Button saveButton;
private EditText mTitle,mDesc;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Api api = retrofit.create(Api.class);
    NewRem newRem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        //remove shadow from action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setElevation(0);

        //views
        mTitle=findViewById(R.id.reminderText);
        mDate =findViewById(R.id.date);
        mDesc =findViewById(R.id.details);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        mTime =findViewById(R.id.time);
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
        saveButton=findViewById(R.id.toolbarbtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               newReminder();

            }
        });

        //back navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }


    //create fragments
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
    public void showTimePickerDialog() {
        // Use the current time as the default values for the picker.
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.set(Calendar.SECOND, 0);
                        
                        mTime.setText(hourOfDay + ":" + minute);
                        createReminder(c);

                    }
                }, hour, minute, true);
        timePickerDialog.show();

    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createReminder(Calendar c) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this,1,intent,0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void newReminder(){
        String title =mTitle.getText().toString();
        String date = mDate.getText().toString();
        String details =mDesc.getText().toString();

        if(TextUtils.isEmpty(title)) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(details)) {
            Toast.makeText(this, "Please enter details", Toast.LENGTH_LONG).show();
            return;
        }

            newRem = new NewRem(title,date,details);
        Call<ReminderModel> call =api.newReminder(newRem);

        call.enqueue(new Callback<ReminderModel>() {
            @Override
            public void onResponse(Call<ReminderModel> call, Response<ReminderModel> response) {
                if(response.isSuccessful()){
                    Intent intent= new Intent(RemindersAct.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ReminderModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());
            }
        });
        }
    }

