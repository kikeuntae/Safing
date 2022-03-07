package com.example.safing.home.DAO;

import android.util.Log;

import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.home.DTO.ThemeRecDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HomeDAO {
    private static final String TAG = "home_dao";
    CommonAsk service ;
    InputStream in;
    Gson gson = new Gson();


    public ArrayList<ThemeRecDTO> Theme_Pager(){
        service = new CommonAsk("Theme_Pager.home");
        in = CommonMethod.excuteAsk(service);
        ArrayList<ThemeRecDTO> list = new ArrayList<>();
        try{
            list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<ThemeRecDTO>>(){}.getType());
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        return list;
    }
}