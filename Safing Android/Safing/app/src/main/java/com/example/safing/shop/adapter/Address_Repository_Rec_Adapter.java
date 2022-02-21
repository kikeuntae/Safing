package com.example.safing.shop.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.R;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.AddressVO;

import java.util.ArrayList;

public class Address_Repository_Rec_Adapter extends RecyclerView.Adapter<Address_Repository_Rec_Adapter.ViewHolder> {

    Context context;
    ArrayList<AddressVO> list;
    LayoutInflater inflater;
    ShopDAO dao = new ShopDAO();

    public Address_Repository_Rec_Adapter(Context context, ArrayList<AddressVO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void delDto(int position){
        list.remove(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_address_repository_rec, parent , false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView item_address_repository_tv1, item_address_repository_tv2, item_address_repository_tv3, item_address_repository_tv4, item_address_repository_tv5;
        ImageButton item_address_repository_btn1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_address_repository_tv1 = itemView.findViewById(R.id.item_address_repository_tv1);
            item_address_repository_tv2 = itemView.findViewById(R.id.item_address_repository_tv2);
            item_address_repository_tv3 = itemView.findViewById(R.id.item_address_repository_tv3);
            item_address_repository_tv4 = itemView.findViewById(R.id.item_address_repository_tv4);
            item_address_repository_tv5 = itemView.findViewById(R.id.item_address_repository_tv5);
            item_address_repository_btn1 = itemView.findViewById(R.id.item_address_repository_btn1);

        }
        public void binding(ViewHolder holder, int position){
            holder.item_address_repository_tv1.setText(list.get(position).getReceiver_name());
            holder.item_address_repository_tv2.setText(list.get(position).getReceiver_phone());
            holder.item_address_repository_tv3.setText(list.get(position).getAddr_post());
            holder.item_address_repository_tv4.setText(list.get(position).getAddr_basic());
            holder.item_address_repository_tv5.setText(list.get(position).getAddr_detail());


            item_address_repository_btn1.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("삭제 확인");
                    builder.setMessage("선택하신 주소를\n삭제하시겠습니까? ");
                    builder.setIcon(R.drawable.question);

                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delDto(position);
                            dao.delete_addr(list.get(position).getAddr_num());
                            notifyDataSetChanged();
                        }
                    });

                    //builder.nagative 아니오 눌림 Toast
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
    }
}
