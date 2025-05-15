 package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

 public class MainActivity extends AppCompatActivity implements Postman{

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         EdgeToEdge.enable(this);
         setContentView(R.layout.activity_main);
         LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_login);
     }

     @Override
     public void fragmentMail(String login, String password) {
         Toast toast = Toast.makeText(this, login + " " + password, Toast.LENGTH_LONG);
         toast.show();
     }
 }*