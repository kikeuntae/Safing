package com.example.safingproject.activity.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safingproject.R;
import com.example.safingproject.activity.MainActivity;

import java.io.InputStream;

public class HomeSearchActivity extends AppCompatActivity {
    SearchView searchView;
    ImageView backbtn;
    TextView search_cam;
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

        search_cam = findViewById(R.id.search_cam);
        search_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeSearchActivity.this, CamInfoActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_right_exit);

    }
}