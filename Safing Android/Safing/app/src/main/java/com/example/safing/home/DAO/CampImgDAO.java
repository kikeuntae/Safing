package com.example.safing.home.DAO;

import android.util.Log;

import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.home.VO.CampImgVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CampImgDAO {

    private static final String TAG = "CampImgDAO";
    CommonAsk service ;
    InputStream in;
    Gson gson = new Gson();

    //tip 리스트
    public ArrayList<CampImgVO> campimg_list(String contentid){
        service = new CommonAsk("campimg_list.home");
        service.addParams("contentid" , contentid);
        in = CommonMethod.excuteAsk(service);
        ArrayList<CampImgVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<CampImgVO>>(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }
        return list;
    }
}
