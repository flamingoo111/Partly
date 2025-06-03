package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoadingFragment extends Fragment {

    private ListFragment.OnFragmentSendDataListenerList fragmentSendDataListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Сохраняем inflater и container, если они нужны для других операций
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        return view;
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