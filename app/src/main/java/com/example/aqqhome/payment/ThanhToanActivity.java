package com.example.aqqhome.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aqqhome.R;
import com.example.aqqhome.Zalopay.AppInfo;
import com.example.aqqhome.Zalopay.CreateOrder;
import com.example.aqqhome.model.roommodel2;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class ThanhToanActivity extends AppCompatActivity {
    private ImageView back;
    private Button continue_button;
    private String RoomID;
    private SharedPreferences sharedPreferences;

    private String amount;

    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        back = findViewById(R.id.back);
        retrofit = RetrofitClient.getRetrofitInstance();
        continue_button = findViewById(R.id.continue_button);
        back.setOnClickListener(v -> {
            finish();
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ZaloPaySDK.init(AppInfo.APP_ID, Environment.SANDBOX);

        Intent intent = getIntent();
        String receivedData = intent.getStringExtra("sono");
        RoomID = intent.getStringExtra("Room");
        amount = receivedData;

        // Hiển thị dữ liệu lên TextView
        TextView tong_tien = findViewById(R.id.tong_tien);
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formatt = format.format(Double.parseDouble(receivedData));

        tong_tien.setText(formatt);

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(amount);
                    String code = data.getString("returncode");

                    if (code.equals("1")) {
                        String tokenn = data.getString("zptranstoken");

                        ZaloPaySDK.getInstance().payOrder(ThanhToanActivity.this, tokenn, "demozpdkk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                                ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
                                Call<roommodel2> call = apiAQQHome.thanhtoan(RoomID,amount);
                                call.enqueue(new retrofit2.Callback<roommodel2>() {
                                    @Override
                                    public void onResponse(Call<roommodel2> call, retrofit2.Response<roommodel2> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(ThanhToanActivity.this, "Nạp tiền thành công", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ThanhToanActivity.this, "Nạp tiền thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<roommodel2> call, Throwable t) {
                                        Toast.makeText(ThanhToanActivity.this, "Phương thức thanh toán bị lỗi, vui lòng quay lại sau " +t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }

                            @Override
                            public void onPaymentCanceled(String zpTransToken, String appTransID) {
                                Toast.makeText(ThanhToanActivity.this, "Thanh toán bị hủy", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                                Toast.makeText(ThanhToanActivity.this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}