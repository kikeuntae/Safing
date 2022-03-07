package com.example.safing.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.home.VO.YoutubeTipVO;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class TipActivity extends AppCompatActivity {
    YouTubePlayerView youtube_player_view;
    TextView youtube_detail_title, youtube_detail_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        Intent intent = getIntent();
        YoutubeTipVO vo = (YoutubeTipVO) intent.getSerializableExtra("vo");


        youtube_player_view = findViewById(R.id.youtube_player_view);
        youtube_detail_title = findViewById(R.id.youtube_detail_title);
        youtube_detail_content = findViewById(R.id.youtube_detail_content);

        //============================youtube_detail===============================//


        youtube_detail_content.setText(vo.getYoutubecontent());
        youtube_detail_title.setText(vo.getYoutubetitle());

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = vo.getPlay();
                youTubePlayer.loadVideo(videoId, 0);

            }
        });

        //============================youtube_detail===============================//

    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(TipActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        }


    }

