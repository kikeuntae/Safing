package com.example.safing.home.DAO;

import android.util.Log;

import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.home.VO.SafeZoneRecVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SafeZoneRecDAO {
    private static final String TAG = "SafeZoneDAO";
    CommonAsk service ;
    InputStream in;
    Gson gson = new Gson();

    //sfzone 가입 리스트
    public ArrayList<SafeZoneRecVO> sfzone_list(){
        service = new CommonAsk("sfzone_rec.home");
        in = CommonMethod.excuteAsk(service);
        ArrayList<SafeZoneRecVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<SafeZoneRecVO> >(){}.getType());
    } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }

    //전체 캠핑장 list
    public ArrayList<SafeZoneRecVO> all_list(){
        service = new CommonAsk("all_list.home");
        in = CommonMethod.excuteAsk(service);
        ArrayList<SafeZoneRecVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<SafeZoneRecVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }

    //캠핑장 검색기능
    public ArrayList<SafeZoneRecVO> all_list (String search) {
        service = new CommonAsk("all_list.home");
        service.params.add(new AskParam("search", search));
        InputStream in = CommonMethod.excuteAsk(service);
        ArrayList<SafeZoneRecVO> list = new ArrayList<>();
        try {
            list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<SafeZoneRecVO>>(){}.getType());
        } catch (Exception e) {

        }

        return list;
    }

}
