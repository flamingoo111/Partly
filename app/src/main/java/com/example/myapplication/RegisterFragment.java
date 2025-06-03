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
import android.widget.Toast;

public class RegisterFragment extends Fragment {
    Activity activity;

    private LoginFragment.OnFragmentSendDataListener fragmentSendDataListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.register_fragment, container, false);

        Button login_button = view.findViewById(R.id.login_btn);
        EditText login = view.findViewById(R.id.loginLoginET);
        EditText password = view.findViewById(R.id.loginPasswordET);
        EditText password_confirm = view.findViewById(R.id.loginPasswordconfirmET);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(password_confirm.getText().toString())){
                    fragmentSendDataListener.onRegisterData(login.getText().toString(), password.getText().toString());
                }else{
                    System.out.println(password.getText().toString() + "    " + password_confirm.getText().toString() );
                    Toast.makeText((Context) fragmentSendDataListener, "Пароль не верный", Toast.LENGTH_LONG).show();}
            }
        });
        TextView tv = view.findViewById(R.id.createAccountText);
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fragmentSendDataListener.onChangeFragment(new LoginFragment());
            }
        });
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (LoginFragment.OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
}