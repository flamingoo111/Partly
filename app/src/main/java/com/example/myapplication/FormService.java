package com.example.myapplication;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FormService {
    @POST("/addvacancy")
    Call<String> setNewVacancy(@Body JobFormSet form);
}