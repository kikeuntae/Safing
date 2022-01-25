package com.example.safing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.DTO.YouTubeTipRecDTO;
import com.example.safing.R;

import java.util.ArrayList;

public class YouTubeTipRecAdapter extends RecyclerView.Adapter<YouTubeTipRecAdapter.ViewHolder> {

    Context context;
    ArrayList<YouTubeTipRecDTO> list2;
    LayoutInflater inflater;
    CardView youtubeview;
    ImageView youtubeview1, eye ;
    TextView youtubetitle, youtubecontent, youtubecnt;

    public YouTubeTipRecAdapter(Context context, ArrayList<YouTubeTipRecDTO> list2) {
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

    }


    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            youtubeview = itemView.findViewById(R.id.youtubeview);
            youtubeview1 = itemView.findViewById(R.id.youtubeview1);
            eye = itemView.findViewById(R.id.eye);
            youtubetitle = itemView.findViewById(R.id.youtubetitle);
            youtubecontent = itemView.findViewById(R.id.youtubecontent);
            youtubecnt = itemView.findViewById(R.id.youtubecnt);

        }
        public void binding(ViewHolder holder, int position){

        }
    }
}
