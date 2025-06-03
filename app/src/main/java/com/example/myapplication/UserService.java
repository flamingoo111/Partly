package com.example.myapplication;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("/findbylogin")
    Call<String> checkUser(@Query("login") String login, @Query("password") String password);
}