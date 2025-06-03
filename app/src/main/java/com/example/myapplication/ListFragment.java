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

public class ListFragment extends Fragment {
    JobForm[] fullVacancy;

    public ListFragment(JobForm[] fullVacancy) {
        this.fullVacancy = fullVacancy;
    }

    interface OnFragmentSendDataListenerList {
        void openVacancy(JobForm jobForm);
    }
    private OnFragmentSendDataListenerList fragmentSendDataListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Сохраняем inflater и container, если они нужны для других операций
        try {
            View view = inflater
                    .inflate(R.layout.fragment_list, container, false);
            ListView lv = view.findViewById(R.id.mainListView);
            VacationAdapter adapter = new VacationAdapter((Context) fragmentSendDataListener, fullVacancy);
//            lv.setOnItemClickListener((parent, view1, position, id) -> {
//                System.out.println(1231232);
//                JobForm jf = (JobForm) parent.getItemAtPosition(position);
//                fragmentSendDataListener.openVacancy(jf);
//            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println(12312312);
                    JobForm jf = (JobForm) parent.getItemAtPosition(position);
                    System.out.println(jf.name);
                    fragmentSendDataListener.openVacancy(jf);
                }
            });

            lv.setAdapter(adapter);
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
            fragmentSendDataListener = (OnFragmentSendDataListenerList) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

//    public void setVacancy(Vacation[] vacancy){
//        try {
//            this.view = infl
//                    .inflate(R.layout.fragment_list, cont, false);
//            ListView lv = this.view.findViewById(R.id.mainListView);
//            VacationAdapter adapter = new VacationAdapter((Context) fragmentSendDataListener, vacancy);
//            lv.setAdapter(adapter);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}