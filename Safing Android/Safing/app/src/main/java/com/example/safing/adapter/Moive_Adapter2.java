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

<<<<<<< HEAD
public class Moive_Adapter2 extends RecyclerView.Adapter<Moive_Adapter2.ViewHolder> {
    boolean speaker_change= true;
    boolean heart_change= true;
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
    boolean speaker_change = true;
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
>>>>>>> origin/main
>>>>>>> origin/기근태
=======
public class Moive_Adapter2 extends RecyclerView.Adapter<Moive_Adapter2.VH> {
    boolean speaker_change= true;
    boolean heart_change= true;

>>>>>>> 기근태
    Context context;
    LayoutInflater inflater;
    DataSource.Factory factory;
    ProgressiveMediaSource.Factory mediaFactory;

    ArrayList<Board_FileVO> videoItems = new ArrayList<>();

    public Moive_Adapter2(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        factory = new DefaultDataSourceFactory(context, "Ex90ExoPlayer"); // 매개 두번째는 임의로 그냥 적음
        mediaFactory = new ProgressiveMediaSource.Factory(factory);
    }

    public void addDto(Board_FileVO VideoItem) {
        videoItems.add(VideoItem);
    }

    @NonNull
    @Override
    public Moive_Adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_exoplayer_movie, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
<<<<<<< HEAD
    public void onBindViewHolder(@NonNull Moive_Adapter2.ViewHolder holder, int position) {
        Board_FileVO videoItem = videoItems.get(position);
        holder.setDto(videoItem);
        String sample = videoItem.getBoard_file_path();

<<<<<<< HEAD
=======
<<<<<<< HEAD
        holder.bind(holder , position);
=======
<<<<<<< HEAD
        holder.bind(holder, position);
=======
<<<<<<< HEAD
        holder.bind(holder , position);
=======
        holder.bind(holder, position);
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
>>>>>>> origin/main
>>>>>>> origin/기근태

        MediaSource mediaSource = holder.buildMediaSource(Uri.parse(sample));

        //prepare
        holder.player.prepare(mediaSource, true, false);
=======
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(holder , position);
>>>>>>> 기근태
    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movie_pager_imgbtn1, movie_pager_imgbtn2 , movie_pager_imgbtn3 , movie_pager_imgbtn4;
=======
<<<<<<< HEAD
    public void setVideo(){
        for(int i = 0 ; i<videoItems.size(); i++){
            if(videoItems.get(i).getPlayer() != null){
=======
<<<<<<< HEAD
    public void setVideo() {
        for (int i = 0; i < videoItems.size(); i++) {
            if (videoItems.get(i).getPlayer() != null) {
=======
<<<<<<< HEAD
=======
>>>>>>> 기근태
    public void setVideo(){
        for(int i = 0 ; i<videoItems.size(); i++){
            if(videoItems.get(i).getPlayer() != null){
                videoItems.get(i).getPlayer().setPlayWhenReady(false);
                videoItems.get(i).getExoPlayerView().onPause();
            }else if(i == position && videoItems.get(i).getPlayer() != null){
                videoItems.get(position).getExoPlayerView().onResume();
                videoItems.get(position).getPlayer().setPlayWhenReady(true);

                speaker_change = true;
            }
        }
    }


<<<<<<< HEAD
    //inner class..
    class VH extends RecyclerView.ViewHolder {
<<<<<<< HEAD
        ImageView movie_pager_imgbtn1, movie_pager_imgbtn2 , movie_pager_imgbtn3 , movie_pager_imgbtn4;
=======
<<<<<<< HEAD
        ImageView movie_pager_imgbtn1, movie_pager_imgbtn2, movie_pager_imgbtn3, movie_pager_imgbtn4;
=======
<<<<<<< HEAD
        ImageView movie_pager_imgbtn1, movie_pager_imgbtn2 , movie_pager_imgbtn3 , movie_pager_imgbtn4;
=======
        ImageView movie_pager_imgbtn1, movie_pager_imgbtn2, movie_pager_imgbtn3, movie_pager_imgbtn4;
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
>>>>>>> origin/main
>>>>>>> origin/기근태
        TextView movie_pager_tv1, movie_pager_tv2, movie_pager_tv3;

        private PlayerView exoPlayerView;
        private SimpleExoPlayer player;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            exoPlayerView = itemView.findViewById(R.id.exoplayerview);

            movie_pager_imgbtn1 = itemView.findViewById(R.id.movie_pager_imgbtn1);
            movie_pager_imgbtn2 = itemView.findViewById(R.id.movie_pager_imgbtn2);
            movie_pager_imgbtn3 = itemView.findViewById(R.id.movie_pager_imgbtn3);
            movie_pager_imgbtn4 = itemView.findViewById(R.id.movie_pager_imgbtn4);

            movie_pager_tv1 = itemView.findViewById(R.id.movie_pager_tv1);
            movie_pager_tv2 = itemView.findViewById(R.id.movie_pager_tv2);
            movie_pager_tv3 = itemView.findViewById(R.id.movie_pager_tv3);

            //exoplayer 실행
            initializePlayer();


            movie_pager_imgbtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(speaker_change== true) {
                        Toast.makeText(context, "1번 아이콘 음량 0만들기", Toast.LENGTH_SHORT).show();
                        movie_pager_imgbtn1.setImageResource(R.drawable.mute);
                        player.setVolume(0);
                        speaker_change= false;
                    }else {
                        Toast.makeText(context, "1번 변경 음량 100 만들기", Toast.LENGTH_SHORT).show();
                        movie_pager_imgbtn1.setImageResource(R.drawable.speaker);
                        player.setVolume(100);
                        speaker_change= true;
                    }
                }
            });
=======
//inner class..
class VH extends RecyclerView.ViewHolder {
    ImageView movie_pager_imgbtn1, movie_pager_imgbtn2 , movie_pager_imgbtn3 , movie_pager_imgbtn4;
    TextView movie_pager_tv1, movie_pager_tv2, movie_pager_tv3;
>>>>>>> 기근태

    private PlayerView exoPlayerView;
    private SimpleExoPlayer player;

    public VH(@NonNull View itemView) {
        super(itemView);

        exoPlayerView = itemView.findViewById(R.id.exoplayerview);

<<<<<<< HEAD
                }
            });
        }//public ViewHolder
        private void initializePlayer() {
            if (player == null) {

                player = ExoPlayerFactory.newSimpleInstance(context);
=======
        movie_pager_imgbtn1 = itemView.findViewById(R.id.movie_pager_imgbtn1);
        movie_pager_imgbtn2 = itemView.findViewById(R.id.movie_pager_imgbtn2);
        movie_pager_imgbtn3 = itemView.findViewById(R.id.movie_pager_imgbtn3);
        movie_pager_imgbtn4 = itemView.findViewById(R.id.movie_pager_imgbtn4);

        movie_pager_tv1 = itemView.findViewById(R.id.movie_pager_tv1);
        movie_pager_tv2 = itemView.findViewById(R.id.movie_pager_tv2);
        movie_pager_tv3 = itemView.findViewById(R.id.movie_pager_tv3);
>>>>>>> 기근태

                //플레이어 연결
                exoPlayerView.setPlayer(player);

<<<<<<< HEAD
                //컨트롤러 없애기
                //exoPlayerView.setUseController(false);

                //사이즈 조절
                //exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM); // or RESIZE_MODE_FILL

                //음량조절
                //player.setVolume(0);

<<<<<<< HEAD
                //프레임 포지션 설정
                //player.seekTo(currentWindow, playbackPosition);
=======

                holder.setDto(videoItem);
                String videoUrl = videoItem.getBoard_file_path();
                MediaSource mediaSource = buildMediaSource(Uri.parse(videoUrl));
                videoItem.getPlayer().prepare(mediaSource, true, false);
                videoItem.getPlayer().setPlayWhenReady(true);
=======
        movie_pager_imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(speaker_change== true) {
                    Toast.makeText(context, "1번 아이콘 음량 0만들기", Toast.LENGTH_SHORT).show();
                    movie_pager_imgbtn1.setImageResource(R.drawable.mute);
                    player.setVolume(0);
                    speaker_change= false;
                }else {
                    Toast.makeText(context, "1번 변경 음량 100 만들기", Toast.LENGTH_SHORT).show();
                    movie_pager_imgbtn1.setImageResource(R.drawable.speaker);
                    player.setVolume(100);
                    speaker_change= true;
                }
>>>>>>> 기근태
            }
        });

<<<<<<< HEAD
=======
>>>>>>> origin/기근태

            }

            String sample = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

            MediaSource mediaSource = buildMediaSource(Uri.parse(sample));
=======
        movie_pager_imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        movie_pager_imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(heart_change== true) {
                    Toast.makeText(context, "좋아요", Toast.LENGTH_SHORT).show();
                    movie_pager_imgbtn3.setImageResource(R.drawable.heart2);
                    heart_change= false;
                }else {
                    Toast.makeText(context, "좋아요 취소", Toast.LENGTH_SHORT).show();
                    movie_pager_imgbtn3.setImageResource(R.drawable.heart1);
                    heart_change= true;
                }
            }
        });

        movie_pager_imgbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    } // public VH
