package com.example.aqqhome.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aqqhome.R;
import com.example.aqqhome.model.roommodel;
import com.example.aqqhome.model.roommodel2;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Retrofit;


public class HouseInfoFragment extends Fragment {

    private TextView toanha,diachi,sotang,hotline, tang, tencanho, dientich;

    private SharedPreferences sharedPreferences;
    private Retrofit retrofit;

    public HouseInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_info, container, false);

        //anhxa
        toanha = view.findViewById(R.id.toanha);
        diachi = view.findViewById(R.id.diachi);
        sotang = view.findViewById(R.id.sotang);
        hotline = view.findViewById(R.id.hotline);
        tencanho = view.findViewById(R.id.tencanho);
        dientich = view.findViewById(R.id.dientich);
        tang = view.findViewById(R.id.tang);

        //get sharedperces
        sharedPreferences = getActivity().getSharedPreferences("RoomID", MODE_PRIVATE);
        String roomid = sharedPreferences.getString("RoomID", "");

        retrofit = RetrofitClient.getRetrofitInstance();
        ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
        Call<roommodel> call = apiAQQHome.getthongtin(roomid);
        call.enqueue(new retrofit2.Callback<roommodel>() {
            @Override
            public void onResponse(Call<roommodel> call, retrofit2.Response<roommodel> response) {
                if (response.isSuccessful()){
                    toanha.setText(response.body().getNameApartment());
                    diachi.setText(response.body().getAddress());
                    hotline.setText(response.body().getManagerPhoneNumber());
                    sotang.setText(response.body().getNumberOfFloors());
                    tencanho.setText(response.body().getRoomName());
                    dientich.setText(response.body().getArea());
                }
            }

            @Override
            public void onFailure(Call<roommodel> call, Throwable t) {

            }
        });








        return view;
    }
}