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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {
    String login;
    interface OnFragmentSendDataListenerListProfile {
        void openLogin();
        String getLogin();
        void openSaved();
    }
    public ProfileFragment(String login) {
        this.login = login;
    }

    private OnFragmentSendDataListenerListProfile fragmentSendDataListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Сохраняем inflater и container, если они нужны для других операций
        try {
            View view = inflater
                    .inflate(R.layout.fragment_profile, container, false);
            ImageButton logout = view.findViewById(R.id.logout_button);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentSendDataListener.openLogin();
                }
            });
            Button personal_info = view.findViewById(R.id.personal_info);
            personal_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentSendDataListener.openSaved();
                }
            });
            TextView user_name = view.findViewById(R.id.user_name);
            user_name.setText(fragmentSendDataListener.getLogin());
            return view;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListenerListProfile) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
}
