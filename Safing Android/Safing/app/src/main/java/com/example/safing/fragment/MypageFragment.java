package com.example.safing.fragment;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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


    public MypageFragment(Context context){
        this.context = context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        mypage_setting = rootView.findViewById(R.id.mypage_setting);
        mypage_add = rootView.findViewById(R.id.mypage_add);
        //셋팅메뉴 이동

        mypage_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QNAActivity.class);
                startActivity(intent);
            }
        });

        mypage_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SettingActivity.class);
                startActivity(intent);
            }
        });



        return rootView;
    }
}