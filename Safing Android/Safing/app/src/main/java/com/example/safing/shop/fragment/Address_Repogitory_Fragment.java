package com.example.safing.shop.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safing.R;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.AddressVO;
import com.example.safing.shop.adapter.Address_Repository_Rec_Adapter;

import java.util.ArrayList;

public class Address_Repogitory_Fragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    RecyclerView Address_Repogitory_rec;

    ArrayList<AddressVO> list = new ArrayList<>();
    ShopDAO dao = new ShopDAO();

    public Address_Repogitory_Fragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (ViewGroup)inflater.inflate(R.layout.fragment_address_repogitory, container, false);

        Address_Repogitory_rec = rootView.findViewById(R.id.Address_Repogitory_rec);

        setRec();

        return rootView;
    }
    public void setRec(){
        list = dao.addrss_list(CommonVal.loginInfo.getMember_id());

        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        Address_Repogitory_rec.setLayoutManager(manager);
        Address_Repository_Rec_Adapter adapter_rec1 = new Address_Repository_Rec_Adapter(context, list);
        Address_Repogitory_rec.setAdapter(adapter_rec1);
    }
}