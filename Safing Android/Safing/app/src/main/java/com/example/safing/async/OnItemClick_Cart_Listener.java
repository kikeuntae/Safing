package com.example.safing.async;

import android.view.View;

import com.example.safing.shop.adapter.Product_Cart_Rec_Adapter;

public interface OnItemClick_Cart_Listener {
    public void onItemClick_Cart(Product_Cart_Rec_Adapter.ViewHolder holderm,
                                 View view, int position);

}
