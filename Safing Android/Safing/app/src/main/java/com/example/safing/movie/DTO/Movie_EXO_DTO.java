package com.example.safing.movie.DTO;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class Movie_EXO_DTO {
    private PlayerView exoPlayerView = null;
    private SimpleExoPlayer player = null;


    public SimpleExoPlayer getPlayer() { return player;    }

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
}
