package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlinx.coroutines.Job;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ListActivity extends AppCompatActivity implements ListFragment.OnFragmentSendDataListenerList, TopButtonMenuFragment.OnFragmentSendDataListenerListButton, BottomButtonMenuFragment.OnFragmentSendDataListenerListBottomButton, ProfileFragment.OnFragmentSendDataListenerListProfile, VacationAdapter.getSavedVacancy{
    JobForm[] savedVacancy;
    JobForm[] fullVacancyUser;
    JobForm[] acceptVacancy;
    JobForm[] myVacancy;
    boolean updateList = true;
    private final static String TOP_BUTTON_TAG = "f1";
    boolean changeET = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_work);
        System.out.println("reg");
        LoadingFragment fragment = (LoadingFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_list_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        LoadingFragment fb = new LoadingFragment();
        ft.add(R.id.fragment_list_main, fb);
        ft.commit();
        TopButtonMenuFragment fragment2 = (TopButtonMenuFragment) getSupportFragmentManager().findFragmentById(R.id.button_top_menu_fragment);
        FragmentManager fm2 = getSupportFragmentManager();
        FragmentTransaction ft2 = fm2.beginTransaction();
        TopButtonMenuFragment fb2 = new TopButtonMenuFragment();
        ft2.add(R.id.button_top_menu_fragment, fb2, TOP_BUTTON_TAG);
        ft2.commit();
        BottomButtonMenuFragment fragment3 = (BottomButtonMenuFragment) getSupportFragmentManager().findFragmentById(R.id.bottomBar);
        FragmentManager fm3 = getSupportFragmentManager();
        FragmentTransaction ft3 = fm3.beginTransaction();
        BottomButtonMenuFragment fb3 = new BottomButtonMenuFragment();
        ft3.add(R.id.bottomBar, fb3);
        ft3.commit();
        makeMonth();

    }

    // Метод cоздания массива месяцев
    private void makeMonth() {
        Vacation[] xxx;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8100/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        VacancyService service = retrofit.create(VacancyService.class);
        Call<String> call = service.getallvacancy();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("zzz");
                if (response.isSuccessful()) {
                    System.out.println("xxx");
                    String ans = response.body().toString();
                    Gson gson = new Gson();
                    Type jobItemListType = new TypeToken<List<JobForm>>() {
                    }.getType();

                    // Парсинг JSON в список объектов
                    List<JobForm> allVacancy = gson.fromJson(ans, jobItemListType);
                    JobForm[] AllofVacancy = new JobForm[allVacancy.size()];
                    System.out.println(123);
                    for (int i = 0; i < allVacancy.size(); i++) {
                        AllofVacancy[i] = allVacancy.get(i);
//                            for(JobForm form: savedVacancy){
//                                if(AllofVacancy[i].id == form.id){
//                                    AllofVacancy[i].isLiked = true;
//                                }
//                            }
                    }
                    setFullVacancy(AllofVacancy);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast toast = Toast.makeText(ListActivity.this, "Ooops...", Toast.LENGTH_LONG);
                toast.show();
                Log.d("MainActivity", t.getMessage());
            }
        });
    }


    public void setFullVacancy(JobForm[] fullVacancy) {
        fullVacancyUser = fullVacancy;
        getSavedVacncybylogin();
    }


    @Override
    public void openActivityJobForm() {
            Intent intent = new Intent(this, JobFormActivity.class);
            String login = getIntent().getStringExtra("login");
            System.out.println("LOGIN:  "    + login);
            intent.putExtra("login", login);
            startActivity(intent);
        }

    @Override
    public void findVacancyByName(String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8100/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        String type = "user";
        VacancyNameService service = retrofit.create(VacancyNameService.class);
        Call<String> call = service.findvacancybyname(name);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("zzz");
                if (response.isSuccessful()) {
                    System.out.println("xxx");
                    String ans = response.body().toString();
                    Gson gson = new Gson();
                    Type jobItemListType = new TypeToken<List<JobForm>>() {
                    }.getType();

                    // Парсинг JSON в список объектов
                    List<JobForm> allVacancy = gson.fromJson(ans, jobItemListType);
                    JobForm[] AllofVacancy = new JobForm[allVacancy.size()];
                    System.out.println(123);
                    // Вывод для проверки (например, в лог)
                    for (int i = 0; i < allVacancy.size(); i++) {
                        AllofVacancy[i] = allVacancy.get(i);
                    }
                    setFullVacancy(AllofVacancy);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast toast = Toast.makeText(ListActivity.this, "Ooops...", Toast.LENGTH_LONG);
                toast.show();
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    @Override
    public void changeETfunc() {
        changeET = true;
    }

    @Override
    public void openProfile() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.button_top_menu_fragment, new TopButtonMenuFragment())
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_list_main, new ProfileFragment(getIntent().getStringExtra("login")))
                .commit();
    }

    @Override
    public void openHome(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_list_main, new LoadingFragment())
                .commit();
        makeMonth();
    }

    @Override
    public void openAdvertisements() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.button_top_menu_fragment, new TopButtonMenuFragment())
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_list_main, new AdvertisementsFragment(acceptVacancy, myVacancy))
                .commit();
    }

    @Override
    public void openLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public String getLogin(){
        return getIntent().getStringExtra("login");
    }

    @Override
    public void openSaved() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.button_top_menu_fragment, new ProfileTopButtonMenuFragment())
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_list_main, new ListFragment(savedVacancy))
                .commit();
    }
    public void setSavedVacancy(JobForm[] fullVacancy) {
        savedVacancy = fullVacancy;
        for(JobForm form: fullVacancyUser){
            for(JobForm form2: fullVacancy){
                if (form.id == form2.id){
                    form.isLiked=true;
                    form2.isLiked = true;
                }
            }
        }
        System.out.println("chek");
        getAcceptVacancy();

    }

    public void getSavedVacancy(JobForm[] fullVacancy) {
        System.out.println("palto");
        List<JobForm> list = new ArrayList<>();
        acceptVacancy = fullVacancy;
        for(JobForm form: fullVacancyUser){
            for(JobForm form2: fullVacancy){
                if (form.id == form2.id){
                    form.isAccept=true;
                    form2.isAccept = true;
                }
            }
        }
        for(JobForm form: savedVacancy){
            for(JobForm form2: fullVacancy){
                if (form.id == form2.id){
                    form.isAccept=true;
                    form2.isAccept = true;
                }
            }
        }
        for(JobForm form: fullVacancy){
            for(JobForm form2: savedVacancy){
                if (form.id == form2.id){
                    form.isLiked=true;
                    form2.isLiked = true;
                }
            }
        }
        for(JobForm form: fullVacancyUser){
            if(form.user.equals(getIntent().getStringExtra("login"))){
                list.add(form);}
        }
        JobForm[] myVacancyJob = new JobForm[list.size()];
        for (int i = 0; i < list.size(); i++) {
            myVacancyJob[i] = list.get(i);
        }
        myVacancy = myVacancyJob;
//        for(JobForm form: savedVacancy){
//            for(JobForm form2: fullVacancy){
//                if (form.id == form2.id){
//                    form.isLiked=true;
//                    form2.isLiked = true;
//                }
//            }
//        }

        System.out.println(changeET);
        if (updateList){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_list_main, new ListFragment(fullVacancyUser))
                    .commit();
//            if (!changeET){
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.button_top_menu_fragment, new TopButtonMenuFragment())
//                        .commit();}
            }
        changeET = false;
        updateList = true;
    }

    public void setTopMenu(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.button_top_menu_fragment, new TopButtonMenuFragment())
                .commit();
    }

    public Integer[] getAllid(){
        Integer[] id = new Integer[savedVacancy.length];
        for (int i = 0; i<savedVacancy.length; i++) {
            id[i] = savedVacancy[i].id;
        }
        return id;
    }

    public Integer[] getAllAcceptId(){
        Integer[] id = new Integer[acceptVacancy.length];
        for (int i = 0; i<acceptVacancy.length; i++) {
            id[i] = acceptVacancy[i].id;
        }
        return id;
    }

    @Override
    public void changeLiked(int id) {
        List<Integer> list = new ArrayList<>();
        List<JobForm> list2 = new ArrayList<>();
        for(int i:getAllid()){
            list.add(i);
        }
        Integer index = list.indexOf(id);
        if (index != -1) {
            JobForm[] newSavedVacancy = new JobForm[savedVacancy.length - 1];
            list.remove((int) index);
            for(JobForm i:savedVacancy){
                if (i.id != id){
                list2.add(i);}
            }
            for (int i = 0; i < list2.size(); i++) {
                newSavedVacancy[i] = list2.get(i);
            }
            savedVacancy = newSavedVacancy;
            System.out.println("askjdhflkajsdhflkja");
        } else if(index==-1) {
            list.add(id);
            System.out.println("testtesttest");
        }
        System.out.println(list);
        Integer[] finalmassiv = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            finalmassiv[i] = list.get(i);
            System.out.println(finalmassiv[i]);
        }
        if (finalmassiv.length == 0){
            Integer[] numbers = {-1};
            finalmassiv = numbers;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8100/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        UpdateSavedService service = retrofit.create(UpdateSavedService.class);
        Call<String> call = service.updateSavedIdByLogin(finalmassiv, getIntent().getStringExtra("login"));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String ans = response.body().toString();
                    System.out.println(ans);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_list_main, new LoadingFragment())
                            .commit();
                    makeMonth();
                    setTopMenu();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast toast = Toast.makeText(ListActivity.this, "Ooops...", Toast.LENGTH_LONG);
                toast.show();
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    @Override
    public void changeAccept(int id) {
        List<Integer> list = new ArrayList<>();
//        List<JobForm> list2 = new ArrayList<>();
        for(int i:getAllAcceptId()){
            list.add(i);
        }
        list.add(id);
//        Integer index = list.indexOf(id);
//        if (index != -1) {
//            JobForm[] newSavedVacancy = new JobForm[savedVacancy.length - 1];
//            list.remove((int) index);
//            for(JobForm i:savedVacancy){
//                if (i.id != id){
//                    list2.add(i);}
//            }
//            for (int i = 0; i < list2.size(); i++) {
//                newSavedVacancy[i] = list2.get(i);
//            }
//            savedVacancy = newSavedVacancy;
//            System.out.println("askjdhflkajsdhflkja");
//        } else if(index==-1) {
//            list.add(id);
//            System.out.println("testtesttest");
//        }
        System.out.println(list);
        Integer[] finalmassiv = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            finalmassiv[i] = list.get(i);
            System.out.println(finalmassiv[i]);
        }
