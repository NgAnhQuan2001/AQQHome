package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class QuanLyActivity extends AppCompatActivity {
    private ImageView cardView,back;
    private CardView suachiphi,xemthongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly);
        cardView = findViewById(R.id.themtoanha);
        suachiphi = findViewById(R.id.suachiphi);
        xemthongtin = findViewById(R.id.xemthongtin);
        cardView.setOnClickListener(v -> {
            startActivity(new Intent(QuanLyActivity.this, AddRoomActivity.class));
        });
        suachiphi.setOnClickListener(v -> {
            startActivity(new Intent(QuanLyActivity.this, Admin_SuachiphiActivity.class));
        });
        xemthongtin.setOnClickListener(v -> {
            startActivity(new Intent(QuanLyActivity.this, Admin_XemthongtinActivity.class));
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            startActivity(new Intent(QuanLyActivity.this, AdminActivity.class));
        });
    }

}