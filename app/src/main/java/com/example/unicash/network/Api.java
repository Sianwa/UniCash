package com.example.unicash.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL= "https://my-json-server.typicode.com/Sianwa/UniCash/";

    @GET("db")
    Call<JSONResponse>getExpense();
}
