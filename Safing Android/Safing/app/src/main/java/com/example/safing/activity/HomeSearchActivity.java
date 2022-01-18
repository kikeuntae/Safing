package com.example.safing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.safing.R;

public class HomeSearchActivity extends AppCompatActivity {
    SearchView searchView;
    ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        searchView = findViewById(R.id.search_searchv);
        backbtn = findViewById(R.id.search_backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeSearchActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_right_exit);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_right_exit);

    }
}