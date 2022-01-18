package com.example.safingproject.activity.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import com.example.safingproject.R;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class PagerActivity extends AppCompatActivity {

    ViewPager2 pager2;
    WormDotsIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        pager2 = findViewById(R.id.pager2);
        indicator = findViewById(R.id.dots_indicator);
        Pager2Adapter adapter = new Pager2Adapter(PagerActivity.this);
        pager2.setAdapter(adapter);
        indicator.setViewPager2(pager2);
    }
}