package com.example.myapplication;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SavedVacancyService {
    @GET("/findvacanciesbylogin")
    Call<String> findvacanciesbylogin(@Query("login") String login);
}
