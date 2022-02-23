package com.example.safing.mypage.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.safing.R;


public class FindActivity extends AppCompatActivity {

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);


        findViewById(R.id.btn_find1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentView(Fragment_1);

            }
        });

        findViewById(R.id.btn_find2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(Fragment_2);


            }
        });
        FragmentView(Fragment_1);
    }

    private void FragmentView(int fragment){

        //FragmentTransactiom를 이용해 프래그먼트를 사용합니다.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment){
            case 1:
                // 첫번 째 프래그먼트 호출
                FindFragment fragment1 = new FindFragment(FindActivity.this);
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // 두번 째 프래그먼트 호출
                FindFragment2 fragment2 = new FindFragment2(FindActivity.this);
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;
        }

    }


}
