package com.example.safing.fragment;

import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
import android.media.Image;
=======
>>>>>>> origin/main
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.example.safing.activity.QNAActivity;
import com.example.safing.activity.SettingActivity;

public class MypageFragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    MainActivity mainActivity = new MainActivity();
    ImageView mypage_setting,mypage_add;
    Button mypage_login;
    TextView mypage_id;

    public MypageFragment(Context context){
        this.context = context;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

<<<<<<< HEAD
=======
        mainActivity = (MainActivity) getActivity();

>>>>>>> origin/main
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
<<<<<<< HEAD

=======
                mainActivity.changeFragment(new LoginFragment(context));
>>>>>>> origin/main
            }
        });



        mypage_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent intent = new Intent(context, QNAActivity.class);
                startActivity(intent);
=======
                mainActivity.changeFragment(new QNAFragment(context));
>>>>>>> origin/main
            }
        });

        mypage_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent intent = new Intent(context, SettingActivity.class);
                startActivity(intent);
=======
                mainActivity.changeFragment(new SettingFragment(context));
>>>>>>> origin/main
            }
        });



        return rootView;
    }
}