package com.example.safing.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.home.VO.CampImgVO;
import com.example.safing.home.VO.SafeZoneRecVO;
import com.example.safing.home.activity.CamInfoActivity;

import java.util.ArrayList;


public class CaminfoPager2Adapter extends RecyclerView.Adapter<CaminfoPager2Adapter.ViewHolder> {

    Context context;
    ArrayList<CampImgVO> list;
    LayoutInflater inflater;

    public CaminfoPager2Adapter(CamInfoActivity context, ArrayList<CampImgVO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.pager_item_caminfo, parent , false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bind(holder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView pager_campimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pager_campimg = itemView.findViewById(R.id.pager_campimg);

        }
    }
            //Glide.with(context).load(list.get(i).getImageurl()).into(holder.pager_campimg);
            //holder.pager_campimg.setImageResource(R.drawable.emptyimage);
    public void bind(ViewHolder holder, int i){
            if (list.get(i).getImageurl() == null) {
                holder.pager_campimg.setImageResource(R.drawable.emptyimage);
            } else {
                Glide.with(context).load(list.get(i).getImageurl()).into(holder.pager_campimg);
            }





    }
}