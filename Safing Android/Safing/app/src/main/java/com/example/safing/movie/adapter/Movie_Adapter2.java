package com.example.safing.movie.adapter;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.R;
import com.example.safing.movie.DAO.Movie_DAO;
import com.example.safing.movie.DTO.Board_Movie_DTO;
import com.example.safing.movie.DTO.Movie_EXO_DTO;
import com.example.safing.movie.fragment.Movie_dialog;
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
import java.util.List;

public class Movie_Adapter2 extends RecyclerView.Adapter<Movie_Adapter2.VH> {
    boolean speaker_change = false;
    boolean heart_change = true;
    Context context;
    LayoutInflater inflater;
    DataSource.Factory factory;
    ProgressiveMediaSource.Factory mediaFactory;
    ArrayList<Movie_EXO_DTO> videoItems = new ArrayList<Movie_EXO_DTO>();
    List<Board_Movie_DTO> video_db;
    Dialog dialog;
    Movie_DAO dao = new Movie_DAO();


    public Movie_Adapter2(Context context, List<Board_Movie_DTO> video_db) {
        this.context = context;
        this.video_db = video_db;
        for (int i = 0; i < video_db.size(); i++) {
            this.videoItems.add(i,new Movie_EXO_DTO());
        }
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
        return video_db.size();
    }

    public void setVideo() {
        for (int i = 0; i < video_db.size(); i++) {
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
        for (int i = 0; i < video_db.size(); i++) {
            if (i != position && videoItems.get(i).getPlayer() != null) {
                videoItems.get(i).getPlayer().setPlayWhenReady(false);
                videoItems.get(i).getExoPlayerView().onPause();

            } else if (i == position && videoItems.get(i).getPlayer() != null) {
                videoItems.get(position).getExoPlayerView().onResume();
                videoItems.get(position).getPlayer().setPlayWhenReady(true);

            }
        }

    }


    //inner class..
    class VH extends RecyclerView.ViewHolder {
        ImageView movie_pager_volume, movie_pager_comment, movie_pager_like, movie_pager_share;
        TextView movie_pager_title, movie_pager_tag, movie_pager_comment_cnt, movie_pager_like_cnt;

        private PlayerView exoPlayerView;
        private SimpleExoPlayer player;

        public VH(@NonNull View itemView) {
            super(itemView);

            exoPlayerView = itemView.findViewById(R.id.exoplayerview);

            movie_pager_volume = itemView.findViewById(R.id.movie_pager_volume);
            movie_pager_comment = itemView.findViewById(R.id.movie_pager_comment);
            movie_pager_like = itemView.findViewById(R.id.movie_pager_like);
            movie_pager_share = itemView.findViewById(R.id.movie_pager_share);

            movie_pager_title = itemView.findViewById(R.id.movie_pager_title);
            movie_pager_tag = itemView.findViewById(R.id.movie_pager_tag);
            movie_pager_comment_cnt = itemView.findViewById(R.id.movie_pager_comment_cnt);
            movie_pager_like_cnt = itemView.findViewById(R.id.movie_pager_like_cnt);


            movie_pager_volume.setOnClickListener(new View.OnClickListener() {
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

            movie_pager_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            movie_pager_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (heart_change == true) {
                        Toast.makeText(context, "좋아요", Toast.LENGTH_SHORT).show();
                        movie_pager_like.setImageResource(R.drawable.heart2);
                        heart_change = false;
                    } else {
                        Toast.makeText(context, "좋아요 취소", Toast.LENGTH_SHORT).show();
                        movie_pager_like.setImageResource(R.drawable.heart1);
                        heart_change = true;
                    }
                }
            });

            movie_pager_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        } // public VH


        public void bind(@NonNull VH holder, int position) {
            Movie_EXO_DTO videoItem = videoItems.get(position);
            Board_Movie_DTO videodb = video_db.get(position);

            if (videoItem.getPlayer() == null) {
                videoItem.setPlayer(ExoPlayerFactory.newSimpleInstance(context));
                videoItem.setExoPlayerView(itemView.findViewById(R.id.exoplayerview));


                holder.setDto(videodb);
                String videoUrl = videodb.getFile_path();
                MediaSource mediaSource = buildMediaSource(Uri.parse(videoUrl));
                videoItem.getPlayer().prepare(mediaSource, true, false);
                videoItem.getPlayer().setPlayWhenReady(true);
                change_volum(speaker_change,position);
                speaker_change =true;

                holder.movie_pager_volume.setOnClickListener(new View.OnClickListener() {  // 소리버튼
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

                });//볼륨

                holder.movie_pager_comment.setOnClickListener(new View.OnClickListener() { // 댓글 버튼
                    @Override
                    public void onClick(View v) {
                        comment_on(context,position, video_db.get(position));
                    }
                });//댓글

                holder.movie_pager_like.setOnClickListener(new View.OnClickListener() { //좋아요 버튼
                    @Override
                    public void onClick(View v) {
                        if (heart_change == true) {
                            Toast.makeText(context, "좋아요", Toast.LENGTH_SHORT).show();
                            movie_pager_like.setImageResource(R.drawable.heart2);
                            heart_change = false;
                        } else {
                            Toast.makeText(context, "좋아요 취소", Toast.LENGTH_SHORT).show();
                            movie_pager_like.setImageResource(R.drawable.heart1);
                            heart_change = true;
                        }
                    }
                }); //좋아요

                holder.movie_pager_share.setOnClickListener(new View.OnClickListener() { //공유버튼
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }


        public void setDto(Board_Movie_DTO video_db) {
            movie_pager_title.setText(video_db.getBoard_title());
            movie_pager_tag.setText(video_db.getBoard_title());

            movie_pager_like_cnt.setText(video_db.getBoard_like_cnt()+"");
        }

        public void change_volum(boolean speaker_change, int position){
            Movie_EXO_DTO videoItem = videoItems.get(position);
            if(speaker_change){
                movie_pager_volume.setImageResource(R.drawable.mute);
                videoItem.getPlayer().setVolume(0);
            }else{
                movie_pager_volume.setImageResource(R.drawable.speaker);
                videoItem.getPlayer().setVolume(100);
            }

        }

        public void comment_on(Context context,int position, Board_Movie_DTO video_db){
            // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
            int video_id = video_db.getBoard_id();
            Movie_dialog Movie_dialog = new Movie_dialog(context,position,video_id);

            // 커스텀 다이얼로그를 호출한다.
            // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
            Movie_dialog.callFunction();

        }

    } // class VH


}