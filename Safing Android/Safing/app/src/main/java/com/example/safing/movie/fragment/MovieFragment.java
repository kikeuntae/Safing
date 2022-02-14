package com.example.safing.movie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.safing.R;
//import com.example.safing.adapter.Moive_Adapter1;
import com.example.safing.movie.DAO.Movie_DAO;
import com.example.safing.movie.DTO.Board_Movie_DTO;
import com.example.safing.movie.adapter.Moive_Adapter1;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {
    Context context;
    TabLayout tab_layout;
    ViewPager2 pager2;
    Moive_Adapter1 adapter1;
    Moive_Adapter1 adapter2;
    int now_state = 0;

    Movie_DAO dao= new Movie_DAO();


    public MovieFragment(Context context){

        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movie, container, false);


        Board_Movie_DTO test = new Board_Movie_DTO();
        test.setMember_id("master");
        test.setBoard_title("test_title_update");
        test.setBoard_content("test_content_update");




        tab_layout = rootView.findViewById(R.id.movie_tab);
        pager2 = rootView.findViewById(R.id.movie_pager);

        tab_layout.addTab(tab_layout.newTab().setText("감성"));
        tab_layout.addTab(tab_layout.newTab().setText("추천"));

        List<Board_Movie_DTO> videoItems  = new ArrayList<>();
        videoItems = dao.list();
        adapter1 =new Moive_Adapter1(getContext() , videoItems);



        List<Board_Movie_DTO> videoItems2  = new ArrayList<>();
        videoItems2 = dao.list();
        adapter2 =new Moive_Adapter1(getContext() , videoItems2);


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                now_state = position;
                adapter1.setVideo(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
        pager2.setAdapter(adapter1);

        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
                    pager2.setAdapter(adapter1);

                } else {
                    pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
                    pager2.setAdapter(adapter2);

                    //dao.create(test);
                    //dao.update(test);
                    //dao.delete(test);
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
        adapter1.setVideo(now_state);
        adapter2.setVideo(now_state);
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter1.setVideo();
        adapter2.setVideo();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

    }


}