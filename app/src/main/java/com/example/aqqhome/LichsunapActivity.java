package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aqqhome.adapter.HistoryAdapter;
import com.example.aqqhome.adapter.OnRoomClickListener;
import com.example.aqqhome.adapter.RoomAdapter;
import com.example.aqqhome.model.ApiHistory;
import com.example.aqqhome.model.ApiResponse;
import com.example.aqqhome.model.bankmodel;
import com.example.aqqhome.model.roommodel2;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LichsunapActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String UserID;

    private ImageView back;
    private RecyclerView recycler_view;
    private Retrofit retrofit;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsunap);
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        UserID = sharedPreferences.getString("UserID", "");
        back = findViewById(R.id.back);
        retrofit = RetrofitClient.getRetrofitInstance();
        ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
        Call<ApiHistory> call = apiAQQHome.getlichsunap(UserID);
        call.enqueue(new Callback<ApiHistory>() {
            @Override
            public void onResponse(Call<ApiHistory> call, Response<ApiHistory> response) {
                if (response.isSuccessful()) {
                    ApiHistory apiHistory = response.body();
                    if ( apiHistory.isSuccess()) {
                        List<bankmodel> historyList = apiHistory.getRecords();

                        // Set up RecyclerView
                        recycler_view = findViewById(R.id.recyclerview);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(LichsunapActivity.this);
                        recycler_view.setLayoutManager(layoutManager);
                        historyAdapter = new HistoryAdapter(historyList);
                        recycler_view.setAdapter(historyAdapter);
                    } else {
                        // Handle unsuccessful response or empty history list
                    }
                } else {
                    // Handle API call failure
                }
            }

            @Override
            public void onFailure(Call<ApiHistory> call, Throwable t) {
                // Handle network failure or error
            }
        });

        back.setOnClickListener(v -> finish());








    }
}