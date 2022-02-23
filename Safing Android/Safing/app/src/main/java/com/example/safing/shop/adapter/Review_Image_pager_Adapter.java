package com.example.safing.shop.adapter;

<<<<<<< HEAD
=======
import static com.example.safing.async.CommonAsk.FILE_PATH;

>>>>>>> a02f5b8566c4136a456a66620dcd97c975ed763f
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
<<<<<<< HEAD:Safing Android/Safing/app/src/main/java/com/example/safing/shop/adapter/Review_Image_pager_Adapter.java
import com.example.safing.shop.DTO.Shop_Product_PagerDTO;
=======
>>>>>>> a02f5b8566c4136a456a66620dcd97c975ed763f:Safing Android/Safing/app/src/main/java/com/example/safing/shop/adapter/Review_Image_Rec_Adapter.java
=======
import com.bumptech.glide.Glide;
>>>>>>> a02f5b8566c4136a456a66620dcd97c975ed763f
import com.example.safing.R;

import java.util.ArrayList;

<<<<<<< HEAD
public class Review_Image_Rec_Adapter extends RecyclerView.Adapter<Review_Image_Rec_Adapter.ViewHolder> {
    Context context;
  //  ArrayList<Shop_Product_PagerDTO> list;
    LayoutInflater inflater;

    public Review_Image_Rec_Adapter(Context context) {
        this.context = context;
   //     this.list = list;
=======
public class Review_Image_pager_Adapter extends RecyclerView.Adapter<Review_Image_pager_Adapter.ViewHolder> {
    Context context;
    ArrayList<String> list = new ArrayList<>();
    LayoutInflater inflater;

    public Review_Image_pager_Adapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
>>>>>>> a02f5b8566c4136a456a66620dcd97c975ed763f
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_review_image_pager, parent , false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        binding(holder, position);
    }


    @Override
    public int getItemCount() {
<<<<<<< HEAD
        return 5;
=======
        return list.size();
>>>>>>> a02f5b8566c4136a456a66620dcd97c975ed763f
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView product_img1;
        TextView product_tv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_img1 = itemView.findViewById(R.id.product_img1);
            product_tv1 = itemView.findViewById(R.id.product_tv1);

        }

    }
    public void binding(ViewHolder holder, int position){
<<<<<<< HEAD
        holder.product_tv1.setText((position+1) +"/"+ 5);
=======
        if(list.get(position).indexOf("/storage/") != -1){
            Glide.with(context).load(list.get(position)).into( holder.product_img1);

        } else {
            Glide.with(context).load(FILE_PATH + list.get(position)).into( holder.product_img1);
        }

        holder.product_tv1.setText((position+1) +"/"+ list.size());
>>>>>>> a02f5b8566c4136a456a66620dcd97c975ed763f
    }
}
