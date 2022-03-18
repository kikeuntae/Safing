package com.example.safing.home.DAO;

import android.util.Log;

import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.home.VO.YoutubeTipVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class YoutubeTipDAO {
    private static final String TAG = "YoutubeTipDAO";
    CommonAsk service ;
    InputStream in;
    Gson gson = new Gson();

    //tip 리스트
    public ArrayList<YoutubeTipVO> tip_list(){
        service = new CommonAsk("tip_rec.home");
        in = CommonMethod.excuteAsk(service);
        ArrayList<YoutubeTipVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<YoutubeTipVO> >(){}.getType());
    } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }



    // 조회수 증가
    public void tip_readcnt(int id){
        service = new CommonAsk("tip_readcnt.home");
        service.params.add(new AskParam("id", id+""));
        in = CommonMethod.excuteAsk(service);
        ArrayList<YoutubeTipVO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken< List<YoutubeTipVO> >(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

    }

}
