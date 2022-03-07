package com.example.safing.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.home.VO.SafeZoneRecVO;
import com.example.safing.home.activity.CamInfoActivity;

import java.util.ArrayList;

public class SafeZoneRecAdapter extends RecyclerView.Adapter<SafeZoneRecAdapter.ViewHolder> {

    Context context;
    ArrayList<SafeZoneRecVO> list;
    LayoutInflater inflater;



    public SafeZoneRecAdapter(Context context, ArrayList<SafeZoneRecVO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.rec_item_sfzone, parent , false );
        return new ViewHolder(itemview);
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
        ImageView firstimageurl ;
        TextView  facltnm, addr1, intro, resvecl, tel, homepage, induty, prmisnde, insrncat, animalcmgcl, facltdivnm,
                mangedivnm,  mgcdiv, operdecl, toiletco, swrmco, wtrplco, sbrscl, mapx, mapy, sfzone;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstimageurl = itemView.findViewById(R.id.firstimageurl);
            facltnm = itemView.findViewById(R.id.facltnm1);

        }

    }
    public void bind(ViewHolder holder, int i){
        holder.firstimageurl.setColorFilter(Color.parseColor("#CD959595"), PorterDuff.Mode.MULTIPLY);
        Glide.with(context).load(list.get(i).getFirstimageurl()).into(holder.firstimageurl);
        holder.facltnm.setText(list.get(i).getFacltnm());

        holder.firstimageurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CamInfoActivity.class);
                SafeZoneRecVO vo = list.get(i);
                intent.putExtra("vo", vo);

                context.startActivity(intent);

            }
        });

    }
}
