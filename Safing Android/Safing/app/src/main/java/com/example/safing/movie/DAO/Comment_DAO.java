package com.example.safing.movie.DAO;

import android.util.Log;

import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.movie.DTO.Board_Movie_DTO;
import com.example.safing.movie.DTO.Movie_comment_DTO;
import com.example.safing.mypage.VO.MemberVO;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Comment_DAO {
    private static final String TAG = "video";
    CommonAsk service;
    Gson gson = new GsonBuilder().serializeNulls().create();
    InputStream in;

    public String memberImg(String member_id) { //프로필이미지 가져오기

        service = new CommonAsk("memberimg.me");
        //MemberVO member = new MemberVO();
        MemberVO vo = new MemberVO();
        //member.setMember_id(member_id);
        String str = gson.toJson(member_id);
        service.params.add(new AskParam("member_id", member_id));
        in = CommonMethod.excuteAsk(service);
        try {
            vo = gson.fromJson(new InputStreamReader(in), new TypeToken<MemberVO>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "gson error");
        }

        String path = "http://192.168.0.65:80/safing/resources/";
        String imgUrl = path + vo.getMember_filepath();
        return imgUrl;
    }

    public ArrayList<Movie_comment_DTO> list(int video_id) { //댓글리스트 가져오기
        {
            ArrayList<Movie_comment_DTO> list = new ArrayList<>();
            service = new CommonAsk("commentselect.co");
            String str = gson.toJson(video_id);
            service.params.add(new AskParam("video_id", str));
            in = CommonMethod.excuteAsk(service);
            try {

                list = gson.fromJson(new InputStreamReader(in), new TypeToken<ArrayList<Movie_comment_DTO>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "gson error");
            }
            return list;
        }
    }

    public void create(Movie_comment_DTO vo) {
        {
            service = new CommonAsk("commentinsert.co");
            String str = gson.toJson(vo);
            service.params.add(new AskParam("vo", str));
            CommonMethod.excuteAsk(service);
        }
    }

    public void update(Movie_comment_DTO vo) {
        {
            service = new CommonAsk("commentupdate.co");
            String str = gson.toJson(vo);
            service.params.add(new AskParam("vo", str));
            CommonMethod.excuteAsk(service);
        }
    }

    public void delete(Movie_comment_DTO vo) {
        {

            service = new CommonAsk("commentdelete.co");
            String str =gson.toJson(vo);
            service.params.add(new AskParam("vo", str));
            CommonMethod.excuteAsk(service);
        }
    }


    public void comment_cnt_re() {


    }
}
