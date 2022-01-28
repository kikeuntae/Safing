package com.example.safing.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.safing.DTO.SafeZoneRecDTO;
import com.example.safing.DTO.ThemeRecDTO;
import com.example.safing.R;
import com.example.safing.activity.HomeSearchActivity;
import com.example.safing.activity.MainActivity;
import com.example.safing.adapter.SafeZoneRecAdapter;
import com.example.safing.adapter.Theme_Pager_Adapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recysf ;
    Context context;
    ViewPager2 pager2 , recyth;
    ImageView home_search;
    MainActivity mainActivity = new MainActivity();
<<<<<<< HEAD
=======
    LinearLayout youtubetip1;
<<<<<<< HEAD
=======

>>>>>>> origin/기근태

>>>>>>> origin/main


    public HomeFragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        recysf = rootView.findViewById(R.id.recysfzone1);
        recyth = rootView.findViewById(R.id.rectheme);
<<<<<<< HEAD
=======
        recyoutube = rootView.findViewById(R.id.youtubetip);
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/main
        sguse = rootView.findViewById(R.id.sguse);
        pager2 = rootView.findViewById(R.id.rectheme);
        youtubetip1 = rootView.findViewById(R.id.youtubetip1);
        home_search = rootView.findViewById(R.id.home_search);
        packgemore = rootView.findViewById(R.id.packgemore);
<<<<<<< HEAD
=======
=======
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
>>>>>>> origin/main

>>>>>>> origin/기근태
        //====================Recysfzone=====================================//
        recysf.findViewById(R.id.recysfzone1);

        ArrayList<SafeZoneRecDTO> list = new ArrayList<>();
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));

        SafeZoneRecAdapter adapter1 = new SafeZoneRecAdapter(context, list);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
        );
        recysf.setLayoutManager(layoutManager1);
        recysf.setAdapter(adapter1);

        //=====================pagerTheme===================================//
        pager2 = rootView.findViewById(R.id.rectheme);
        ArrayList<ThemeRecDTO> list1 = new ArrayList<>();
        list1.add(new ThemeRecDTO(R.layout.rec_item_theme, ""));
        list1.add(new ThemeRecDTO(R.layout.rec_item_theme, ""));
        list1.add(new ThemeRecDTO(R.layout.rec_item_theme, ""));
        list1.add(new ThemeRecDTO(R.layout.rec_item_theme, ""));
        list1.add(new ThemeRecDTO(R.layout.rec_item_theme, ""));
        list1.add(new ThemeRecDTO(R.layout.rec_item_theme, ""));
        list1.add(new ThemeRecDTO(R.layout.rec_item_theme, ""));

        Theme_Pager_Adapter adapter = new Theme_Pager_Adapter(context);
        pager2.setAdapter(adapter);
        //=====================pagerTheme===================================//

<<<<<<< HEAD
        home_search = rootView.findViewById(R.id.home_search);
=======



<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/main
        sguse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(), SafeGuardInfoActivity.class);
                startActivity(intent);
            }
        });
<<<<<<< HEAD


=======


=======
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)











        home_search = rootView.findViewById(R.id.home_search);
>>>>>>> origin/main
>>>>>>> origin/main
>>>>>>> origin/기근태
        home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, HomeSearchActivity.class);
                startActivity(intent);
                mainActivity = (MainActivity) getActivity();
                mainActivity.overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_left_exit);
            }
        });


<<<<<<< HEAD
=======
        packgemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new Product_Package_Fragment(context));
            }
        });
<<<<<<< HEAD
=======




>>>>>>> origin/main


>>>>>>> origin/기근태



        return rootView;
    }
}