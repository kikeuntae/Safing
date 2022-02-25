package com.example.safing.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.shop.DTO.Product_DetailDTO;
import com.example.safing.R;

import java.util.ArrayList;

public class Producdt_Detail_Apdater extends RecyclerView.Adapter<Producdt_Detail_Apdater.ViewHolder> {

    Context context;
    ArrayList<Product_DetailDTO> list;
    LayoutInflater inflater;
    public Producdt_Detail_Apdater(Context context) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_detail_rec, parent , false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_product_detail_img;
        TextView item_product_detail_tv1, item_product_detail_tv2, item_product_detail_tv3;

        public ImageView getItem_product_detail_img() {
            return item_product_detail_img;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_product_detail_img = itemView.findViewById(R.id.item_product_detail_img);
            item_product_detail_tv1 = itemView.findViewById(R.id.item_product_detail_tv1);
            item_product_detail_tv2 = itemView.findViewById(R.id.item_product_detail_tv2);
            item_product_detail_tv3 = itemView.findViewById(R.id.item_product_detail_tv3);


            item_product_detail_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



        }
        public void binding(ViewHolder holder, int position){

        }
    }
}