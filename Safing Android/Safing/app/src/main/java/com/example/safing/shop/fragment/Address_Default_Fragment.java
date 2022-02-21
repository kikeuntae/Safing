package com.example.safing.shop.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safing.R;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.AddressVO;


public class Address_Default_Fragment extends Fragment {


    Context context;
    EditText address_defualt_tv1, address_defualt_tv2, address_defualt_tv3, address_defualt_tv4, address_defualt_tv5, address_defualt_tv6, address_defualt_tv7;
    Button address_defualt_btn1;
    CheckBox address_defualt_box1, address_defualt_box2;

    boolean checked = false;
    boolean btn1_checked = false;
    boolean btn2_checked = false;

    AddressVO vo = new AddressVO();
    AddressVO newVo = new AddressVO();
    ShopDAO dao = new ShopDAO();


    public Address_Default_Fragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_address_default, container, false);

        address_defualt_tv1 = rootView.findViewById(R.id.address_defualt_tv1);
        address_defualt_tv2 = rootView.findViewById(R.id.address_defualt_tv2);
        address_defualt_tv3 = rootView.findViewById(R.id.address_defualt_tv3);
        address_defualt_tv4 = rootView.findViewById(R.id.address_defualt_tv4);
        address_defualt_tv5 = rootView.findViewById(R.id.address_defualt_tv5);
        address_defualt_tv6 = rootView.findViewById(R.id.address_defualt_tv6);
        address_defualt_tv7 = rootView.findViewById(R.id.address_defualt_tv7);
        address_defualt_btn1 = rootView.findViewById(R.id.address_defualt_btn1);
        address_defualt_box1 = rootView.findViewById(R.id.address_defualt_box1);
        address_defualt_box2 = rootView.findViewById(R.id.address_defualt_box2);

        //=============== 주소찾기 ===============

        address_defualt_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //=============== layout ===============

        vo = dao.default_addrss(CommonVal.loginInfo.getMember_id());

        if(!("null").equals(vo.getReceiver_name())){
            address_defualt_tv1.setText(vo.getReceiver_name());

            String[] phone = vo.getReceiver_phone().split("-");

            address_defualt_tv2.setText(phone[0]);
            address_defualt_tv3.setText(phone[1]);
            address_defualt_tv4.setText(phone[2]);
            address_defualt_tv5.setText(vo.getAddr_num()+"");
            address_defualt_tv6.setText(vo.getAddr_basic());
            address_defualt_tv7.setText(vo.getAddr_detail());

            if(("y").equals(vo.getAddr_setting())){
                address_defualt_box1.setChecked(true);
            } else {
                address_defualt_box1.setChecked(false);
            }
        }

        address_defualt_box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!checked){
                    if(!("y").equals(vo.getAddr_setting())){

                    } else if(!(vo.getAddr_basic()).equals(address_defualt_tv6.getText()+"")){
                    }
                    checked = true;
                } else {
                    address_defualt_box1.setChecked(false);
                }
            }
        });

        address_defualt_box2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checked){
                    if(!(vo.getAddr_basic()).equals(address_defualt_tv6.getText()+"")){
                    }
                    checked = false;
                } else {
                    address_defualt_box2.setChecked(false);
                }
            }
        });

        return rootView;
    }

    public AddressVO setAddress(){
        newVo.setReceiver_name(address_defualt_tv1.getText()+"");
        newVo.setReceiver_phone(address_defualt_tv2.getText()+"-"+address_defualt_tv3.getText()+"-"+address_defualt_tv4.getText());
        newVo.setAddr_num(Integer.parseInt(address_defualt_tv5.getText()+""));
        newVo.setAddr_basic(address_defualt_tv6.getText()+"");
        newVo.setAddr_detail(address_defualt_tv7.getText()+"");

        return newVo;
    }
}