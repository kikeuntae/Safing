package com.example.safing.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.async.OnItemClick_Theme_Listener;
import com.example.safing.home.DAO.HomeDAO;
import com.example.safing.home.DAO.SafeZoneRecDAO;
import com.example.safing.home.DAO.YoutubeTipDAO;
import com.example.safing.home.DTO.ThemeRecDTO;
import com.example.safing.home.VO.SafeZoneRecVO;
import com.example.safing.home.VO.YoutubeTipVO;
import com.example.safing.home.activity.SafeGuardInfoActivity;
import com.example.safing.home.adapter.SafeZoneRecAdapter;
import com.example.safing.home.adapter.Theme_Pager_Adapter;
import com.example.safing.home.adapter.YouTubeTipRecAdapter;
import com.example.safing.shop.fragment.Product_Package_Fragment;
import com.example.safing.shop.fragment.ShopFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    RecyclerView recysf, recyoutube ;
    Context context;
    RecyclerView rectheme;
    ImageView home_search, sguse, thumbnails ;
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

        mainActivity = (MainActivity) getActivity();

        recyoutube = rootView.findViewById(R.id.youtubetip);
        sguse = rootView.findViewById(R.id.sguse);
        youtubetip1 = rootView.findViewById(R.id.youtubetip1);
        home_search = rootView.findViewById(R.id.home_search);
        packgemore = rootView.findViewById(R.id.packgemore);
        rectheme = rootView.findViewById(R.id.rectheme);
        thumbnails = rootView.findViewById(R.id.thumbnails);

        //====================Recysfzone=====================================//
        recysf.findViewById(R.id.recysfzone1);
        SafeZoneRecDAO dao1 = new SafeZoneRecDAO();
        ArrayList<SafeZoneRecVO> list = dao1.sfzone_list();

        SafeZoneRecAdapter adapter1 = new SafeZoneRecAdapter(context, list);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
        );
        recysf.setLayoutManager(layoutManager1);
        recysf.setAdapter(adapter1);
        //====================Recysfzone=====================================//

        //=====================RecyclerCamTip========================================//
        recyoutube.findViewById(R.id.youtubetip);
        YoutubeTipDAO dao = new YoutubeTipDAO();
        ArrayList<YoutubeTipVO> list2 = dao.tip_list();

        YouTubeTipRecAdapter adapter2 = new YouTubeTipRecAdapter(context, list2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
        );
        recyoutube.setLayoutManager(layoutManager2);
        recyoutube.setAdapter(adapter2);


        //=====================RecyclerCamTip========================================//

        //=====================pagerTheme===================================//

        HomeDAO dao2 = new HomeDAO();
        ArrayList<ThemeRecDTO> list1 = dao2.Theme_Pager();


        Theme_Pager_Adapter adapter = new Theme_Pager_Adapter(context, list1);
        rectheme.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClick_Theme_Listener() {
            @Override
            public void onItemClick_package(Theme_Pager_Adapter.ViewHolder holderm, View view, int position) {
                mainActivity.changeFragment(new Product_Package_Fragment(context, list1.get(position).getPackage_num()));
            }
        });
        //=====================pagerTheme===================================//




        sguse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(), SafeGuardInfoActivity.class);
                startActivity(intent);
            }
        });



        home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent  = new Intent(context, HomeSearchFragment.class);
                startActivity(intent);*/
                mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new HomeSearchFragment(context));
                mainActivity.overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_left_exit);
            }
        });


        packgemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new ShopFragment(context));
            }
        });





        return rootView;
    }

    public void changeFragment(Fragment fragment){
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.shop_container1 , fragment).addToBackStack(null).commit();
    }


}