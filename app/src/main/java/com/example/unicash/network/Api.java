package com.example.unicash.network;

import com.example.unicash.models.ExpenseModel;
import com.example.unicash.models.Login;
import com.example.unicash.models.NewExp;
import com.example.unicash.models.NewRem;
import com.example.unicash.models.ReminderModel;
import com.example.unicash.models.SignUp;
import com.example.unicash.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "http://192.168.43.166:8080";

    @GET("db")
    Call<JSONResponse> getExpense();

    @POST("/api/user/signin")
    Call<User> login(@Body Login login);

    @POST("/api/user/signup")
    Call<User> signup(@Body SignUp signup);

    @POST("/api/record/newExpense")
    Call<ExpenseModel> newExpense(@Body NewExp newExp );

    @POST("/api/record/newReminder")
    Call<ReminderModel> newReminder(@Body NewRem newRem );
}