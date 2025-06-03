package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class AdvertisementsFragment extends Fragment {
    JobForm[] fullVacancy;
    JobForm[] myVacancy;

    public AdvertisementsFragment(JobForm[] fullVacancy, JobForm[] myVacancy) {
        this.fullVacancy = fullVacancy;
        this.myVacancy = myVacancy;
    }


    private ListFragment.OnFragmentSendDataListenerList fragmentSendDataListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Сохраняем inflater и container, если они нужны для других операций
        try {
            View view = inflater
                    .inflate(R.layout.fragment_list_myandother_vacancy, container, false);
            ListView lv = view.findViewById(R.id.accepted_vacancies_list);
            VacationAdapter adapter = new VacationAdapter((Context) fragmentSendDataListener, fullVacancy);
            lv.setAdapter(adapter);
            ListView lv2 = view.findViewById(R.id.your_vacancies_list);
            VacationAdapter adapter2 = new VacationAdapter((Context) fragmentSendDataListener, myVacancy);
            lv2.setAdapter(adapter2);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println(12312312);
                    JobForm jf = (JobForm) parent.getItemAtPosition(position);
                    System.out.println(jf.name);
                    fragmentSendDataListener.openVacancy(jf);
                }
            });
            lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println(12312312);
                    JobForm jf = (JobForm) parent.getItemAtPosition(position);
                    System.out.println(jf.name);
                    fragmentSendDataListener.openVacancy(jf);
                }
            });
            return view;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Инфлейтим правильный макет

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (ListFragment.OnFragmentSendDataListenerList) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
}