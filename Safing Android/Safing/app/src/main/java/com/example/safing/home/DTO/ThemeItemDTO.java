package com.example.safing.home.DTO;

import java.io.Serializable;

public class ThemeItemDTO implements Serializable {
    private String theme_content, theme_addr, theme_tag1, theme_tag2, theme_tag3, theme_detail;
    private int theme_rec_pick, theme_campimg, theme_packimg1, theme_packimg2;

    public ThemeItemDTO(String theme_content, String theme_addr, String theme_tag1, String theme_tag2, String theme_tag3, String theme_detail, int theme_rec_pick, int theme_campimg, int theme_packimg1, int theme_packimg2) {
        this.theme_content = theme_content;
        this.theme_addr = theme_addr;
        this.theme_tag1 = theme_tag1;
        this.theme_tag2 = theme_tag2;
        this.theme_tag3 = theme_tag3;
        this.theme_detail = theme_detail;
        this.theme_rec_pick = theme_rec_pick;
        this.theme_campimg = theme_campimg;
        this.theme_packimg1 = theme_packimg1;
        this.theme_packimg2 = theme_packimg2;



    }


    public String getTheme_content() {
        return theme_content;
    }

    public void setTheme_content(String theme_content) {
        this.theme_content = theme_content;
    }

    public String getTheme_addr() {
        return theme_addr;
    }

    public void setTheme_addr(String theme_addr) {
        this.theme_addr = theme_addr;
    }

    public String getTheme_tag1() {
        return theme_tag1;
    }

    public void setTheme_tag1(String theme_tag1) {
        this.theme_tag1 = theme_tag1;
    }

    public String getTheme_tag2() {
        return theme_tag2;
    }

    public void setTheme_tag2(String theme_tag2) {
        this.theme_tag2 = theme_tag2;
    }

    public String getTheme_tag3() {
        return theme_tag3;
    }

    public void setTheme_tag3(String theme_tag3) {
        this.theme_tag3 = theme_tag3;
    }

    public String getTheme_detail() {
        return theme_detail;
    }

    public void setTheme_detail(String theme_detail) {
        this.theme_detail = theme_detail;
    }

    public int getTheme_rec_pick() {
        return theme_rec_pick;
    }

    public void setTheme_rec_pick(int theme_rec_pick) {
        this.theme_rec_pick = theme_rec_pick;
    }

    public int getTheme_campimg() {
        return theme_campimg;
    }

    public void setTheme_campimg(int theme_campimg) {
        this.theme_campimg = theme_campimg;
    }

    public int getTheme_packimg1() {
        return theme_packimg1;
    }

    public void setTheme_packimg1(int theme_packimg1) {
        this.theme_packimg1 = theme_packimg1;
    }

    public int getTheme_packimg2() {
        return theme_packimg2;
    }

    public void setTheme_packimg2(int theme_packimg2) {
        this.theme_packimg2 = theme_packimg2;
    }
}
