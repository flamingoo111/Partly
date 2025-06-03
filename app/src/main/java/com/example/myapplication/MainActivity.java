 package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

 public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentSendDataListener{
     private SharedPreferences sharedPreferences;
     final String SAVED_LOGIN = "LOGIN";
     final String SAVED_PASSWORD = "PASSWORD";

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         EdgeToEdge.enable(this);
         setContentView(R.layout.activity_main);
         LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_login);
         FragmentManager fm = getSupportFragmentManager();
         FragmentTransaction ft = fm.beginTransaction();
         LoginFragment fb = new LoginFragment();
         ft.add(R.id.fragment_login, fb);
         ft.commit();
     }

     @Override
     public void onSendData(String login, String password) {
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://192.168.56.1:8100/")
                 .addConverterFactory(ScalarsConverterFactory.create())
                 .build();
         UserService service = retrofit.create(UserService.class);
         Call<String> call = service.checkUser(login, password);
         System.out.println(call);
         call.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 if(response.isSuccessful()){
                     String ans = response.body().toString();
                     Toast toast = Toast.makeText(MainActivity.this, ans, Toast.LENGTH_LONG);
                     saveData(login,password);
                     toast.show();
                     if (ans.equals("True")){
                         Intent intent = new Intent(MainActivity.this, ListActivity.class);

                         // Передаём данные
                         intent.putExtra("login", login); // Строку
                         startActivity(intent);
                     }
                 }
             }
             @Override
             public void onFailure(Call<String> call, Throwable t) {
                 Toast toast = Toast.makeText(MainActivity.this, "Ooops...", Toast.LENGTH_LONG);
                 toast.show();
                 Log.d("MainActivity", t.getMessage());             }
         });
     }

     @Override
     public void onChangeFragment(Fragment frgm) {
         Fragment newFragment = frgm;
         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
         transaction.replace(R.id.fragment_login, newFragment);
         transaction.addToBackStack(null);
         transaction.commit();
         ImageView iv = findViewById(R.id.picture_of_login);
         iv.setImageResource(LoginFragment.class.isInstance(frgm)?R.drawable.login_pic: R.drawable.register_pic);
     }

     @Override
     public void onRegisterData(String login, String password) {
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://192.168.56.1:8100/")
                 .addConverterFactory(ScalarsConverterFactory.create())
                 .build();
         String type = "user";
         UserRegisterService service = retrofit.create(UserRegisterService.class);
         Call<String> call = service.addUser(login, password, type);
         System.out.println(call);
         call.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 if(response.isSuccessful()){
                     String ans = response.body().toString();
                     System.out.println(ans);
                     Toast toast = Toast.makeText(MainActivity.this, ans, Toast.LENGTH_LONG);
//                     saveData(login,password);
                     toast.show();
                 }
             }
             @Override
             public void onFailure(Call<String> call, Throwable t) {
                 Toast toast = Toast.makeText(MainActivity.this, "Ooops...", Toast.LENGTH_LONG);
                 toast.show();
                 Log.d("MainActivity", t.getMessage());             }
     })
     ;}

     void saveData(String login, String password) {
         sharedPreferences = getPreferences(MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPreferences.edit();
         editor.putString(SAVED_LOGIN, login);
         editor.putString(SAVED_PASSWORD, password);
         editor.apply();
         Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
     }
     String[] loadData() {
         sharedPreferences = getPreferences(MODE_PRIVATE);
         String savedLogin = sharedPreferences.getString(SAVED_LOGIN, "");
         String savedPassword = sharedPreferences.getString(SAVED_PASSWORD, "");
         return new String[]{savedLogin, savedPassword};
     }
 }