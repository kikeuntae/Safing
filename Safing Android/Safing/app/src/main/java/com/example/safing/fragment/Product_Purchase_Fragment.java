package com.example.safing.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.google.android.material.tabs.TabLayout;


public class Product_Purchase_Fragment extends Fragment {
    Context context;
    TabLayout product_purchase_tab1;
    MainActivity mainActivity = new MainActivity();


    public Product_Purchase_Fragment(Context context){
        this.context = context;
    }

    public Product_Purchase_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product_purchase, container, false);

        product_purchase_tab1 = rootView.findViewById(R.id.product_purchase_tab1);

        mainActivity = (MainActivity) getActivity();

        product_purchase_tab1.addTab(product_purchase_tab1.newTab().setText("기본 주소"));
        product_purchase_tab1.addTab(product_purchase_tab1.newTab().setText("주소 선택"));

        changeFragment(new Address_Defualt_Fragment(context));

        product_purchase_tab1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    Toast.makeText(context, "기본 주소", Toast.LENGTH_SHORT).show();
                    changeFragment(new Address_Defualt_Fragment(context));
                } else {
                    Toast.makeText(context, "다른주소", Toast.LENGTH_SHORT).show();
                    changeFragment(new Address_Repogitory_Fragment(context));
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
    public void changeFragment(Fragment fragment){
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.shop_container2 , fragment).commit();
    }
}