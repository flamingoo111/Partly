package com.example.myapplication;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VacancyNameService {
    @GET("/findvacancy")
    Call<String> findvacancybyname(@Query("name") String name);
}
