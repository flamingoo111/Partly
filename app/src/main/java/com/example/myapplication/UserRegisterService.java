package com.example.myapplication;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserRegisterService {
    @GET("/addperson")
    Call<String> addUser(@Query("login") String login, @Query("password") String password, @Query("type") String type );
}