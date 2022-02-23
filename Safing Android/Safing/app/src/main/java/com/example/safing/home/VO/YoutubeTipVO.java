package com.example.safing.home.VO;

import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import java.io.Serializable;

public class YoutubeTipVO implements Serializable {
    int  id, youtubecnt, board_id;
    String thumbnails, youtubetitle, youtubecontent, play;

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYoutubecnt() {
        return youtubecnt;
    }

    public void setYoutubecnt(int youtubecnt) {
        this.youtubecnt = youtubecnt;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getYoutubetitle() {
        return youtubetitle;
    }

    public void setYoutubetitle(String youtubetitle) {
        this.youtubetitle = youtubetitle;
    }

    public String getYoutubecontent() {
        return youtubecontent;
    }

    public void setYoutubecontent(String youtubecontent) {
        this.youtubecontent = youtubecontent;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }
}
