package com.example.safing.mypage.fragment;

import android.content.Context;
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
import com.example.safing.MainActivity;
import com.example.safing.mypage.fragment.LoginFragment;
import com.example.safing.mypage.fragment.QNAFragment;
import com.example.safing.mypage.fragment.SettingFragment;

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

        mainActivity = (MainActivity) getActivity();

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
                mainActivity.changeFragment(new QNAFragment(context));
            }
        });

        mypage_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.changeFragment(new SettingFragment(context));
            }
        });



        return rootView;
    }
}