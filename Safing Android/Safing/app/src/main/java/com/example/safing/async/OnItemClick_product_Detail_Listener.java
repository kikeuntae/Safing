package com.example.safing.async;

import android.view.View;

import com.example.safing.shop.adapter.Product_Detail_Apdater;

public interface OnItemClick_product_Detail_Listener {
    public void onItemClick_detail(Product_Detail_Apdater.ViewHolder holderm,
                                   View view, int position);

}
