package com.example.safing.shop.DAO;

import android.util.Log;

import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.VO.CartVO;
import com.example.safing.shop.VO.ProductVO;
import com.example.safing.shop.VO.Product_DetailVO;
import com.example.safing.shop.VO.Product_PackageVO;
import com.example.safing.shop.VO.Product_Package_DetailVO;
import com.example.safing.shop.VO.ReviewVO;
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

    //패키지 리스트
    public ArrayList<Product_PackageVO> package_list(){
        service = new CommonAsk("package_list.sh");

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
        service = new CommonAsk("product_list.sh");
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

    //패키지 상세정보
    public Product_Package_DetailVO package_detail(int package_num){
        service = new CommonAsk("package_detail.sh");
        service.params.add(new AskParam("package_num", package_num+""));
        in = CommonMethod.excuteAsk(service);
        Product_Package_DetailVO vo = new Product_Package_DetailVO();
        try{
            vo = gson.fromJson(new InputStreamReader(in), Product_Package_DetailVO.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return vo;
    }

    //상품 상세정보
    public Product_DetailVO product_detail(int product_num){
        service = new CommonAsk("product_detail.sh");
        service.params.add(new AskParam("product_num", product_num+""));
        in = CommonMethod.excuteAsk(service);
        Product_DetailVO vo = new Product_DetailVO();
        try{
            vo = gson.fromJson(new InputStreamReader(in), Product_DetailVO.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return vo;
    }

    //패키지 상품 상세정보 페이지
    public ArrayList<Product_DetailVO> product_details_page_pack(int num){
        service = new CommonAsk("product_details_page_pack.sh");
        service.params.add(new AskParam("num", num+""));
        in = CommonMethod.excuteAsk(service);
        ArrayList<Product_DetailVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<Product_DetailVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return list;
    }

    //장바구니 담기 상품
    public int insert_cart_pro(Product_DetailVO vo){
        service = new CommonAsk("insert_cart_pro.sh");
        CartVO cart = new CartVO();
        cart.setMember_id(CommonVal.loginInfo.getMember_id());
        cart.setProduct_num(vo.getProduct_num());
        cart.setProduct_price(vo.getProduct_price());
        cart.setOrder_count(vo.getOrder_count());
        service.params.add(new AskParam("vo", gson.toJson(cart)));

        in = CommonMethod.excuteAsk(service);
        int result = 0;
        try{
            result = gson.fromJson(new InputStreamReader(in), Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return result;
    }

    //장바구니 담기 패키지
    public int insert_cart_pack(ArrayList<Product_DetailVO> list, int packge_num){
        service = new CommonAsk("insert_cart_pack.sh");
        CartVO cartvo = new CartVO();

        for (Product_DetailVO vo: list) {
            cartvo.setMember_id(CommonVal.loginInfo.getMember_id());
            cartvo.setProduct_num(vo.getProduct_num());
            cartvo.setPackage_num(packge_num);
            cartvo.setProduct_price(vo.getProduct_price());
            cartvo.setOrder_count(vo.getOrder_count());
            service.params.add(new AskParam("vo", gson.toJson(cartvo)));
        }

        in = CommonMethod.excuteAsk(service);
        int result = 0;
        try{
            result = gson.fromJson(new InputStreamReader(in), Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return result;
    }

    //상품 리뷰리스트
    public ArrayList<ReviewVO> review_list_pro(int product_num){
        service = new CommonAsk("review_list_pro.sh");
        service.params.add(new AskParam("num", product_num+""));
        in = CommonMethod.excuteAsk(service);
        ArrayList<ReviewVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<ReviewVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }

    //패키지 리뷰리스트
    public ArrayList<ReviewVO> review_list_pack(int pakcage_num){
        service = new CommonAsk("review_list_pack.sh");
        service.params.add(new AskParam("num", pakcage_num+""));
        in = CommonMethod.excuteAsk(service);
        ArrayList<ReviewVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<ReviewVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return list;
    }

}
