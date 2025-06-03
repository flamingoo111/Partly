package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JobFormActivity extends AppCompatActivity {
    String[] categories = { "Фриланс", "Курьерская доставка", "Репетиторство", "Ручная работа", "IT", "Физический труд", "Другое..."  };
    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_form);
        login = getIntent().getStringExtra("login");
        System.out.println(login  + " 2");
        Spinner spinner = findViewById(R.id.category_spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);
        ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBack();
            }
        });
        Button send = findViewById(R.id.submit_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.56.1:8100/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                FormService service = retrofit.create(FormService.class);
                JobFormSet form_job = getVacancy();

                if (!(form_job.equals(new JobFormSet()))) {
                    Call<String> call = service.setNewVacancy(form_job);
                    System.out.println("try....");
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                System.out.println("scusecc");
                                Toast toast = Toast.makeText(JobFormActivity.this, "Done...", Toast.LENGTH_LONG);
                                toast.show();
                                getBack();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            System.out.println("failure");
                            Toast toast = Toast.makeText(JobFormActivity.this, "Ooops...", Toast.LENGTH_LONG);
                            toast.show();
                            Log.d("MainActivity", t.getMessage());
                        }
                    });
                }
                getBack();
            }
        });
    }

    public void getBack() {
        Intent intent = new Intent(this, ListActivity.class);
        String login = getIntent().getStringExtra("login");
        intent.putExtra("login", login);
        startActivity(intent);
    }

    public JobFormSet getVacancy(){
        EditText etname = findViewById(R.id.title_input);
        String name = etname.getText().toString();
        Spinner spinner = findViewById(R.id.category_spinner);
        String category = spinner.getSelectedItem().toString();
        EditText etadres = findViewById(R.id.address_input);
        String adres = etadres.getText().toString();
        EditText etshort_desc_input = findViewById(R.id.short_desc_input);
        String short_desc_input = etshort_desc_input.getText().toString();
        EditText etfull_desc_input = findViewById(R.id.full_desc_input);
        String full_desc_input = etfull_desc_input.getText().toString();
        EditText etpayment_input = findViewById(R.id.payment_input);
        String payment_input = etpayment_input.getText().toString();
        boolean error = false;
        long papayment_input_int = 0;
        try {
            papayment_input_int = Long.parseLong(payment_input);
        } catch (NumberFormatException e) {
            System.out.println("Catch paynament, " + payment_input);
            findViewById(R.id.error_payment_input).setVisibility(View.VISIBLE);
        }
        EditText etphone_input = findViewById(R.id.phone_input);
        String phone_input = etphone_input.getText().toString();
        long phone_input_int = 0;
        try {
            phone_input_int = Long.parseLong(phone_input);
        } catch (NumberFormatException e) {
            System.out.println("Catch phone, " + phone_input);
            findViewById(R.id.error_phone_input).setVisibility(View.VISIBLE);
        }
        System.out.println(phone_input_int + " " +  papayment_input_int);
        if(name.equals("")){
            findViewById(R.id.title_input).setVisibility(View.VISIBLE);
            error=true;
        }
        if(adres.equals("")){
            findViewById(R.id.error_address_input).setVisibility(View.VISIBLE);
            error=true;
        }
        if(short_desc_input.equals("")){
            findViewById(R.id.error_short_desc_input).setVisibility(View.VISIBLE);
            error=true;
        }
        if(full_desc_input.equals("")){
            findViewById(R.id.error_full_desc_input).setVisibility(View.VISIBLE);
            error=true;
        }
        if(payment_input.equals("") || papayment_input_int == 0){
            findViewById(R.id.error_payment_input).setVisibility(View.VISIBLE);
            error=true;
        }
        if(name.equals("") || phone_input_int == 0){
            findViewById(R.id.error_phone_input).setVisibility(View.VISIBLE);
            error=true;
        }
        System.out.println(error);
        if(!error){
            return new JobFormSet(name, adres, category,short_desc_input,full_desc_input,papayment_input_int,phone_input_int, getIntent().getStringExtra("login"));
        }else{
            return new JobFormSet();
        }
    }

}
