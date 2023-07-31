package com.example.aqqhome.retrofit;

import com.example.aqqhome.model.ApiHistory;
import com.example.aqqhome.model.ApiResponse;
import com.example.aqqhome.model.bankmodel;
import com.example.aqqhome.model.feemodel;
import com.example.aqqhome.model.roommodel;
import com.example.aqqhome.model.roommodel2;
import com.example.aqqhome.model.servicesmodel;
import com.example.aqqhome.model.usermodel2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiAQQHome {
    @POST("dangki.php")
    @FormUrlEncoded
    Call<usermodel2> dangki(
            @Field("Email") String Email,
            @Field("Pass") String Pass,
            @Field("FullName") String FullName,
            @Field("PhoneNumber") String PhoneNumber,

            @Field("uid") String uid
    );


    @POST("getmoney.php")
    @FormUrlEncoded
    Call<usermodel2> getmoney(
            @Field("UserID") String UserID
    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Call<usermodel2> dangnhap(
            @Field("Email") String Email,
            @Field("Pass") String Pass
    );

    @POST("checkcode.php")
    @FormUrlEncoded
    Call<roommodel2> checkcode(
            @Field("Code") String Code
    );

    @POST("getbill.php")
    @FormUrlEncoded
    Call<roommodel2> getbill(
            @Field("RoomID") String RoomID
    );

    @POST("getthongtin.php")
    @FormUrlEncoded
    Call<roommodel> getthongtin(
            @Field("RoomID") String RoomID
    );

    @POST("themcanho.php")
    @FormUrlEncoded
    Call<roommodel2> themcanho(
            @Field("RoomName") String RoomName,
            @Field("NumberOfFloors") String NumberOfFloors,
            @Field("Area") String Area,
            @Field("ParkingCardNumber") String ParkingCardNumber,
            @Field("Code") String Code,
            @Field("ApartmentID") String ApartmentID
    );

    @POST("naptien.php")
    @FormUrlEncoded
    Call<bankmodel> naptien(
            @Field("UserID") String UserID,
            @Field("token") String token,
            @Field("Sotiennap") String Sotiennap,
            @Field("thoigiannap") String thoigiannap
    );

    @FormUrlEncoded
    @POST("gettoanha.php")
    Call<List<roommodel2>> gettoanha(@Field("ApartmentID") String apartmentID);

    @FormUrlEncoded
    @POST("gettoanha.php")
    Call<ApiResponse> gettoanhaa(@Field("ApartmentID") String apartmentID);

    @FormUrlEncoded
    @POST("getlichsunap.php")
    Call<ApiHistory> getlichsunap(@Field("UserID") String UserID);

    @FormUrlEncoded
    @POST("thanhtoan.php")
    Call<roommodel2> thanhtoan(
            @Field("RoomID") String RoomID,
            @Field("amount") String amount
    );
    @POST("gettenchungcu.php")
    @FormUrlEncoded
    Call<roommodel> gettenchungcu(
            @Field("ApartmentID") String ApartmentID
    );
    @POST("trangthaichiphi.php")
    @FormUrlEncoded
    Call<servicesmodel> trangthaichiphi(
            @Field("RoomID") String RoomID
    );
    @POST("capnhaptrangthai.php")
    @FormUrlEncoded
    Call<servicesmodel> capnhaptrangthai(
            @Field("RoomID") String RoomID,
            @Field("IsManagerServiceUsed") String IsManagerServiceUsed,
            @Field("IsGarbageServiceUsed") String IsGarbageServiceUsed

    );
    @POST("getchiphi.php")
    @FormUrlEncoded
    Call<feemodel> getchiphi(
            @Field("RoomID") String RoomID

    );

    @POST("getfund.php")
    @FormUrlEncoded
    Call<roommodel> getfund(
            @Field("ApartmentID") String ApartmentID

    );
    @POST("admingetchiphi.php")
    @FormUrlEncoded
    Call<feemodel> admingetchiphi(
            @Field("ApartmentID") String ApartmentID

    );

}


