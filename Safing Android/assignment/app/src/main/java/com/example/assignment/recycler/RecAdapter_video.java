package com.example.assignment.recycler;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.MainActivity;
import com.example.assignment.OnItemClick;
import com.example.assignment.R;
import com.example.assignment.VideoActivity;
import com.example.assignment.fragment.Fragment1;
import com.example.assignment.fragment.Fragment2;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class RecAdapter_video extends RecyclerView.Adapter<RecAdapter_video.ViewHolder> implements OnItemClick {


    Context context;
    LayoutInflater inflater;
    ArrayList<RecDTO> list;


    ProgressiveMediaSource.Factory mediaFactory;


    public RecAdapter_video(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public RecAdapter_video(Context context, ArrayList<RecDTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addDto(RecDTO dto) {
        list.add(dto);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View itemview =
                inflater.inflate(R.layout.vdieoitem, parent,
                        false);

        return new ViewHolder(itemview,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder vh= (ViewHolder)holder;

        ProgressiveMediaSource mediaSource= mediaFactory.createMediaSource(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        //위에서 만든 비디오 데이터 소스를 플레이어에게 로딩하도록....
        vh.player.prepare(mediaSource);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public void onClick(String value) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PlayerView exoPlayerView;
        SimpleExoPlayer player;


        public ViewHolder(@NonNull View itemView, OnItemClick listener) {
            super(itemView);

            exoPlayerView = itemView.findViewById(R.id.exoPlayerView_adapter);

            player= ExoPlayerFactory.newSimpleInstance(context, new DefaultTrackSelector());
            exoPlayerView.setPlayer(player);

        }


    }
}
