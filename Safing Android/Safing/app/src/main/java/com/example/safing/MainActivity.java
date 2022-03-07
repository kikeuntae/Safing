package com.example.safing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.inspector.StaticInspectionCompanionProvider;
import android.widget.Toast;

import com.example.safing.async.CommonVal;
import com.example.safing.home.fragment.HomeFragment;
import com.example.safing.iot.fragment.IoTFragment;
import com.example.safing.movie.fragment.MovieFragment;
import com.example.safing.mypage.VO.MemberVO;
import com.example.safing.mypage.fragment.MypageFragment;
import com.example.safing.shop.VO.PurchaseHistoryVO;
import com.example.safing.shop.fragment.Product_Fragment;
import com.example.safing.shop.fragment.Product_Package_Fragment;
import com.example.safing.shop.fragment.Product_PurchaseHistory_Fragment;
import com.example.safing.shop.fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom_nav;
    private long backpressedTime = 0;
    Fragment backFragment ;
    Fragment nowFragment ;
    private static MemberVO login_member = new MemberVO();

    // 장바구니 선택삭제 부분 디자인 변경
    // 주문삭제 이후 금액 변경이 안됨
    // db에서 실제 삭제가 안됨
    // 구매하기 (로그인정보값)
    // 영상 재생/중지 버튼 중앙 이동
    // 로딩중 프로그래스 다이아로그 띄우기
    // 댓글 디자인 변경
    // 좋아요 토스트 메시지 삭제
    // 마이페이지 스크롤, 디자인 처리



    public static void setLogin_member(MemberVO login_member) {
        MainActivity.login_member = login_member;
    }

    public static MemberVO getLogin_member() {
        return login_member;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHashKey();

        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy);}
        bottom_nav = findViewById(R.id.bottom_nav);

        Intent intent = getIntent();
        String chageFrag = intent.getStringExtra("fragment");
        PurchaseHistoryVO vo = (PurchaseHistoryVO) intent.getSerializableExtra("vo");
        nowFragment = new HomeFragment(MainActivity.this);
        changeFragment(nowFragment);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }



        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.tab1){
                    changeFragment(new HomeFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab2){
                    changeFragment(new MovieFragment(MainActivity.this));

                    return true;
                }else if(item.getItemId() == R.id.tab3){
                    changeFragment(new IoTFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab4){
                    changeFragment(new ShopFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab5){
                    changeFragment(new MypageFragment(MainActivity.this));
                    return true;
                }
                return false;
            }
        });

        if(("Product_PurchaseHistory_Fragment").equals(chageFrag)){
            changeFragment(new Product_PurchaseHistory_Fragment(MainActivity.this));
        } else if(("review").equals(chageFrag)){
            if(vo.getProduct_num()> 0){
                changeFragment(new Product_Fragment(MainActivity.this, vo.getProduct_num(), "review"));
            } else {
                changeFragment(new Product_Package_Fragment(MainActivity.this, vo.getPackage_num(), "review"));
            }
        }
    }
    public void changeFragment(Fragment fragment){
        if(backFragment != nowFragment){
            backFragment = nowFragment ;
        }
        nowFragment = fragment ;
        getSupportFragmentManager().beginTransaction().replace(R.id.container , nowFragment).commit();
    }
    @Override
    public void onBackPressed() {

        if (HomeFragment.class.isInstance(nowFragment) && System.currentTimeMillis() > backpressedTime + 2000) {
            backpressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() <= backpressedTime + 2000) {
            finish();
            //onDestroy();
        } else {
            if (backFragment == null) {
                changeFragment(new HomeFragment(MainActivity.this));
            } else {
                changeFragment(backFragment);
            }

        }


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
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }




}