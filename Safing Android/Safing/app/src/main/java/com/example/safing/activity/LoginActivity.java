package com.example.safing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safing.R;

public class LoginActivity extends AppCompatActivity {

    Button member_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_login);

        member_insert = findViewById(R.id.member_insert);

        member_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });

    }
}