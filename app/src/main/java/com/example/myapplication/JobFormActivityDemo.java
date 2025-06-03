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
import android.widget.TextView;
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

public class JobFormActivityDemo extends AppCompatActivity {
    JobForm vacancy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_demo);
        vacancy = (JobForm) getIntent().getSerializableExtra("job_form");
        ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBack();
            }
        });
        TextView title_text = findViewById(R.id.title_text);
        TextView category_text = findViewById(R.id.category_text);
        TextView address_text = findViewById(R.id.address_text);
        TextView short_desc_text = findViewById(R.id.short_desc_text);
        TextView full_desc_text = findViewById(R.id.full_desc_text);
        TextView payment_text = findViewById(R.id.payment_text);
        TextView phone_text = findViewById(R.id.phone_text);

        title_text.setText(vacancy.name);
        category_text.setText(vacancy.place);
        address_text.setText(vacancy.category);
        short_desc_text.setText(vacancy.shortDescription);
        full_desc_text.setText(vacancy.longDescription);
        payment_text.setText(vacancy.price + "");
        phone_text.setText(vacancy.number + "");

    }

    public void getBack() {
        Intent intent = new Intent(this, ListActivity.class);
        String login = getIntent().getStringExtra("login");
        intent.putExtra("login", login);
        startActivity(intent);
    }



}
