package com.example.safing.mypage.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safing.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.navercorp.nid.oauth.NidOAuthLogin;
import com.navercorp.nid.oauth.NidOAuthLoginState;
import com.nhn.android.naverlogin.OAuthLogin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SettingActivity extends AppCompatActivity {

    // 플로팅버튼 상태
    private boolean fabMain_status = false;

    private FloatingActionButton fabMain;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        fabMain = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        getHashKey();
        // 메인플로팅 버튼 클릭
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFab();

            }
        });
        // 문의 플로팅 버튼 클릭
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "1:1문의함으로 연결합니다", Toast.LENGTH_SHORT).show();
            }
        });

        // FAQ 플로팅 버튼 클릭
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "FAQ로 연결합니다", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NidOAuthLoginState.OK.equals(OAuthLogin.getInstance().getState(SettingActivity.this))){
                    NidOAuthLogin aa = new NidOAuthLogin();
                    aa.logout();
                }
                Toast.makeText(SettingActivity.this, "로그아웃 성공!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    // 플로팅 액션 버튼 클릭시 애니메이션 효과
    public void toggleFab() {
        if(fabMain_status) {
            // 플로팅 액션 버튼 닫기
            // 애니메이션 추가
            ObjectAnimator fc_animation = ObjectAnimator.ofFloat(fab1, "translationY", 0f);
            fc_animation.start();
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fab2, "translationY", 0f);
            fe_animation.start();
            // 메인 플로팅 이미지 변경
            fabMain.setImageResource(R.drawable.img_5);

        }else {
            // 플로팅 액션 버튼 열기
            ObjectAnimator fc_animation = ObjectAnimator.ofFloat(fab1, "translationY", -200f);
            fc_animation.start();
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fab2, "translationY", -400f);
            fe_animation.start();
            // 메인 플로팅 이미지 변경
            fabMain.setImageResource(R.drawable.img_3);
        }
        // 플로팅 버튼 상태 변경
        fabMain_status = !fabMain_status;


        //인텐트
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QNAActivity.class);
                startActivity(intent);
            }
        });


    }
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

}