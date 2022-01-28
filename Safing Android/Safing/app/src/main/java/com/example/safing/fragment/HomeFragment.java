package com.example.safing.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.safing.DTO.SafeZoneRecDTO;
import com.example.safing.DTO.ThemeRecDTO;
import com.example.safing.DTO.YouTubeTipRecDTO;
import com.example.safing.R;
import com.example.safing.activity.CamInfoActivity;
import com.example.safing.activity.HomeSearchActivity;
import com.example.safing.activity.MainActivity;
import com.example.safing.activity.SafeGuardInfoActivity;
import com.example.safing.activity.ThemePagerActivity;
import com.example.safing.activity.TipActivity;
import com.example.safing.adapter.SafeZoneRecAdapter;
import com.example.safing.adapter.Theme_Pager_Adapter;
import com.example.safing.adapter.YouTubeTipRecAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recysf, recyoutube ;
    Context context;
    ViewPager2 pager2 , recyth;
    ImageView home_search, sguse;
    TextView packgemore;
    MainActivity mainActivity = new MainActivity();
    LinearLayout youtubetip1;


    public HomeFragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        recysf = rootView.findViewById(R.id.recysfzone1);
        recyth = rootView.findViewById(R.id.rectheme);
        recyoutube = rootView.findViewById(R.id.youtubetip);
        sguse = rootView.findViewById(R.id.sguse);
        pager2 = rootView.findViewById(R.id.rectheme);
        youtubetip1 = rootView.findViewById(R.id.youtubetip1);
        home_search = rootView.findViewById(R.id.home_search);
        packgemore = rootView.findViewById(R.id.packgemore);

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
        //====================Recysfzone=====================================//

        //=====================RecyclerCamTip========================================//
        recyoutube.findViewById(R.id.youtubetip);

        ArrayList<YouTubeTipRecDTO> list2 = new ArrayList<>();
        list2.add(new YouTubeTipRecDTO(R.layout.item_youtube_tip, ""));
        list2.add(new YouTubeTipRecDTO(R.layout.item_youtube_tip, ""));
        list2.add(new YouTubeTipRecDTO(R.layout.item_youtube_tip, ""));
        list2.add(new YouTubeTipRecDTO(R.layout.item_youtube_tip, ""));
        list2.add(new YouTubeTipRecDTO(R.layout.item_youtube_tip, ""));


        YouTubeTipRecAdapter adapter2 = new YouTubeTipRecAdapter(context, list2);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
        );
        recyoutube.setLayoutManager(layoutManager2);
        recyoutube.setAdapter(adapter2);
        //=====================RecyclerCamTip========================================//

        //=====================pagerTheme===================================//
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




        sguse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(), SafeGuardInfoActivity.class);
                startActivity(intent);
            }
        });











        home_search = rootView.findViewById(R.id.home_search);
        home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, HomeSearchActivity.class);
                startActivity(intent);
                mainActivity = (MainActivity) getActivity();
                mainActivity.overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_left_exit);
            }
        });


        packgemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new Product_Package_Fragment(context));
            }
        });





        return rootView;
    }




    public void changeFragment(Fragment fragment){
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.shop_container1 , fragment).commit();
    }
}