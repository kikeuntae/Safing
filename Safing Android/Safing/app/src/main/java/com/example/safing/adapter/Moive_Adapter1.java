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

import com.example.safing.DTO.Movie_pagerDTO;
import com.example.safing.R;
import com.example.safing.VO.BoardVO;
import com.example.safing.VO.CommonFileVO;
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

public class Moive_Adapter1 extends RecyclerView.Adapter<Moive_Adapter1.ViewHolder> {
    Context context;
    LayoutInflater inflater;
    ArrayList<Movie_pagerDTO> mlist = new ArrayList<>();
    ArrayList<CommonFileVO> flist = new ArrayList<>();

    DataSource.Factory factory;
    ProgressiveMediaSource.Factory mediaFactory;

    public Moive_Adapter1(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        factory = new DefaultDataSourceFactory(context, "Ex90ExoPlayer"); // 매개 두번째는 임의로 그냥 적음
        mediaFactory = new ProgressiveMediaSource.Factory(factory);
    }

    @NonNull
    @Override
    public Moive_Adapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_exoplayer_movie, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder,position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton movie_pager_imgbtn1, movie_pager_imgbtn2 , movie_pager_imgbtn3 , movie_pager_imgbtn4 , movie_pager_imgbtn5;
        TextView movie_pager_tv1, movie_pager_tv2, movie_pager_tv3, movie_pager_tv4;

        private PlayerView exoPlayerView;
        private SimpleExoPlayer player;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            exoPlayerView = itemView.findViewById(R.id.exoplayerview);

            movie_pager_imgbtn1 = itemView.findViewById(R.id.movie_pager_imgbtn1);
            movie_pager_imgbtn2 = itemView.findViewById(R.id.movie_pager_imgbtn2);
            movie_pager_imgbtn3 = itemView.findViewById(R.id.movie_pager_imgbtn3);
            movie_pager_imgbtn4 = itemView.findViewById(R.id.movie_pager_imgbtn4);
            movie_pager_imgbtn5 = itemView.findViewById(R.id.movie_pager_imgbtn5);

            movie_pager_tv1 = itemView.findViewById(R.id.movie_pager_tv1);
            movie_pager_tv2 = itemView.findViewById(R.id.movie_pager_tv2);
            movie_pager_tv3 = itemView.findViewById(R.id.movie_pager_tv3);
            movie_pager_tv4 = itemView.findViewById(R.id.movie_pager_tv4);

            //exoplayer 실행
            initializePlayer();

            movie_pager_tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(icon_change== true) {
                        Toast.makeText(context, "1번 아이콘 음량 0만들기", Toast.LENGTH_SHORT).show();
                        movie_pager_imgbtn2.setImageResource(R.drawable.mute);
                        player.setVolume(0);
                        icon_change= false;
                    }else {
                        Toast.makeText(context, "1번 변경 음량 100 만들기", Toast.LENGTH_SHORT).show();
                        movie_pager_imgbtn2.setImageResource(R.drawable.speaker);
                        player.setVolume(100);
                        icon_change= true;
                    }
                }
            });



        }//public ViewHolder

        public  void bind(ViewHolder holder, int postion){

        }//bind()

        private void initializePlayer() {
            if (player == null) {

                player = ExoPlayerFactory.newSimpleInstance(context);

                //플레이어 연결
                exoPlayerView.setPlayer(player);

                //컨트롤러 없애기
                //exoPlayerView.setUseController(false);

                //사이즈 조절
                //exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM); // or RESIZE_MODE_FILL

                //음량조절
                //player.setVolume(0);

                //프레임 포지션 설정
                //player.seekTo(currentWindow, playbackPosition);

            }

            String sample = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

            MediaSource mediaSource = buildMediaSource(Uri.parse(sample));

            //prepare
            player.prepare(mediaSource, true, false);

            //start,stop
            player.setPlayWhenReady(true);
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

    }//class ViewHolder

    //홀더를 화면에 온전히 보여짐
    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder){
        super.onViewDetachedFromWindow(holder);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.player.setPlayWhenReady(true);
    }

    //홀더를 화면에 온전히 보여지지 않음
    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder){
        super.onViewDetachedFromWindow(holder);
        ViewHolder viewHolder = (ViewHolder)holder;

        viewHolder.player.setPlayWhenReady(false);
    }


}