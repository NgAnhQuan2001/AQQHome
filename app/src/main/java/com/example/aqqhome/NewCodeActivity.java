package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.example.aqqhome.model.roommodel2;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;
import com.example.aqqhome.utils.customdialog;

import retrofit2.Call;
import retrofit2.Retrofit;

public class NewCodeActivity extends AppCompatActivity {
    private EditText newcode;
    private Button newcodebtn,logoutbtn;
    private Retrofit retrofit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_code);
        sharedPreferences = getSharedPreferences("RoomID", MODE_PRIVATE);
        newcode = findViewById(R.id.code);
        newcodebtn = findViewById(R.id.codebtn);
        retrofit = RetrofitClient.getRetrofitInstance();

        newcodebtn.setOnClickListener(v -> {
            String Code = newcode.getText().toString();
            if (Code.isEmpty()) {
                customdialog.showdialog("Lỗi", "Vui lòng nhập mã xác nhận", "OK", this);
            } else {
                Checkcode(Code);
            }
        });
        logoutbtn = findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            Intent i = new Intent(NewCodeActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void Checkcode(String Code) {
        ApiAQQHome ApiAQQHome = retrofit.create(ApiAQQHome.class);
        Call<roommodel2> call = ApiAQQHome.checkcode(Code);
        call.enqueue(new retrofit2.Callback<roommodel2>() {
            @Override
            public void onResponse(Call<roommodel2> call, retrofit2.Response<roommodel2> response) {
                if (response.isSuccessful()) {
                    roommodel2 roommodel = response.body();
                    if (roommodel != null && roommodel.isSuccess()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("RoomID", roommodel.getRoomID());
                        editor.putString("RoomName", roommodel.getRoomName());
                        editor.putString("ApartmentID", roommodel.getApartmentID());

                        editor.apply();
                        customdialog.showdialog("Thông báo", "Mã xác nhận hợp lệ", "OK", NewCodeActivity.this);
                        new Handler().postDelayed(() -> {
                            Intent intent = new Intent(NewCodeActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }, 2500);



                    } else {
                        customdialog.showdialog("Lỗi", "Mã xác nhận không hợp lệ", "OK", NewCodeActivity.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<roommodel2> call, Throwable t) {
                customdialog.showdialog("Lỗi", "Lỗi kết nối", "OK", NewCodeActivity.this);
            }
        });
    }
}
