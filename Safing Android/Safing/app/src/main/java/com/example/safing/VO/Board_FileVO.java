package com.example.safing.VO;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class Board_FileVO {
    private int		board_file_id	  ;
    private int		board_id	      ;
    private String	member_id	      ;
    private String	board_file_name	  ;
    private String	board_file_path	  ;
    private PlayerView exoPlayerView;
    private SimpleExoPlayer player;

    public SimpleExoPlayer getPlayer() {

        return player;
    }

    public Board_FileVO(int board_file_id, String member_id, String	board_file_name, String board_file_path) {
        this.board_file_id = board_file_id;
        this.member_id = member_id;
        this.board_file_name = board_file_name;
        this.board_file_path = board_file_path;
    }

    public PlayerView getExoPlayerView() {
        return exoPlayerView;
    }
    public void setPlayer(SimpleExoPlayer player) {
        this.player = player;
    }

    public void setExoPlayerView(PlayerView exoPlayerView) {
        this.exoPlayerView = exoPlayerView;
        this.exoPlayerView.setPlayer(this.player);
    }


    public int getBoard_file_id() {
        return board_file_id;
    }
    public void setBoard_file_id(int board_file_id) {
        this.board_file_id = board_file_id;
    }
    public int getBoard_id() {
        return board_id;
    }
    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }
    public String getMember_id() {
        return member_id;
    }
    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public String getBoard_file_name() {
        return board_file_name;
    }
    public void setBoard_file_name(String board_file_name) {
        this.board_file_name = board_file_name;
    }
    public String getBoard_file_path() {
        return board_file_path;
    }
    public void setBoard_file_path(String board_file_path) {
        this.board_file_path = board_file_path;
    }


}
