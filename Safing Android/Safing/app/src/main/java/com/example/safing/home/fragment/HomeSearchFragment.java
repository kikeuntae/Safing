package com.example.safing.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.home.DAO.SafeZoneRecDAO;
import com.example.safing.home.VO.SafeZoneRecVO;
import com.example.safing.home.activity.HomeSearchActivity;
import com.example.safing.home.activity.SafeGuardInfoActivity;
import com.example.safing.home.adapter.HomeSearchAdapter;
import com.example.safing.shop.fragment.ShopFragment;

import java.util.ArrayList;


public class HomeSearchFragment extends Fragment {

    RecyclerView search_rec1;
    Context context;
    SwipeRefreshLayout swipe;
    SearchView searchView;
    ImageView backbtn;
    MainActivity mainActivity = new MainActivity();

    public HomeSearchFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home_search, container, false);

        search_rec1 = rootView.findViewById(R.id.search_rec1);
        swipe = rootView.findViewById(R.id.spot_swipe);
        searchView = rootView.findViewById(R.id.search_searchv);
        backbtn = rootView.findViewById(R.id.search_backbtn);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //검색 버튼( 엔터를 눌렀을 때 엔터키를 누른 순간까지 입력된 글자들을 가져옴
                dataSelect(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                //글자가 바뀔때마다 그 순간에 입력된 글자까지 전부 가져옴.(이벤트 발생 빈번)

                    dataSelect(query);

                return true;
            }

        });

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //조회를 다시하는 처리. 메소드
                swipe.setRefreshing(false);
                dataSelect(null);
            }

        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });



        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
               /* Intent intent = new Intent(getActivity(), HomeFragment.class);
                startActivity(intent);
*/
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment(context)).addToBackStack(null).commit();

                mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new HomeFragment(context));
            }

        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);

        return rootView;
    }




    public void dataSelect (String query) {
        ArrayList<SafeZoneRecVO> list = new ArrayList<>();
        if(query.trim().length() > 0 ){
            SafeZoneRecDAO dao = new SafeZoneRecDAO();

            if(query != null) {
                list = dao.all_list(query);
            }else {
                list = dao.all_list();
            }
        }


        LinearLayoutManager manager = new LinearLayoutManager(
                getContext() , RecyclerView.VERTICAL, false
        );

        HomeSearchAdapter adapter = new HomeSearchAdapter(context, list);
        search_rec1.setLayoutManager(manager);
        search_rec1.setAdapter(adapter);
    }



}