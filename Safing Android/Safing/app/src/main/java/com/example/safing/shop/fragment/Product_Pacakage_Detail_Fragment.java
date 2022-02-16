package com.example.safing.shop.fragment;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.async.CommonVal;
import com.example.safing.async.OnItemClick_product_Detail_Listener;
import com.example.safing.async.OnItemClick_product_Package_Detail_Listener;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.Product_DetailVO;
import com.example.safing.shop.adapter.Product_Detail_Apdater;
import com.example.safing.shop.adapter.Product_Pakcage_Detail_Apdater;

import java.text.NumberFormat;
import java.util.ArrayList;


public class Product_Pacakage_Detail_Fragment extends Fragment {
    Context context;
    RecyclerView product_detail_rec;
    LinearLayoutManager manager;
    ImageView product_detail_img;
    Button product_detail_btn1, product_detail_btn2;
    TextView product_detail_tv1, product_detail_tv2, product_detail_tv3;
    ShopDAO dao = new ShopDAO();
    ArrayList<Product_DetailVO> list = new ArrayList<>();
    MainActivity mainActivity = new MainActivity();

    int packge_num = 0;


    public Product_Pacakage_Detail_Fragment(Context context, int packge_num){
        this.context = context;
        this.packge_num = packge_num;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_detail_product, container, false);

        product_detail_rec = rootView.findViewById(R.id.product_detail_rec);
        product_detail_btn1 = rootView.findViewById(R.id.product_detail_btn1);
        product_detail_btn2 = rootView.findViewById(R.id.product_detail_btn2);
        product_detail_img = rootView.findViewById(R.id.product_detail_img);
        product_detail_tv1 = rootView.findViewById(R.id.product_detail_tv1);
        product_detail_tv2 = rootView.findViewById(R.id.product_detail_tv2);
        product_detail_tv3 = rootView.findViewById(R.id.product_detail_tv3);

        mainActivity = (MainActivity) getActivity();

        list = dao.product_details_page_pack(packge_num);
        Glide.with(context).load(FILE_PATH + list.get(0).getFile_path_info()).into( product_detail_img);
        setRec(list);

        //============= 장바구니 버튼 =====
        product_detail_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonVal.loginInfo.getMember_id().isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("로그인 확인");
                    builder.setMessage("로그인이 필요합니다.");
                    builder.setIcon(R.drawable.question1);
                    builder.setPositiveButton("로그인하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //   mainActivity.changeFragment(new LoginFragment(context));
                        }
                    });
                    builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    int result = dao.insert_cart_pack(list, packge_num);
                    if(result > 0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("장바구니 담기");
                        builder.setMessage("장바구니에 담았습니다.\n확인하시겠습니까?");
                        builder.setIcon(R.drawable.question1);
                        builder.setPositiveButton("장바구니보기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mainActivity.changeFragment(new Product_Cart_Fragment(context));
                            }
                        });
                        builder.setPositiveButton("쇼핑하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "쇼핑하기", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(context, "장바구니 담기 실패", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //============= 구매하기 버튼 ========
        product_detail_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonVal.loginInfo.getMember_id().isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("로그인 확인");
                    builder.setMessage("로그인이 필요합니다.");
                    builder.setIcon(R.drawable.question1);
                    builder.setPositiveButton("로그인하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //  mainActivity.changeFragment(new LoginFragment(context));
                        }
                    });
                    builder.setPositiveButton("쇼핑하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "쇼핑하기", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    mainActivity.changeFragment(new Product_Purchase_Fragment(context));
                }
            }
        });


        return rootView;
    }

    public void setRec(ArrayList<Product_DetailVO> list){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        product_detail_rec.setLayoutManager(manager);
        Product_Pakcage_Detail_Apdater adapter_rec = new Product_Pakcage_Detail_Apdater(context, list, Product_Pacakage_Detail_Fragment.this);
        product_detail_rec.setAdapter(adapter_rec);

        adapter_rec.setOnItemClickListener(new OnItemClick_product_Package_Detail_Listener() {
            @Override
            public void onItemClick_detail(Product_Pakcage_Detail_Apdater.ViewHolder holderm, View view, int position) {
                mainActivity.changeFragment(new Product_Fragment(context, list.get(position).getProduct_num()));
            }
        });

    }

    public void changePrice(int price, int cnt, int position){
        list.get(position).setProduct_price(price);
        list.get(position).setOrder_count(cnt);

        int courier = 0;
        int priceSum = 0;

        for (Product_DetailVO vo: list) {
            priceSum += (vo.getProduct_price()* vo.getOrder_count());
        }

        if(priceSum < 100000){
            courier = 5000;
        }

        int priceSum2 = price + courier;

        product_detail_tv1.setText(NumberFormat.getInstance().format(priceSum)+"원");
        product_detail_tv2.setText(NumberFormat.getInstance().format(courier)+"원");
        product_detail_tv3.setText(NumberFormat.getInstance().format(priceSum2)+"원");
    }
}