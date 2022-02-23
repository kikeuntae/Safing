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

        KakaoSdk.init(this, "5f751e504b5fd8ab19b195be254992ae");//카카오

        authLogin = OAuthLogin.getInstance();
        authLogin.showDevelopersLog(true);
        authLogin.init(
                LoginActivity.this,
                "jP8OUbSsgMTpkHgqkM4B",
                "VV7lUj9G96",
                "Safing1"
        );//네이버

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
                //명시적 인텐트 ( 어디위치로 가고 작업을 어떤걸 할건지 우리가 이미 인지한상태)
            }
        });




        //https://developers.kakao.com/docs/latest/ko/kakaologin/android
        //kotlin ↓ 자바로 바꿈
        Function2<OAuthToken, Throwable , Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null){
                    Toast.makeText(LoginActivity.this, "정보를 받아옴", Toast.LENGTH_SHORT).show();
                    getKakaoinfo();
                }
                if(throwable != null){
                    Toast.makeText(LoginActivity.this, "뭔가 오류가남.", Toast.LENGTH_SHORT).show();
                }

                return null;
            }
        };

        imgv_kakaologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    Toast.makeText(LoginActivity.this, "카카오톡 설치 됨", Toast.LENGTH_SHORT).show();
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this,callback);
                }else{
                    Toast.makeText(LoginActivity.this, "카카오톡 설치 안됨", Toast.LENGTH_SHORT).show();
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


        btn_login.setOnClickListener(new View.OnClickListener() {//로그인 버튼
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
                        Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,SettingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "로그인 실패!", Toast.LENGTH_SHORT).show();
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
            throw new RuntimeException("API 응답을 읽는데 실패했습니다. ", e);
        }




    }
    // 성공 후 이동할 액티비티
    protected void redirectSignupActivity() {
        final Intent intent = new Intent(LoginActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();

    }




    public void getKakaoinfo(){
        UserApiClient.getInstance().me( (user, throwable) -> {
            if(throwable != null){
                //오류가 났을때 어떤 오류인지 알아볼수가 있음 . KOE + 숫자
            }else{
                // [ {  }  ] json 구조처럼 바로 데이터가 있는게 아니라 Account라는 키로 한칸을 들어가고
                //여기안에서 또 profile이라는 칸으로 또 이동 .
                Account kakaoAcount = user.getKakaoAccount();
                String email = kakaoAcount.getEmail();
                if(kakaoAcount != null){
                    Profile profile = kakaoAcount.getProfile();
                    if(profile != null){
                        Toast.makeText(LoginActivity.this, email +"님 환영", Toast.LENGTH_SHORT).show();
                        goMain();

                    }
                }
            }

            return  null;
        }) ;
    }
    //현재 상태 -> 일반 로그인 1 , Kakao 로그인 2 , Google Login 3 , FaceBook Login 4..
    //Naver Login 5
    public void goMain(){
        //is<- boolean을 리턴하는 메소드의 명명규칙
        //자동로그인이 체크 되어있을때는 아이디와 비밀번호를 공유 자원에 넣어둔다.
        //SharedPreferences <- 라는 공유자원에 id , pw <- ?
        //일반로그인의 경우 Edittext에 있는 정보 , 소셜로그인 ?
        //DB 일반id , pw .... kakao , naver
        //select * from member where kakao = ? => id , pw

        if(chk_auto.isChecked()){
            Toast.makeText(LoginActivity.this, "자동 로그인 체크가 되어있습니다.", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            //초기에 우리가 공유자원에 어떤 데이터를 key로 지정을해서 넣어놓지 않은 상태에서 공유자원에 데이터
            //있는지 먼저 확인하는 작업
            SharedPreferences.Editor editor = preferences.edit();//Editor 리턴하는 메소드.
            editor.putString("id", edt_id.getText() + "");
            editor.putString("pw", edt_pw.getText() + "");
            editor.apply(); // commit이랑 같은 개념 데이터를 넣고 반드시 써줘야함
        }else{
            //2가지 방법 ( 지우기 ) - key를 이용해서 특정 데이터만 삭제하는 방법
            //                      - 공유자원에 있는 모든 데이터를 삭제하는 방법
            //                      - 내가 사용하는 어플이 캐시,데이터가 큰 경우 삭제해보고 다시 어플 사용.
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            //1 remove ( key )
            editor.remove("id");
            editor.remove("pw");
            editor.apply();
            //2. clear <- 모든 정보가 삭제 되기때문에 주의 해야함.
            //editor.clear();
            // editor.apply();

           // Toast.makeText(LoginActivity.this, "체크가 X되어있습니다.", Toast.LENGTH_SHORT).show();

        }


        Intent intent = new Intent(LoginActivity.this, SettingActivity.class);
        startActivity(intent);

    }

//// 카카오톡으로 로그인
//UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
//        if (error != null) {
//            Log.e(TAG, "로그인 실패", error)
//        }
//        else if (token != null) {
//            Log.i(TAG, "로그인 성공 ${token.accessToken}")
//        }
//    }




}