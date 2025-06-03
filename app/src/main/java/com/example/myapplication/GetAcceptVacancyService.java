package com.example.myapplication;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetAcceptVacancyService {
    @GET("/findgetvacanciesbylogin")
    Call<String> findgetvacanciesbylogin(@Query("login") String login);
}
