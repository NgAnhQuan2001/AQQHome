package com.example.aqqhome;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.Map;

public class IntroActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencess;
    private String type;

    private static final int SPLASH_TIME_OUT = 5000; // Thời gian chờ là 5 giây

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        type = sharedPreferences.getString("Type", "");
        sharedPreferencess = getSharedPreferences("RoomID", MODE_PRIVATE);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Xin vui lòng chờ...");
        progressDialog.show();

        new Handler().postDelayed(() -> {
            progressDialog.dismiss();
            Map<String, ?> keys = sharedPreferences.getAll();
            Map<String, ?> keyss = sharedPreferencess.getAll();


            if (keys.isEmpty()) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else if (keyss.isEmpty() && !type.equals("1")) {
                Intent intent = new Intent(IntroActivity.this, NewCodeActivity.class);
                startActivity(intent);
                finish();
            } else if (!keys.isEmpty() && type.equals("1")){
                Intent intent = new Intent(IntroActivity.this, AdminActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }, SPLASH_TIME_OUT);
    }
}
