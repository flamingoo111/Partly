package com.example.myapplication;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpdateSavedService {
    @GET("/updateSavedIdByLogin")
    Call<String> updateSavedIdByLogin(@Query("id") Integer[] id, @Query("login") String login);
}