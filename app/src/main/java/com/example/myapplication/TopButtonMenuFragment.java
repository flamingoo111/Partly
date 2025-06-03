package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TopButtonMenuFragment extends Fragment {
    interface OnFragmentSendDataListenerListButton {
        void openActivityJobForm();
        void findVacancyByName(String name);
        void changeETfunc();
    }
    private OnFragmentSendDataListenerListButton fragmentSendDataListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.top_fragment_menu, container, false);

        AppCompatImageButton addVacancy = view.findViewById(R.id.plusButton);
        addVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataListener.openActivityJobForm();
            }
        });

        EditText eTxt = (EditText) view.findViewById(R.id.searchEditText);

        eTxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.equals("")){
                    fragmentSendDataListener.changeETfunc();
                    fragmentSendDataListener.findVacancyByName(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListenerListButton) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
}