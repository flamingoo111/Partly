package com.example.myapplication;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpdateAcceptService {
    @GET("/updateAcceptIdBylogin")
    Call<String> updateAcceptIdBylogin(@Query("id") Integer[] id, @Query("login") String login);
}