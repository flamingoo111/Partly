package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BottomButtonMenuFragment extends Fragment {
    interface OnFragmentSendDataListenerListBottomButton {
        void openProfile();
        void openHome();
        void openAdvertisements();
    }
    private OnFragmentSendDataListenerListBottomButton fragmentSendDataListener;
    String login;

//    public BottomButtonMenuFragment(String login) {
//        this.login = login;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.bottom_navigation, container, false);

        Button home = view.findViewById(R.id.home_btn);
        Button my_advertisements = view.findViewById(R.id.advertisements_btn);
        Button chat = view.findViewById(R.id.chat_btn);
        Button profile = view.findViewById(R.id.my_profile);
        Button advertisements_btn = view.findViewById(R.id.advertisements_btn);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentSendDataListener.openProfile();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentSendDataListener.openHome();
            }
        });
        advertisements_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentSendDataListener.openAdvertisements();
            }
        });

        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListenerListBottomButton) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
}