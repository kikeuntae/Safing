package com.example.safing.shop.DAO;

import android.util.Log;

import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.shop.DTO.Product_Cart_RecDTO;
import com.example.safing.shop.DTO.Product_PurchaseHistory_RecDTO;
import com.example.safing.shop.VO.ProductVO;
import com.example.safing.shop.VO.Product_PackageVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {
    private static final String TAG = "shop_dao";
    CommonAsk service ;
    InputStream in;
    Gson gson = new Gson();

    //키지 리스트
    public ArrayList<Product_PackageVO> package_list(){
        service = new CommonAsk("package_rec.sh");
        in = CommonMethod.excuteAsk(service);
        ArrayList<Product_PackageVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<Product_PackageVO> >(){}.getType());
    } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }

    //상품 리스트
    public ArrayList<ProductVO> product_list(String query){
        service = new CommonAsk("product_rec.sh");
        service.params.add(new AskParam("search", query));
        in = CommonMethod.excuteAsk(service);
        ArrayList<ProductVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<ProductVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }

    //장바구니 리스트
    public ArrayList<Product_Cart_RecDTO> cart_list(){
        service = new CommonAsk("product_cart.sh");
        in = CommonMethod.excuteAsk(service);
        ArrayList<Product_Cart_RecDTO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<Product_Cart_RecDTO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }

    //구매내역 리스트
    public ArrayList<Product_PurchaseHistory_RecDTO> purchaseHistory_list(){
        service = new CommonAsk("purchaseHistory_list.sh");
        in = CommonMethod.excuteAsk(service);
        ArrayList<Product_PurchaseHistory_RecDTO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<Product_PurchaseHistory_RecDTO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }



}
