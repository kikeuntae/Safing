package com.example.safing.shop.VO;

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewVO implements Serializable {
    private String member_id	 	;
    private String member_filepath	;
    private String board_content	;
    private String board_writedate	;
    private int	   review_num		;
    private int	   rating		    ;
    private int	   board_like_cnt   ;
    private ArrayList<String> imagelist;

    public int getReview_num() {
        return review_num;
    }

    public void setReview_num(int review_num) {
        this.review_num = review_num;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_filepath() {
        return member_filepath;
    }

    public void setMember_filepath(String member_filepath) {
        this.member_filepath = member_filepath;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getBoard_writedate() {
        return board_writedate;
    }

    public void setBoard_writedate(String board_writedate) {
        this.board_writedate = board_writedate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getBoard_like_cnt() {
        return board_like_cnt;
    }

    public void setBoard_like_cnt(int board_like_cnt) {
        this.board_like_cnt = board_like_cnt;
    }

    public ArrayList<String> getImagelist() {
        return imagelist;
    }

    public void setImagelist(ArrayList<String> imagelist) {
        this.imagelist = imagelist;
    }
}
