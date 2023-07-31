package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aqqhome.model.feemodel;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Admin_SuachiphiActivity extends AppCompatActivity {
    private ImageView back;

    private Retrofit retrofit;
    private SharedPreferences sharedpreferences;
    private TextView phiquanly, phirac;
    private String ApartmentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_suachiphi);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            finish();
        });
        phiquanly = findViewById(R.id.phiquanly);
        phirac = findViewById(R.id.phirac);
        retrofit = RetrofitClient.getRetrofitInstance();


        sharedpreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String ApartmentID = sharedpreferences.getString("Manager", "");
        ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
        Call<feemodel> call = apiAQQHome.admingetchiphi(ApartmentID);
        call.enqueue(new retrofit2.Callback<feemodel>() {
            @Override
            public void onResponse(Call<feemodel> call, retrofit2.Response<feemodel> response) {
                if (response.isSuccessful()) {
                    feemodel text = response.body();
                    if (text != null && text.isSuccess()) {
                        String a = text.getManagementFee();
                        String b = text.getGarbageFee();
                        phiquanly.setText(a);
                        phirac.setText(b);
                    }
                }
            }

            @Override
            public void onFailure(Call<feemodel> call, Throwable t) {

            }
        });



    }
}