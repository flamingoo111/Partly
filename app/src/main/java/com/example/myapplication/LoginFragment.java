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

public class LoginFragment extends Fragment {
    Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.login_fragment, container, false);

        Button login_button = view.findViewById(R.id.login_btn);
        EditText login = view.findViewById(R.id.loginLoginET);
        EditText password = view.findViewById(R.id.loginLoginET);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((Postman) activity).fragmentMail(login.getText(), password.getText());
                } catch (ClassCastException ignored) {}
            }
        });
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            activity=(Activity) context;
        }
    }
}