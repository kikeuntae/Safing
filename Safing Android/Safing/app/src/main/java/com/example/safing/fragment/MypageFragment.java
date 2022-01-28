package com.example.safing.fragment;

import android.content.Context;
<<<<<<< HEAD
=======
import android.content.Intent;
<<<<<<< HEAD
<<<<<<< HEAD
import android.media.Image;
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
import android.media.Image;
=======
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
>>>>>>> origin/main
>>>>>>> origin/기근태
=======

>>>>>>> 기근태
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.safing.R;
import com.example.safing.activity.MainActivity;

public class MypageFragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    MainActivity mainActivity = new MainActivity();


    public MypageFragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
        mainActivity = (MainActivity) getActivity();

=======
<<<<<<< HEAD
=======
        mainActivity = (MainActivity) getActivity();

>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
>>>>>>> origin/main
=======
>>>>>>> 기근태
        mypage_setting = rootView.findViewById(R.id.mypage_setting);
        mypage_add = rootView.findViewById(R.id.mypage_add);
        mypage_login = rootView.findViewById(R.id.mypage_login);
        mypage_id = rootView.findViewById(R.id.mypage_tv1);
        //셋팅메뉴 이동


        mypage_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypage_login.setVisibility(View.GONE);
                mypage_id.setVisibility(View.VISIBLE);

                mainActivity.changeFragment(new LoginFragment(context));

            }
        });



        mypage_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QNAActivity.class);
                startActivity(intent);

                mainActivity.changeFragment(new QNAFragment(context));

            }
        });

        mypage_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SettingActivity.class);
                startActivity(intent);
                mainActivity.changeFragment(new SettingFragment(context));

            }
        });

<<<<<<< HEAD


>>>>>>> origin/기근태
=======
>>>>>>> 기근태
        return rootView;
    }
}