package com.example.unicash;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL= "https://my-json-server.typicode.com/Sianwa/UniCash/";

    @GET("Expenses")
    Call<ExpenseModel>getExpense();
}
