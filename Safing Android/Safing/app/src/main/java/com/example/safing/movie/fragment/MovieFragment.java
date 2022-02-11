package com.example.safing.movie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.safing.R;
import com.example.safing.VO.Board_FileVO;
//import com.example.safing.adapter.Moive_Adapter1;
import com.example.safing.movie.adapter.Moive_Adapter2;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MovieFragment extends Fragment {
    Context context;
    TabLayout tab_layout;
    ViewPager2 pager2;
    Moive_Adapter2 adapter;
    int now_state = 0;


    public MovieFragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movie, container, false);

        tab_layout = rootView.findViewById(R.id.movie_tab);
        pager2 = rootView.findViewById(R.id.movie_pager);

        tab_layout.addTab(tab_layout.newTab().setText("감성"));
        tab_layout.addTab(tab_layout.newTab().setText("추천"));

        ArrayList<Board_FileVO> videoItems  = new ArrayList<>();
        adapter =new Moive_Adapter2(getContext() , videoItems);

        videoItems.add(new Board_FileVO(1,"아이디1","#태그1", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        videoItems.add(new Board_FileVO(2,"아이디2","#태그2","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"));
        videoItems.add(new Board_FileVO(3,"아이디3","#태그3","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"));
        videoItems.add(new Board_FileVO(4,"아이디4","#태그4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        videoItems.add(new Board_FileVO(5,"아이디5","#태그5","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                now_state = position;
                adapter.setVideo(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
        pager2.setAdapter(adapter);

        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
                    pager2.setAdapter(adapter);
                } else {
                    pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
                    pager2.setAdapter(adapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setVideo(now_state);
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.setVideo();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

    }
}