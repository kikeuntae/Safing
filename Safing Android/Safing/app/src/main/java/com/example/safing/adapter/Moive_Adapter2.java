package com.example.safing.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.R;
import com.example.safing.VO.Board_FileVO;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class Moive_Adapter2 extends RecyclerView.Adapter<Moive_Adapter2.VH> {
    boolean speaker_change = true;
    boolean heart_change = true;
    Context context;
    LayoutInflater inflater;
    DataSource.Factory factory;
    ProgressiveMediaSource.Factory mediaFactory;
    ArrayList<Board_FileVO> videoItems;

    public Moive_Adapter2(Context context, ArrayList<Board_FileVO> videoItems) {
        this.context = context;
        this.videoItems = videoItems;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    private MediaSource buildMediaSource(Uri uri) {

        String userAgent = Util.getUserAgent(context, "blackJin");

        if (uri.getLastPathSegment().contains("mp3") || uri.getLastPathSegment().contains("mp4")) {

            return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                    .createMediaSource(uri);

        } else if (uri.getLastPathSegment().contains("m3u8")) {

            //com.google.android.exoplayer:exoplayer-hls 확장 라이브러리를 빌드 해야 합니다.
            return new HlsMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                    .createMediaSource(uri);

        } else {

            return new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(context, userAgent))
                    .createMediaSource(uri);
        }

    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_exoplayer_movie, parent, false);
        VH holder = new VH(itemView);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.bind(holder, position);


    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public void setVideo() {
        for (int i = 0; i < videoItems.size(); i++) {
            if (videoItems.get(i).getPlayer() != null) {
                videoItems.get(i).getPlayer().setPlayWhenReady(false);
                videoItems.get(i).getExoPlayerView().onPause();
            }
        }
    }

    public void setVideo(int nowstate, int position) {
        if (videoItems.get(position).getPlayer() != null) {
            videoItems.get(nowstate).getExoPlayerView().onPause();
            videoItems.get(nowstate).getPlayer().setPlayWhenReady(false);

        }
    }

    public void setVideo(int position) {
        for (int i = 0; i < videoItems.size(); i++) {
            if (i != position && videoItems.get(i).getPlayer() != null) {
                videoItems.get(i).getPlayer().setPlayWhenReady(false);
                videoItems.get(i).getExoPlayerView().onPause();
                speaker_change = true;
            } else if (i == position && videoItems.get(i).getPlayer() != null) {
                videoItems.get(position).getExoPlayerView().onResume();
                videoItems.get(position).getPlayer().setPlayWhenReady(true);
                speaker_change = true;
            }
        }

    }


    //inner class..
    class VH extends RecyclerView.ViewHolder {
        ImageView movie_pager_imgbtn1, movie_pager_imgbtn2, movie_pager_imgbtn3, movie_pager_imgbtn4;
        TextView movie_pager_tv1, movie_pager_tv2, movie_pager_tv3;

        private PlayerView exoPlayerView;
        private SimpleExoPlayer player;

        public VH(@NonNull View itemView) {
            super(itemView);

            exoPlayerView = itemView.findViewById(R.id.exoplayerview);

            movie_pager_imgbtn1 = itemView.findViewById(R.id.movie_pager_imgbtn1);
            movie_pager_imgbtn2 = itemView.findViewById(R.id.movie_pager_imgbtn2);
            movie_pager_imgbtn3 = itemView.findViewById(R.id.movie_pager_imgbtn3);
            movie_pager_imgbtn4 = itemView.findViewById(R.id.movie_pager_imgbtn4);

            movie_pager_tv1 = itemView.findViewById(R.id.movie_pager_tv1);
            movie_pager_tv2 = itemView.findViewById(R.id.movie_pager_tv2);
            movie_pager_tv3 = itemView.findViewById(R.id.movie_pager_tv3);


            movie_pager_imgbtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*                    if(speaker_change== true) {
                        Toast.makeText(context, "1번 아이콘 음량 0만들기", Toast.LENGTH_SHORT).show();
                        movie_pager_imgbtn1.setImageResource(R.drawable.mute);
                        videoItem.getPlayer().setVolume(0);
                        speaker_change= false;
                    }else {
                        Toast.makeText(context, "1번 변경 음량 100 만들기", Toast.LENGTH_SHORT).show();
                        movie_pager_imgbtn1.setImageResource(R.drawable.speaker);
                        player.setVolume(100);
                        speaker_change= true;
                    }*/
                }
            });

            movie_pager_imgbtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            movie_pager_imgbtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (heart_change == true) {
                        Toast.makeText(context, "좋아요", Toast.LENGTH_SHORT).show();
                        movie_pager_imgbtn3.setImageResource(R.drawable.heart2);
                        heart_change = false;
                    } else {
                        Toast.makeText(context, "좋아요 취소", Toast.LENGTH_SHORT).show();
                        movie_pager_imgbtn3.setImageResource(R.drawable.heart1);
                        heart_change = true;
                    }
                }
            });

            movie_pager_imgbtn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        } // public VH


        public void bind(@NonNull VH holder, int position) {
            Board_FileVO videoItem = videoItems.get(position);
            if (videoItem.getPlayer() == null) {
                videoItem.setPlayer(ExoPlayerFactory.newSimpleInstance(context));
                videoItem.setExoPlayerView(itemView.findViewById(R.id.exoplayerview));


                holder.setDto(videoItem);
                String videoUrl = videoItem.getBoard_file_path();
                MediaSource mediaSource = buildMediaSource(Uri.parse(videoUrl));
                videoItem.getPlayer().prepare(mediaSource, true, false);
                videoItem.getPlayer().setPlayWhenReady(true);

                holder.movie_pager_imgbtn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (speaker_change == true) {
                            Toast.makeText(context, "1번 아이콘 음량 0만들기", Toast.LENGTH_SHORT).show();
                            movie_pager_imgbtn1.setImageResource(R.drawable.mute);
                            videoItem.getPlayer().setVolume(0);
                            speaker_change = false;
                        } else {
                            Toast.makeText(context, "1번 변경 음량 100 만들기", Toast.LENGTH_SHORT).show();
                            movie_pager_imgbtn1.setImageResource(R.drawable.speaker);
                            videoItem.getPlayer().setVolume(100);
                            speaker_change = true;
                        }
                    }
                });

            }
        }


        public void setDto(Board_FileVO videoitem) {
            movie_pager_tv1.setText(videoitem.getBoard_file_id() + "");
            movie_pager_tv2.setText(videoitem.getMember_id());
            movie_pager_tv3.setText(videoitem.getBoard_file_name());
        }

    } // class VH


}