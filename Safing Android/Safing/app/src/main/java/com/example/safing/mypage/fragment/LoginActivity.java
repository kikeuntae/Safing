package com.example.safing.mypage.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safing.R;
import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.async.NaverGetAsk;
import com.example.safing.mypage.VO.MemberVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.kakao.sdk.user.model.Profile;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.navercorp.nid.oauth.view.NidOAuthLoginButton;
import com.nhn.android.naverlogin.OAuthLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;


public class LoginActivity extends AppCompatActivity {
    OAuthLogin authLogin ;
    NidOAuthLoginButton loginButton;
    EditText edt_id , edt_pw;
    Button btn_login;
    ImageView imgv_kakaologin;
    CheckBox chk_auto;
    Button btn;
    CommonAsk service;
    Gson gson = new GsonBuilder().serializeNulls().create();
    MemberVO dto = new MemberVO();
    InputStream in;
    Button find;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);






       /* dto.setMember_id("Yasuo");
        dto.setMember_age(23);
        dto.setMember_pw("yasuo1212");
        dto.setMember_phone("yasuo");
        dto.setMember_name("yasuo");
        service = new CommonAsk("join.me");
        String str = gson.toJson(dto);
        service.params.add(new com.example.safing.async.AskParam("vo", str));
        com.example.safing.async.CommonMethod.excuteAsk(service);*/

        /*Drawable alpha = (findViewById(R.id.loginlayout)).getBackground();
        alpha.setAlpha(230);*/

        KakaoSdk.init(this, "5f751e504b5fd8ab19b195be254992ae");//?????????

        authLogin = OAuthLogin.getInstance();
        authLogin.showDevelopersLog(true);
        authLogin.init(
                LoginActivity.this,
                "jP8OUbSsgMTpkHgqkM4B",
                "VV7lUj9G96",
                "Safing1"
        );//?????????

        loginButton = findViewById(R.id.naver_login);

        loginButton.setOAuthLoginCallback(new OAuthLoginCallback() {

            @Override
            public void onSuccess() {
                String Authorization =  "Bearer " + authLogin.getAccessToken(LoginActivity.this);
                NaverGetAsk ask = new NaverGetAsk(Authorization);

                InputStream in = null;
                try {
                    in = ask.execute().get();
                    readBody(in);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                redirectSignupActivity();

                //  new RequestApiTask(MainActivity.this, authLogin).execute();
            }

            @Override
            public void onFailure(int i, String s) {

            }

            @Override
            public void onError(int i, String s) {

            }
        });


        findViewById(R.id.naver_loginim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.callOnClick();
            }
        });



        chk_auto = findViewById(R.id.chk_auto);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);
        imgv_kakaologin = findViewById(R.id.imgv_kakaologin);

        findViewById(R.id.btn_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateActivity.class);
                startActivity(intent);
                //????????? ????????? ( ??????????????? ?????? ????????? ????????? ????????? ????????? ?????? ???????????????)
            }
        });




        //https://developers.kakao.com/docs/latest/ko/kakaologin/android
        //kotlin ??? ????????? ??????
        Function2<OAuthToken, Throwable , Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null){
                    Toast.makeText(LoginActivity.this, "????????? ?????????", Toast.LENGTH_SHORT).show();
                    getKakaoinfo();
                }
                if(throwable != null){
                    Toast.makeText(LoginActivity.this, "?????? ????????????.", Toast.LENGTH_SHORT).show();
                }

                return null;
            }
        };

        imgv_kakaologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ??????????????? ???????????? ????????? ?????????????????? ?????????, ????????? ????????????????????? ?????????
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    Toast.makeText(LoginActivity.this, "???????????? ?????? ???", Toast.LENGTH_SHORT).show();
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this,callback);
                }else{
                    Toast.makeText(LoginActivity.this, "???????????? ?????? ??????", Toast.LENGTH_SHORT).show();
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this,callback);
                }
            }
        });

        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        String id = preferences.getString("id" , null);
        String pw = preferences.getString("pw" , null);
        if(id != null && pw != null){
            edt_id.setText(id);
            edt_pw.setText(pw);
            btn_login.performClick();
        }


        btn_login.setOnClickListener(new View.OnClickListener() {//????????? ??????
            @Override
            public void onClick(View view) {

                dto.setMember_id(edt_id.getText().toString());
                dto.setMember_pw(edt_pw.getText().toString());
                service = new CommonAsk("login.me");
                String str = gson.toJson(dto);
                service.params.add(new AskParam("vo",str));
                //async.AskParam

                in = CommonMethod.excuteAsk(service);
                try {
                    MemberVO vo = gson.fromJson(new InputStreamReader(in), MemberVO.class);
                    if(vo != null){
                        Toast.makeText(LoginActivity.this, "????????? ??????!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,SettingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "????????? ??????!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }


            }
        });
        findViewById(R.id.find_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindActivity.class);
                startActivity(intent);

            }
        });
    }//onCreate


    private String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            Gson gson = new Gson();
            JsonObject jsonObject = new JsonParser().parse(responseBody.toString()).getAsJsonObject();
            jsonObject =(JsonObject) jsonObject.get("response");

            String aa = jsonObject.get("email") + "";
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API ????????? ????????? ??????????????????. ", e);
        }




    }
    // ?????? ??? ????????? ????????????
    protected void redirectSignupActivity() {
        final Intent intent = new Intent(LoginActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();

    }




    public void getKakaoinfo(){
        UserApiClient.getInstance().me( (user, throwable) -> {
            if(throwable != null){
                //????????? ????????? ?????? ???????????? ??????????????? ?????? . KOE + ??????
            }else{
                // [ {  }  ] json ???????????? ?????? ???????????? ????????? ????????? Account?????? ?????? ????????? ????????????
                //??????????????? ??? profile????????? ????????? ??? ?????? .
                Account kakaoAcount = user.getKakaoAccount();
                String email = kakaoAcount.getEmail();
                if(kakaoAcount != null){
                    Profile profile = kakaoAcount.getProfile();
                    if(profile != null){
                        Toast.makeText(LoginActivity.this, email +"??? ??????", Toast.LENGTH_SHORT).show();
                        goMain();

                    }
                }
            }

            return  null;
        }) ;
    }
    //?????? ?????? -> ?????? ????????? 1 , Kakao ????????? 2 , Google Login 3 , FaceBook Login 4..
    //Naver Login 5
    public void goMain(){
        //is<- boolean??? ???????????? ???????????? ????????????
        //?????????????????? ?????? ?????????????????? ???????????? ??????????????? ?????? ????????? ????????????.
        //SharedPreferences <- ?????? ??????????????? id , pw <- ?
        //?????????????????? ?????? Edittext??? ?????? ?????? , ??????????????? ?
        //DB ??????id , pw .... kakao , naver
        //select * from member where kakao = ? => id , pw

        if(chk_auto.isChecked()){
            Toast.makeText(LoginActivity.this, "?????? ????????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            //????????? ????????? ??????????????? ?????? ???????????? key??? ??????????????? ???????????? ?????? ???????????? ??????????????? ?????????
            //????????? ?????? ???????????? ??????
            SharedPreferences.Editor editor = preferences.edit();//Editor ???????????? ?????????.
            editor.putString("id", edt_id.getText() + "");
            editor.putString("pw", edt_pw.getText() + "");
            editor.apply(); // commit?????? ?????? ?????? ???????????? ?????? ????????? ????????????
        }else{
            //2?????? ?????? ( ????????? ) - key??? ???????????? ?????? ???????????? ???????????? ??????
            //                      - ??????????????? ?????? ?????? ???????????? ???????????? ??????
            //                      - ?????? ???????????? ????????? ??????,???????????? ??? ?????? ??????????????? ?????? ?????? ??????.
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            //1 remove ( key )
            editor.remove("id");
            editor.remove("pw");
            editor.apply();
            //2. clear <- ?????? ????????? ?????? ??????????????? ?????? ?????????.
            //editor.clear();
            // editor.apply();

           // Toast.makeText(LoginActivity.this, "????????? X??????????????????.", Toast.LENGTH_SHORT).show();

        }


        Intent intent = new Intent(LoginActivity.this, SettingActivity.class);
        startActivity(intent);

    }

//// ?????????????????? ?????????
//UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
//        if (error != null) {
//            Log.e(TAG, "????????? ??????", error)
//        }
//        else if (token != null) {
//            Log.i(TAG, "????????? ?????? ${token.accessToken}")
//        }
//    }




}