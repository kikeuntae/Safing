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

public class Movie_DAO {
    private static final String TAG = "video";
    CommonAsk service;
    Gson gson = new GsonBuilder().serializeNulls().create();
    InputStream in;

    public List<Board_Movie_DTO> list() { //db 동영상 가져오기
        {
            service = new CommonAsk("movielist.bo");
            in = CommonMethod.excuteAsk(service);
            List<Board_Movie_DTO> list = new ArrayList<>();
            try {
                list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<Board_Movie_DTO>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "gson error");
            }
            return list;
        }
    }

    public void create(Board_Movie_DTO vo) {
        {
            service = new CommonAsk("movieinsert.bo");
            String str = gson.toJson(vo);
            service.params.add(new AskParam("vo", str));
            CommonMethod.excuteAsk(service);
        }
    }

    public void update(Board_Movie_DTO vo) {
        {
            service = new CommonAsk("movieupdate.bo");
            String str = gson.toJson(vo);
            service.params.add(new AskParam("vo", str));
            CommonMethod.excuteAsk(service);
        }
    }

    public void delete(Board_Movie_DTO vo) {
        {

            service = new CommonAsk("moviedelete.bo");
            String str =gson.toJson(vo);
            service.params.add(new AskParam("vo", str));
            CommonMethod.excuteAsk(service);
        }
    }

    public void like(Board_Movie_DTO vo){ //좋아요 처리
        {
            String str =gson.toJson(vo);
            service = new CommonAsk("like.bo");
            service.params.add(new AskParam("vo", str));
            CommonMethod.excuteAsk(service);
        }

    }

    public int movie_comment_cnt(int board_id) { //CRUD 이후 동영상 댓글 숫자 처리
        {
            service = new CommonAsk("movie_comment_cnt.bo");
            service.params.add(new AskParam("id", board_id+""));
            in = CommonMethod.excuteAsk(service);
            String comment_cnt = null;
            try {
                comment_cnt = gson.fromJson(new InputStreamReader(in), new TypeToken<String>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "gson error");
            }
            int cnt = Integer.parseInt(comment_cnt);
            return cnt;
        }
    }


    public List<Board_Movie_DTO> list_new() { //db 동영상 가져오기
        {
            service = new CommonAsk("movielist_new.bo");
            in = CommonMethod.excuteAsk(service);
            List<Board_Movie_DTO> list = new ArrayList<>();
            try {
                list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<Board_Movie_DTO>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "gson error");
            }
            return list;
        }
    }

    public List<Board_Movie_DTO> list_mypage(MemberVO vo) { // 마이페이지 동영상 리스트
        {
            List<Board_Movie_DTO> list = new ArrayList<>();
            String str = gson.toJson(vo);
            service = new CommonAsk("movielist_mypage.bo");
            service.params.add(new AskParam("vo",str));
            in = CommonMethod.excuteAsk(service);
            try {
                list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<Board_Movie_DTO>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "gson error");
            }
            return list;
        }
    }
}
