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
<<<<<<< HEAD
    boolean speaker_change = false;
=======
<<<<<<< HEAD
    boolean speaker_change = true;
>>>>>>> d785786d5ea155274c65bfa3305b907797f3797b
    boolean heart_change = true;
=======
<<<<<<< HEAD
    boolean speaker_change= true;
    boolean heart_change= true;
=======
    boolean speaker_change = true;
    boolean heart_change = true;
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
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

<<<<<<< HEAD
        holder.bind(holder, position);
=======
<<<<<<< HEAD
        holder.bind(holder , position);
=======
        holder.bind(holder, position);
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)


    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

<<<<<<< HEAD
    public void setVideo() {
        for (int i = 0; i < videoItems.size(); i++) {
            if (videoItems.get(i).getPlayer() != null) {
=======
<<<<<<< HEAD
    public void setVideo(){
        for(int i = 0 ; i<videoItems.size(); i++){
            if(videoItems.get(i).getPlayer() != null){
=======
    public void setVideo() {
        for (int i = 0; i < videoItems.size(); i++) {
            if (videoItems.get(i).getPlayer() != null) {
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
                videoItems.get(i).getPlayer().setPlayWhenReady(false);
                videoItems.get(i).getExoPlayerView().onPause();
            }
        }
    }

<<<<<<< HEAD
    public void setVideo(int nowstate, int position) {
        if (videoItems.get(position).getPlayer() != null) {
=======
<<<<<<< HEAD
    public void setVideo(int nowstate , int position) {
        if(videoItems.get(position).getPlayer() != null){
=======
    public void setVideo(int nowstate, int position) {
        if (videoItems.get(position).getPlayer() != null) {
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
            videoItems.get(nowstate).getExoPlayerView().onPause();
            videoItems.get(nowstate).getPlayer().setPlayWhenReady(false);

        }
    }

    public void setVideo(int position) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
        for(int i = 0 ; i<videoItems.size(); i++){
            if(i != position && videoItems.get(i).getPlayer() != null ){
                videoItems.get(i).getPlayer().setPlayWhenReady(false);
                videoItems.get(i).getExoPlayerView().onPause();
            }else if(i == position && videoItems.get(i).getPlayer() != null){
                videoItems.get(position).getExoPlayerView().onResume();
                videoItems.get(position).getPlayer().setPlayWhenReady(true);
=======
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
        for (int i = 0; i < videoItems.size(); i++) {
            if (i != position && videoItems.get(i).getPlayer() != null) {
                videoItems.get(i).getPlayer().setPlayWhenReady(false);
                videoItems.get(i).getExoPlayerView().onPause();

            } else if (i == position && videoItems.get(i).getPlayer() != null) {
                videoItems.get(position).getExoPlayerView().onResume();
                videoItems.get(position).getPlayer().setPlayWhenReady(true);
<<<<<<< HEAD

=======
                speaker_change = true;
<<<<<<< HEAD
=======
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
>>>>>>> d785786d5ea155274c65bfa3305b907797f3797b
            }
        }

    }


    //inner class..
    class VH extends RecyclerView.ViewHolder {
<<<<<<< HEAD
        ImageView movie_pager_imgbtn1, movie_pager_imgbtn2, movie_pager_imgbtn3, movie_pager_imgbtn4;
=======
<<<<<<< HEAD
        ImageView movie_pager_imgbtn1, movie_pager_imgbtn2 , movie_pager_imgbtn3 , movie_pager_imgbtn4;
=======
        ImageView movie_pager_imgbtn1, movie_pager_imgbtn2, movie_pager_imgbtn3, movie_pager_imgbtn4;
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
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
<<<<<<< HEAD


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
                change_volum(speaker_change,position);
                speaker_change =true;

                holder.movie_pager_imgbtn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (speaker_change) { //true
                            Toast.makeText(context, "1번 아이콘 음량 0만들기", Toast.LENGTH_SHORT).show();
                            change_volum(speaker_change,position);
                            speaker_change = false;
                        } else { //false
                            Toast.makeText(context, "1번 변경 음량 100 만들기", Toast.LENGTH_SHORT).show();
                            change_volum(speaker_change,position);
                            speaker_change = true;
                        }
                    }

                });

            }
        }

=======
<<<<<<< HEAD



        } // public VH


        public void bind(@NonNull VH holder, int position){
            Board_FileVO videoItem = videoItems.get(position);
            if(videoItem.getPlayer() == null ) {
                videoItem.setPlayer(ExoPlayerFactory.newSimpleInstance(context));
                videoItem.setExoPlayerView(itemView.findViewById(R.id.exoplayerview));

=======


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
>>>>>>> origin/main

                holder.setDto(videoItem);
                String videoUrl = videoItem.getBoard_file_path();
                MediaSource mediaSource = buildMediaSource(Uri.parse(videoUrl));
                videoItem.getPlayer().prepare(mediaSource, true, false);
                videoItem.getPlayer().setPlayWhenReady(true);
            }
        }
<<<<<<< HEAD



=======

>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)

        public void setDto(Board_FileVO videoitem) {
            movie_pager_tv1.setText(videoitem.getBoard_file_id() + "");
            movie_pager_tv2.setText(videoitem.getMember_id());
            movie_pager_tv3.setText(videoitem.getBoard_file_name());
        }

        public void change_volum(boolean speaker_change, int position){
            Board_FileVO videoItem = videoItems.get(position);
            if(speaker_change){
                movie_pager_imgbtn1.setImageResource(R.drawable.mute);
                videoItem.getPlayer().setVolume(0);
            }else{
                movie_pager_imgbtn1.setImageResource(R.drawable.speaker);
                videoItem.getPlayer().setVolume(100);
            }

        }

    } // class VH
<<<<<<< HEAD


=======
<<<<<<< HEAD




=======

>>>>>>> origin/main

>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
}