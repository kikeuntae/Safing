package com.example.safing.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.async.CommonVal;
import com.example.safing.home.DAO.YoutubeTipDAO;
import com.example.safing.home.VO.YoutubeTipVO;
import com.example.safing.R;
import com.example.safing.home.activity.TipActivity;

import java.util.ArrayList;

public class YouTubeTipRecAdapter extends RecyclerView.Adapter<YouTubeTipRecAdapter.ViewHolder> {

    Context context;
    ArrayList<YoutubeTipVO> list2;
    LayoutInflater inflater;


    public YouTubeTipRecAdapter(Context context, ArrayList<YoutubeTipVO> list2) {
        this.context = context;
        this.list2 = list2;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_youtube_tip, parent , false );
        return new ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bind(holder,position);
    }


    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView eye, thumbnails;
        TextView youtubetitle, youtube_detail_title, youtube_detail_content, youtubecnt;
        LinearLayout youtubetip1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eye = itemView.findViewById(R.id.eye);
            youtubetitle = itemView.findViewById(R.id.youtubetitle);
            youtubecnt = itemView.findViewById(R.id.youtubecnt);
            youtubetip1 = itemView.findViewById(R.id.youtubetip1);
            thumbnails = itemView.findViewById(R.id.thumbnails);

            youtube_detail_title = itemView.findViewById(R.id.youtube_detail_title);
            youtube_detail_content = itemView.findViewById(R.id.youtube_detail_content);



        }


    }
    public void bind(ViewHolder holder, int i){

        holder.youtubetitle.setText(list2.get(i).getYoutubetitle()+"");
        Glide.with(context).load(list2.get(i).getThumbnails()).into(holder.thumbnails);

        holder.youtubecnt.setText(list2.get(i).getYoutubecnt()+"");



        holder.youtubetip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, TipActivity.class);
                YoutubeTipVO vo = list2.get(i);
                intent.putExtra("vo" , vo);

                YoutubeTipDAO dao = new YoutubeTipDAO();
                dao.tip_readcnt(vo.getId());

                context.startActivity(intent);
            }
        });

    }
}
