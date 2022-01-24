package com.example.safing.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.DTO.SafeZoneRecDTO;
import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.example.safing.fragment.Product_Fragment;
import com.example.safing.fragment.Product_Package_Fragment;

import java.util.ArrayList;

public class Shop_Rec_Adapter extends RecyclerView.Adapter<Shop_Rec_Adapter.ViewHolder> {
    boolean bookMark = true;
    Context context;
    ArrayList<SafeZoneRecDTO> list;
    LayoutInflater inflater;

    public Shop_Rec_Adapter(Context context) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_info, parent , false );
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
        ImageView product_img1, product_img2;
        TextView product_tv1, product_tv2, product_tv3, product_tv4, product_tv5, product_tv6, product_tv7, product_tv8, product_tv9;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_img1 = itemView.findViewById(R.id.product_img1);
            product_img2 = itemView.findViewById(R.id.product_img2);
            product_tv1 = itemView.findViewById(R.id.product_tv1);
            product_tv2 = itemView.findViewById(R.id.product_tv2);
            product_tv3 = itemView.findViewById(R.id.product_tv3);
            product_tv4 = itemView.findViewById(R.id.product_tv4);
            product_tv5 = itemView.findViewById(R.id.product_tv5);
            product_tv6 = itemView.findViewById(R.id.product_tv6);
            product_tv7 = itemView.findViewById(R.id.product_tv7);
            product_tv8 = itemView.findViewById(R.id.product_tv8);
            product_tv9 = itemView.findViewById(R.id.product_tv9);

            product_img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHoler.this,
                                view, position);
                    }
                }
            });

            product_img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bookMark== true) {
                        Toast.makeText(context, "장바구니", Toast.LENGTH_SHORT).show();
                        product_img2.setImageResource(R.drawable.bookmark2);
                        bookMark= false;
                    }else {
                        Toast.makeText(context, "장바구니 취소", Toast.LENGTH_SHORT).show();
                        product_img2.setImageResource(R.drawable.bookmark1);
                        bookMark= true;
                    }

                }
            });

        }
        public void binding(ViewHolder holder, int position){

        }
    }
}
