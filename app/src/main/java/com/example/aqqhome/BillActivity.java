package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aqqhome.model.feemodel;
import com.example.aqqhome.model.roommodel2;
import com.example.aqqhome.model.servicesmodel;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;

public class BillActivity extends AppCompatActivity {
    private TextView sono,tongtien;
    private Button pay;

    private ImageView back;
    private String sonoo;
    private SharedPreferences sharedpreferences;

    private Retrofit retrofit;
    private String roomid;

    private BottomSheetDialog bottomSheetDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        back = findViewById(R.id.back);
        sono = findViewById(R.id.sono);
        tongtien = findViewById(R.id.tongtien);
        pay = findViewById(R.id.pay);

        //get back
        back.setOnClickListener(v -> {
            finish();


        });
        pay.setOnClickListener(v -> {
            Intent intent = new Intent(BillActivity.this, ThanhToanActivity.class);
            intent.putExtra("sono", sonoo);
            intent.putExtra("Room", roomid);
            startActivity(intent);
            finish();

        });


        //get roomid
        sharedpreferences = getSharedPreferences("RoomID", MODE_PRIVATE);
        roomid = sharedpreferences.getString("RoomID", "Guest");

        //retrofit
        retrofit = RetrofitClient.getRetrofitInstance();
        ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
        Call<roommodel2> call = apiAQQHome.getbill(roomid);
        call.enqueue(new retrofit2.Callback<roommodel2>() {
            @Override
            public void onResponse(Call<roommodel2> call, retrofit2.Response<roommodel2> response) {
                if (response.isSuccessful()) {
                    roommodel2 roommodel = response.body();
                    if (roommodel != null && roommodel.isSuccess()) {
                        sonoo = roommodel.getDebt();
                        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                        String format = formatter.format(Double.parseDouble(sonoo));
                        sono.setText(format);

                        tongtien.setText(format);

                    }
                }
            }

            @Override
            public void onFailure(Call<roommodel2> call, Throwable t) {

            }
        });
        sono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Bottom Sheet Dialog
               bottomSheetDialog = new BottomSheetDialog(BillActivity.this);
                // Sử dụng layout đã tạo
                bottomSheetDialog.setContentView(R.layout.chitiet);
                ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
                Call<feemodel> call = apiAQQHome.getchiphi(roomid);
                call.enqueue(new retrofit2.Callback<feemodel>() {
                    @Override
                    public void onResponse(Call<feemodel> call, retrofit2.Response<feemodel> response) {
                        if (response.isSuccessful()) {
                            feemodel text = response.body();
                            if (text != null && text.isSuccess()) {
                                String a = text.getManagementFee();
                                String b = text.getGarbageFee();
                                TextView phiquanly = bottomSheetDialog.findViewById(R.id.phiquanly);
                                TextView phirac = bottomSheetDialog.findViewById(R.id.phirac);
                                phiquanly.setText(a);
                                phirac.setText(b);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<feemodel> call, Throwable t) {

                    }
                });
                bottomSheetDialog.show();



            }
        });













    }
}