package com.example.safing.shop.adapter;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.async.CommonVal;
import com.example.safing.async.OnItemClick_Cart_Listener;
import com.example.safing.async.OnItemClick_product_Detail_Listener;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.CartVO;
import com.example.safing.shop.fragment.Product_Cart_Fragment;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Product_Cart_Rec_Adapter extends RecyclerView.Adapter<Product_Cart_Rec_Adapter.ViewHolder> implements OnItemClick_Cart_Listener {

    Context context;
    LayoutInflater inflater;
    ShopDAO dao = new ShopDAO();
    ArrayList<CartVO> list = new ArrayList<>();
    OnItemClick_Cart_Listener listener;

    public Product_Cart_Rec_Adapter(Context context, ArrayList<CartVO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(OnItemClick_Cart_Listener listener){
        this.listener = listener;
    }

    public void delDto(int position){
        list.remove(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_cart_rec, parent , false );
        return new ViewHolder(itemview, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public void onItemClick_Cart(ViewHolder holderm, View view, int position) {
        if(listener != null){
            listener.onItemClick_Cart(holderm, view, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_product_cart_rec_img, item_product_cart_rec_btn1, product_cart_btn1;
        TextView item_product_cart_rec_tv1, item_product_cart_rec_tv2, item_product_cart_rec_tv3, item_product_cart_rec_tv4;
        CheckBox item_product_cart_rec_box, product_cart_box1;

        public ViewHolder(@NonNull View itemView, OnItemClick_Cart_Listener listener) {
            super(itemView);

            item_product_cart_rec_img = itemView.findViewById(R.id.item_product_cart_rec_img);
            item_product_cart_rec_tv1 = itemView.findViewById(R.id.item_product_cart_rec_tv1);
            item_product_cart_rec_tv2 = itemView.findViewById(R.id.item_product_cart_rec_tv2);
            item_product_cart_rec_tv3 = itemView.findViewById(R.id.item_product_cart_rec_tv3);
            item_product_cart_rec_tv4 = itemView.findViewById(R.id.item_product_cart_rec_tv4);
            item_product_cart_rec_box = itemView.findViewById(R.id.item_product_cart_rec_box);
            item_product_cart_rec_btn1 = itemView.findViewById(R.id.item_product_cart_rec_btn1);

            Product_Cart_Fragment fragment = new Product_Cart_Fragment(context);
            product_cart_box1 = fragment.getActivity().findViewById(R.id.product_cart_box1);
            product_cart_btn1 = fragment.getActivity().findViewById(R.id.product_cart_btn1);

            item_product_cart_rec_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick_Cart(ViewHolder.this,
                                v, position);
                    }
                }
            });


            product_cart_box1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item_product_cart_rec_box.setChecked(true);
                }
            });

            if(item_product_cart_rec_box.isChecked()){
                fragment.changePrice(list.get(getAdapterPosition()).getProduct_price(), getAdapterPosition());
            }

            product_cart_btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("주문삭제 확인");
                    builder.setMessage("선택하신 주문을\n삭제하시겠습니까? ");
                    builder.setIcon(R.drawable.question1);

                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(item_product_cart_rec_box.isChecked()){
                                delDto(getAdapterPosition());
                                notifyDataSetChanged();
                                dao.delete_cart(list.get(getAdapterPosition()).getCart_num());
                            }
                        }
                    });

                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog  = builder.create();
                    dialog.show();
                }
            });

            item_product_cart_rec_btn1.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("주문삭제 확인");
                    builder.setMessage("선택하신 주문을\n삭제하시겠습니까? ");
                    builder.setIcon(R.drawable.question1);

                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delDto(getAdapterPosition());
                            notifyDataSetChanged();
                            dao.delete_cart(list.get(getAdapterPosition()).getCart_num());
                        }
                    });

                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog  = builder.create();
                    dialog.show();
                }
            });
        }
        public void binding(ViewHolder holder, int position){
            Glide.with(context).load(FILE_PATH + list.get(position).getFile_path()).into(holder.item_product_cart_rec_img);

            if(list.get(position).getProduct_name() != null){
                holder.item_product_cart_rec_tv1.setText(list.get(position).getProduct_name());
            } else {
                holder.item_product_cart_rec_tv1.setText(list.get(position).getPackage_name());
            }
            holder.item_product_cart_rec_tv2.setText(NumberFormat.getInstance().format(list.get(position).getProduct_price()/list.get(position).getOrder_count())+"원");
            holder.item_product_cart_rec_tv3.setText(list.get(position).getOrder_count()+"개");
            holder.item_product_cart_rec_tv4.setText(NumberFormat.getInstance().format(list.get(position).getProduct_price())+"원");

        }
    }
}
