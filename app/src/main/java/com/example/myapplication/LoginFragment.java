package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment {
    Activity activity;

    interface OnFragmentSendDataListener {
        void onSendData(String login, String password);
        void onChangeFragment(Fragment fragment);
        void onRegisterData(String login, String password);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.login_fragment, container, false);

        Button login_button = view.findViewById(R.id.login_btn);
        EditText login = view.findViewById(R.id.loginLoginET);
        EditText password = view.findViewById(R.id.loginPasswordET);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataListener.onSendData(login.getText().toString(), password.getText().toString());
            }
        });
        TextView tv = view.findViewById(R.id.createAccountText);
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fragmentSendDataListener.onChangeFragment(new RegisterFragment());
            }
        });
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
}