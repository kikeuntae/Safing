package com.example.safing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.safing.iot.fragment.IoTFragment;
<<<<<<< HEAD
import com.example.safing.shop.VO.PurchaseHistoryVO;
import com.example.safing.shop.fragment.Product_Fragment;
import com.example.safing.shop.fragment.Product_Package_Fragment;
=======
import com.example.safing.movie.fragment.MovieFragment;
import com.example.safing.mypage.fragment.LoginActivity;
import com.example.safing.mypage.fragment.MypageFragment;
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
import com.example.safing.shop.fragment.Product_PurchaseHistory_Fragment;
import com.example.safing.shop.fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom_nav;
    private long backpressedTime = 0;
    Fragment backFragment ;
    Fragment nowFragment ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy);}

        bottom_nav = findViewById(R.id.bottom_nav);

        Intent intent = getIntent();
        String chageFrag = intent.getStringExtra("fragment");
<<<<<<< HEAD
        PurchaseHistoryVO vo = (PurchaseHistoryVO) intent.getSerializableExtra("vo");
=======
        nowFragment = new HomeFragment(MainActivity.this);
        changeFragment(nowFragment);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.tab1){

                    return true;
                }else if(item.getItemId() == R.id.tab2){

                    return true;
                }else if(item.getItemId() == R.id.tab3){
                    changeFragment(new IoTFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab4){
                    changeFragment(new ShopFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab5){
<<<<<<< HEAD

=======
                    //Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                    //startActivity(intent1);
                    changeFragment(new MypageFragment(MainActivity.this));
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
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

        if ( HomeFragment.class.isInstance(nowFragment) && System.currentTimeMillis() > backpressedTime + 2000) {
            backpressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'??????\' ????????? ?????? ??? ???????????? ???????????????.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() <= backpressedTime + 2000) {
            finish();
            //onDestroy();
        } else{
            if(backFragment == null){
                changeFragment(new HomeFragment(MainActivity.this));
            }else{
                changeFragment(backFragment);
            }

        }


    }

}