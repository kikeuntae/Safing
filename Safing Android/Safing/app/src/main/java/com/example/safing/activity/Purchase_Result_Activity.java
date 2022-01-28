package com.example.safing.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.safing.R;
import com.example.safing.fragment.Product_PurchaseHistory_Fragment;

public class Purchase_Result_Activity extends AppCompatActivity {
    TextView purchase_result_tv1;
    Button purchase_result_btn1, purchase_result_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_purchase_result);

        purchase_result_tv1 = findViewById(R.id.purchase_result_tv1);
        purchase_result_btn1 = findViewById(R.id.purchase_result_btn1);
        purchase_result_btn2 = findViewById(R.id.purchase_result_btn2);

        purchase_result_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Purchase_Result_Activity.this, MainActivity.class);
                intent.putExtra("fragment", "Product_PurchaseHistory_Fragment");
                startActivity(intent);
            }
        });

        purchase_result_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Purchase_Result_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}