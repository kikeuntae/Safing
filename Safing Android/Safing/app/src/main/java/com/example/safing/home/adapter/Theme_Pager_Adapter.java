package com.example.safing.home.adapter;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.content.Context;

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
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonVal;
import com.example.safing.async.OnItemClick_Theme_Listener;
import com.example.safing.home.DTO.ThemeRecDTO;

import java.util.ArrayList;


public class Theme_Pager_Adapter extends RecyclerView.Adapter<Theme_Pager_Adapter.ViewHolder> implements OnItemClick_Theme_Listener{

    Context context;
    LayoutInflater inflater;
    ArrayList<ThemeRecDTO> list;
    OnItemClick_Theme_Listener listener;


    public Theme_Pager_Adapter(Context context, ArrayList<ThemeRecDTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(OnItemClick_Theme_Listener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.rec_item_theme, parent , false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       binding(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick_package(ViewHolder holderm, View view, int position) {
        if(listener != null){
            listener.onItemClick_package(holderm, view, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView theme_rec_tv1, theme_rec_tv2, theme_rec_tv3;
        ImageView theme_rec_imgv1, theme_rec_imgv2, theme_rec_imgv3;
        LinearLayout rec_item_theme;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            theme_rec_tv1 = itemView.findViewById(R.id.theme_rec_tv1);
            theme_rec_tv2 = itemView.findViewById(R.id.theme_rec_tv2);
            theme_rec_tv3 = itemView.findViewById(R.id.theme_rec_tv3);
            theme_rec_imgv1 = itemView.findViewById(R.id.theme_rec_imgv1);
            theme_rec_imgv2 = itemView.findViewById(R.id.theme_rec_imgv2);
            theme_rec_imgv3 = itemView.findViewById(R.id.theme_rec_imgv3);
            rec_item_theme = itemView.findViewById(R.id.rec_item_theme);

            rec_item_theme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick_package(ViewHolder.this,
                                v, position);
                    }
                }
            });


        }
    }
    public void binding(ViewHolder holder, int position){
        holder.theme_rec_tv1.setText(list.get(position).getPackage_name());
        holder.theme_rec_tv2.setText(list.get(position).getFacltnm());
        String[] tag = list.get(position).getTag_key().split("#");
        holder.theme_rec_tv3.setText("#" + tag[0] + " " +"#" + tag[1] + " " + "#" + tag[2] + " " + "#" + tag[3]);
        Glide.with(context).load(list.get(position).getFirstimageurl()).into( holder.theme_rec_imgv1);
        Glide.with(context).load(FILE_PATH + list.get(position).getFile_path1()).into( holder.theme_rec_imgv2);
        Glide.with(context).load(FILE_PATH + list.get(position).getFile_path2()).into( holder.theme_rec_imgv3);
    }
}