//        if (finalmassiv.length == 0){
//            Integer[] numbers = {-1};
//            finalmassiv = numbers;
//        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8100/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        UpdateAcceptService service = retrofit.create(UpdateAcceptService.class);
        Call<String> call = service.updateAcceptIdBylogin(finalmassiv, getIntent().getStringExtra("login"));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String ans = response.body().toString();
                    System.out.println(ans);
                    updateList = false;
                    makeMonth();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast toast = Toast.makeText(ListActivity.this, "Ooops...", Toast.LENGTH_LONG);
                toast.show();
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    @Override
    public void getSavedVacncybylogin() {
            System.out.println("11lkdjfkasjdasd");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.56.1:8100/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            SavedVacancyService service = retrofit.create(SavedVacancyService.class);
            Call<String> call = service.findvacanciesbylogin(getIntent().getStringExtra("login"));
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    System.out.println("test");
                    if (response.isSuccessful()) {
                        String ans = response.body().toString();
                        Gson gson = new Gson();
                        Type jobItemListType = new TypeToken<List<JobForm>>() {
                        }.getType();

                        // Парсинг JSON в список объектов
                        List<JobForm> allVacancy = gson.fromJson(ans, jobItemListType);
                        JobForm[] AllofVacancy = new JobForm[allVacancy.size()];
                        // Вывод для проверки (например, в лог)
                        for (int i = 0; i < allVacancy.size(); i++) {
                            AllofVacancy[i] = allVacancy.get(i);
                        }
                        System.out.println("saved");
                        setSavedVacancy(AllofVacancy);
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast toast = Toast.makeText(ListActivity.this, "Ooops...", Toast.LENGTH_LONG);
                    toast.show();
                    Log.d("MainActivity", t.getMessage());
                }
            });

    }
    @Override
    public void getAcceptVacancy() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8100/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        GetAcceptVacancyService service = retrofit.create(GetAcceptVacancyService.class);
            Call<String> call = service.findgetvacanciesbylogin(getIntent().getStringExtra("login"));
        System.out.println("llll");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    System.out.println("succsess");
                    String ans = response.body().toString();
                    Gson gson = new Gson();
                    Type jobItemListType = new TypeToken<List<JobForm>>() {
                    }.getType();
                    // Парсинг JSON в список объектов
                    List<JobForm> allVacancy = gson.fromJson(ans, jobItemListType);
                    JobForm[] AllofVacancy = new JobForm[allVacancy.size()];
                    // Вывод для проверки (например, в лог)
                    for (int i = 0; i < allVacancy.size(); i++) {
                        AllofVacancy[i] = allVacancy.get(i);
                    }
                    getSavedVacancy(AllofVacancy);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast toast = Toast.makeText(ListActivity.this, "Ooops...", Toast.LENGTH_LONG);
                toast.show();
                Log.d("MainActivity", t.getMessage());
            }
        });
}

    @Override
    public void openVacancy(JobForm jobForm) {
        Intent intent = new Intent(this, JobFormActivityDemo.class);
        String login = getIntent().getStringExtra("login");
        intent.putExtra("login", login);
        intent.putExtra("job_form", jobForm);
        startActivity(intent);
    }
}
