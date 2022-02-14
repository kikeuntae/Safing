package com.example.safing.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.safing.MainActivity;
import com.example.safing.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingFragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    MainActivity mainActivity = new MainActivity();

    private boolean fabMain_status = false;

    private FloatingActionButton fabMain;
    private FloatingActionButton fabCamera;
    private FloatingActionButton fabEdit;


    public SettingFragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);

        fabMain = rootView.findViewById(R.id.fab);
        fabCamera = rootView.findViewById(R.id.fab1);
        fabEdit = rootView.findViewById(R.id.fab2);

        // 메인플로팅 버튼 클릭
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFab();
            }
        });

        // 문의 플로팅 버튼 클릭
        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "1:1문의함으로 연결합니다", Toast.LENGTH_SHORT).show();
            }
        });

        // FAQ 플로팅 버튼 클릭
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "FAQ로 연결합니다", Toast.LENGTH_SHORT).show();
            }
        });


    // 플로팅 액션 버튼 클릭시 애니메이션 효과



        return rootView;
    }

    public void toggleFab() {
        if(fabMain_status) {
            // 플로팅 액션 버튼 닫기
            // 애니메이션 추가
            ObjectAnimator fc_animation = ObjectAnimator.ofFloat(fabCamera, "translationY", 0f);
            fc_animation.start();
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fabEdit, "translationY", 0f);
            fe_animation.start();
            // 메인 플로팅 이미지 변경
            fabMain.setImageResource(R.drawable.btn_signin_twitter);

        }else {
            // 플로팅 액션 버튼 열기
            ObjectAnimator fc_animation = ObjectAnimator.ofFloat(fabCamera, "translationY", -200f);
            fc_animation.start();
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fabEdit, "translationY", -400f);
            fe_animation.start();
            // 메인 플로팅 이미지 변경
            fabMain.setImageResource(R.drawable.plus);
        }
        // 플로팅 버튼 상태 변경
        fabMain_status = !fabMain_status;
    }

}