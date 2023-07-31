package com.example.aqqhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aqqhome.fragment.CaidatFragment;
import com.example.aqqhome.model.roommodel;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Retrofit;

public class AdminActivity extends AppCompatActivity {
    private ConstraintLayout qlcanho, money,quychungcu;
    private TextView nameinf, phoneinf,nameapa;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencess;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);
        qlcanho = findViewById(R.id.qlcanho);
        nameinf = findViewById(R.id.nameinf);
        phoneinf = findViewById(R.id.phoneinf);
        nameapa = findViewById(R.id.nameapa);
        quychungcu = findViewById(R.id.quychungcu);
        retrofit = RetrofitClient.getRetrofitInstance();
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        sharedPreferencess = getSharedPreferences("RoomID", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String Manager = sharedPreferences.getString("Manager", "");
        String phone = sharedPreferences.getString("phone", "");
        ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
        Call<roommodel> call = apiAQQHome.getthongtin(Manager);
        call.enqueue(new retrofit2.Callback<roommodel>() {
            @Override
            public void onResponse(Call<roommodel> call, retrofit2.Response<roommodel> response) {
                if (response.isSuccessful()){
                    roommodel roommodel = response.body();
                    nameapa.setText(roommodel.getNameApartment());
                }
            }

            @Override
            public void onFailure(Call<roommodel> call, Throwable t) {

            }


        });


        nameinf.setText(name);
        phoneinf.setText(phone);
        money = findViewById(R.id.money);

        money.setOnClickListener(v -> {
            startActivity(new Intent(AdminActivity.this, MoneyActivity.class));
        });
        quychungcu.setOnClickListener(v -> {
            startActivity(new Intent(AdminActivity.this, QuyChungCuActivity.class));
        });


        qlcanho.setOnClickListener(v -> {
            startActivity(new Intent(AdminActivity.this, QuanLyActivity.class));
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent i = new Intent(AdminActivity.this, AdminActivity.class);
                    startActivity(i);
                } else if (id == R.id.caidat) {
                    fragment = new CaidatFragment();
                } else if (id == R.id.bangtin) {
                    //fragment = new BangtinFragment();
                } else if (id == R.id.thongbao) {
                    //fragment = new ThongbaoFragment();
                } else {
                    return false;
                }
                return loadFragment(fragment);
            }
        });


    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}