>>>>>>> 기근태

            //prepare
            player.prepare(mediaSource, true, false);

            //start,stop
            player.setPlayWhenReady(true);
        }
        private MediaSource buildMediaSource(Uri uri) {

            String userAgent = Util.getUserAgent(context, "blackJin");

<<<<<<< HEAD
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

<<<<<<< HEAD
        }
=======
>>>>>>> origin/main
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
=======
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

                holder.setDto(videoItem);
                String videoUrl = videoItem.getBoard_file_path();
                MediaSource mediaSource = buildMediaSource(Uri.parse(videoUrl));
                videoItem.getPlayer().prepare(mediaSource, true, false);
                videoItem.getPlayer().setPlayWhenReady(true);
            }
        }
>>>>>>> 기근태


>>>>>>> origin/기근태

        public void setDto(Board_FileVO videoitem) {
            movie_pager_tv1.setText(videoitem.getBoard_file_id()+"");
            movie_pager_tv2.setText(videoitem.getMember_id());
            movie_pager_tv3.setText(videoitem.getBoard_file_name());
        }
    }//class ViewHolder

<<<<<<< HEAD
    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder){
        super.onViewDetachedFromWindow(holder);
       ViewHolder viewHolder = (ViewHolder) holder;
=======
    } // class VH
<<<<<<< HEAD
<<<<<<< HEAD


=======
<<<<<<< HEAD


>>>>>>> origin/기근태

        viewHolder.player.setPlayWhenReady(true);
    }

    //홀더를 화면에 온전히 보여지지 않음
    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder){
        super.onViewDetachedFromWindow(holder);
        ViewHolder viewHolder = (ViewHolder)holder;

        viewHolder.player.setPlayWhenReady(false);
    }

<<<<<<< HEAD
}
=======
<<<<<<< HEAD



=======
>>>>>>> parent of 74edf15 (01/27 20:38 최성욱)
>>>>>>> origin/main
}
>>>>>>> origin/기근태
=======
>>>>>>> 기근태
