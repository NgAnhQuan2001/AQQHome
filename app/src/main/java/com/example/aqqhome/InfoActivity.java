package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class InfoActivity extends AppCompatActivity {
    private EditText editTextName,editTextPhone,editTextEmail,editTextApartment;

    private ImageView back;

    private SharedPreferences sharedPreferences,sharedPreferencess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        sharedPreferencess = getSharedPreferences("RoomID", MODE_PRIVATE);
        editTextName = findViewById(R.id.editTextName);
        editTextName.setText(sharedPreferences.getString("name", ""));
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPhone.setText(sharedPreferences.getString("phone", ""));
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.setText(sharedPreferences.getString("email", ""));
        editTextApartment = findViewById(R.id.editTextApartment);
        editTextApartment.setText(sharedPreferencess.getString("RoomName", ""));

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            finish();
        });








    }
}