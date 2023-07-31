package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.aqqhome.adapter.OnRoomClickListener;
import com.example.aqqhome.adapter.RoomAdapter;
import com.example.aqqhome.model.ApiResponse;
import com.example.aqqhome.model.roommodel2;
import com.example.aqqhome.model.servicesmodel;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Admin_XemthongtinActivity extends AppCompatActivity {
    private ImageView back;
    private RecyclerView recycler_view;
    private List<roommodel2> roommodel2List;

    private SharedPreferences sharedPreferences;
    private Retrofit retrofit;
    private String ApartmentID, roomid;

    private SwitchCompat switchButton, switchButtonn;

    private View bottomSheetView;

    private boolean switchState, switchStatee;

    private Button trangthai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_xemthongtin);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
        recycler_view = findViewById(R.id.recycler_view);
        roommodel2List = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        ApartmentID = sharedPreferences.getString("Manager", "");
        retrofit = RetrofitClient.getRetrofitInstance();
        ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
        Call<ApiResponse> call = apiAQQHome.gettoanhaa(ApartmentID);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    List<roommodel2> rooms = apiResponse.getRecords();

                    // Set up the RecyclerView
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    RoomAdapter adapter = new RoomAdapter(rooms, new OnRoomClickListener() {
                        @Override
                        public void onRoomClick(roommodel2 room) {
                            // Create a new BottomSheetDialog
                            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Admin_XemthongtinActivity.this);
                            // Inflate the layout for this BottomSheetDialog
                            bottomSheetView = LayoutInflater.from(Admin_XemthongtinActivity.this).inflate(R.layout.roomdetail_bottomsheet, null);
                            bottomSheetDialog.setContentView(bottomSheetView);
                            // Update the TextViews in the BottomSheet with the Room information
                            TextView roomIdView = bottomSheetView.findViewById(R.id.sophong);
                            TextView roomsotang = bottomSheetView.findViewById(R.id.sotang);
                            TextView roomdientich = bottomSheetView.findViewById(R.id.dientich);
                            TextView roomsothexe = bottomSheetView.findViewById(R.id.sothexe);
                            TextView roomcodethamgia = bottomSheetView.findViewById(R.id.codethamgia);
                            TextView roomtienno = bottomSheetView.findViewById(R.id.tienno);
                            roomIdView.setText(room.getRoomName());
                            roomsotang.setText(room.getNumberOfFloors());
                            roomdientich.setText(room.getArea());
                            roomsothexe.setText(room.getParkingCardNumber());
                            roomcodethamgia.setText(room.getCode());
                            roomtienno.setText(room.getDebt());
                            // Show the BottomSheetDialog
                            roomid = room.getRoomID();
                            ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
                            Call<servicesmodel> call = apiAQQHome.trangthaichiphi(roomid);
                            call.enqueue(new Callback<servicesmodel>() {
                                @Override
                                public void onResponse(Call<servicesmodel> call, Response<servicesmodel> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        servicesmodel servicesmodel = response.body();
                                        String format = servicesmodel.getIsManagerServiceUsed();
                                        String formatt = servicesmodel.getIsGarbageServiceUsed();

                                        // Bỏ qua nếu không có dữ liệu
                                        if (format == null || formatt == null) {
                                            return;
                                        }

                                        if (format.equals("1")) {
                                            switchState = true;
                                        } else {
                                            switchState = false;
                                        }
                                        if (formatt.equals("1")) {
                                            switchStatee = true;
                                        } else {
                                            switchStatee = false;
                                        }

                                        switchButton = bottomSheetView.findViewById(R.id.switchButton);
                                        switchButtonn = bottomSheetView.findViewById(R.id.switchButtonn);
                                        switchButton.setChecked(switchState);

                                        switchButtonn.setChecked(switchStatee);
                                    }
                                }

                                @Override
                                public void onFailure(Call<servicesmodel> call, Throwable t) {
                                    // Có thể thêm log ở đây để debug lỗi
                                }
                            });
                            trangthai = bottomSheetView.findViewById(R.id.trangthai);
                            trangthai.setOnClickListener(view -> {
                                        switchButton = bottomSheetView.findViewById(R.id.switchButton);
                                        switchButtonn = bottomSheetView.findViewById(R.id.switchButtonn);
                                        boolean currentSwitchState = switchButton.isChecked();
                                        boolean currentSwitchStatee = switchButtonn.isChecked();
                                        String isManagerServiceUsed = currentSwitchState ? "1" : "0";
                                        String isGarbageServiceUsed = currentSwitchStatee ? "1" : "0";
                                        ApiAQQHome apiAQQHome1 = retrofit.create(ApiAQQHome.class);
                                        Call<servicesmodel> call1 = apiAQQHome1.capnhaptrangthai(roomid, isManagerServiceUsed, isGarbageServiceUsed);
                                        call1.enqueue(new Callback<servicesmodel>() {
                                            @Override
                                            public void onResponse(Call<servicesmodel> call, Response<servicesmodel> response) {
                                                {
                                                    if (response.isSuccessful()) {
                                                        Toast.makeText(Admin_XemthongtinActivity.this, "Cập nhập trạng thái thành công", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(Admin_XemthongtinActivity.this, "Máy chủ không phản hồi xin vui lòng quay lại sau", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<servicesmodel> call, Throwable t) {
                                            }
                                        });
                                    }
                            );


                            bottomSheetDialog.show();
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("ROOM_INFO", "Error: " + t.getMessage());
            }
        });


    }
}
