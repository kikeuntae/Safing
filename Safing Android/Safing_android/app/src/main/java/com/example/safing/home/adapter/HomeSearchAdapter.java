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
import com.example.safing.R;
import com.example.safing.home.VO.SafeZoneRecVO;
import com.example.safing.home.activity.CamInfoActivity;

import java.util.ArrayList;

public class HomeSearchAdapter extends RecyclerView.Adapter<HomeSearchAdapter.Viewholder>{
    //xml로 만들어놓은 아이템을 붙이기위한 LayoutInflater <- Context
    Context context;
    ArrayList<SafeZoneRecVO> list;
    LayoutInflater inflater;


    public HomeSearchAdapter(Context context, ArrayList<SafeZoneRecVO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1. 화면을 Inflate 시키는 작업을 하기
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //제어권을 viewHolder가 가지고 있다.
        View itemView = inflater.inflate(R.layout.item_search_camp, parent, false);
        //Viewholder viewholder = new Viewholder(itemView);
        //return viewholder;
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.bind(holder,position);
    }
    //2. list.size로 바꾼다
    @Override
    public int getItemCount() {
        return list.size();
    }
    //3.bind 만들어서 여기서
    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView s_camimg;
        TextView s_facltnm, s_addr1, s_induty;
        LinearLayout s_campinfo;
        public Viewholder(@NonNull View itemView) {

            super(itemView);
            s_camimg = itemView.findViewById(R.id.s_camimg);
            s_facltnm = itemView.findViewById(R.id.s_facltnm);
            s_addr1 = itemView.findViewById(R.id.s_addr1);
            s_campinfo = itemView.findViewById(R.id.s_campinfo);
            s_induty = itemView.findViewById(R.id.s_induty);
        }
        //3. ItemView세팅되고 나서 list <=> item.xml 연결해서 세팅하는 부분
        public void bind(@NonNull Viewholder holder, int position) {
            //4. 내용 바꾸기
            if (list.get(position).getFirstimageurl().equals("-")) {
                holder.s_camimg.setImageResource(R.drawable.emptyimage);
            }else {
                Glide.with(context).load(list.get(position).getFirstimageurl()).into(holder.s_camimg);
            }

            holder.s_facltnm.setText(list.get(position).getFacltnm());
            holder.s_addr1.setText(list.get(position).getAddr1());
            holder.s_induty.setText(list.get(position).getInduty());

            holder.s_campinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Detail로 이동, Detail에서 추가 수정 삭제.
                    Intent intent = new Intent(context, CamInfoActivity.class);
                    intent.putExtra("vo", list.get(position));  //vo를 Serializable 직렬화 시킨 후 intent 로 받을 수 있음
                    context.startActivity(intent);

                }
            });
        }

    }

}//CusAdapter
