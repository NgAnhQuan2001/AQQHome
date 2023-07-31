package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aqqhome.model.bankmodel;
import com.example.aqqhome.model.roommodel;
import com.example.aqqhome.model.roommodel2;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;

public class QuyChungCuActivity extends AppCompatActivity {
    private ImageView back;
    private TextView sotien;
    private String ApartmentID;
    private SharedPreferences sharedPreferences;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quy_chung_cu);
        sotien = findViewById(R.id.sotien);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            finish();
        });
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        ApartmentID = sharedPreferences.getString("Manager", "");
        retrofit = RetrofitClient.getRetrofitInstance();
        ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
        Call<roommodel> call = apiAQQHome.getfund(ApartmentID);
           call.enqueue(new retrofit2.Callback<roommodel>() {
                @Override
                public void onResponse(Call<roommodel> call, retrofit2.Response<roommodel> response) {
                    if (response.isSuccessful()){
                        roommodel roommodel = response.body();


                        String money = response.body().getFund();
                        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                        String formattedMoney = formatter.format(Double.parseDouble(money));
                        sotien.setText(formattedMoney);
                    }
                }

                @Override
                public void onFailure(Call<roommodel> call, Throwable t) {

                }
            });






    }
}