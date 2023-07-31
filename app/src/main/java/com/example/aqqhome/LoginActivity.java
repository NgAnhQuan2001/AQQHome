package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aqqhome.model.usermodel2;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;
import com.example.aqqhome.utils.customdialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText emailid, passwordid;
    private Button SignInbtn, Signupbtn;
    private AppCompatButton admin;
    private SharedPreferences sharedPreferences,sharedPreferencess;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailid = findViewById(R.id.emaillo);
        passwordid = findViewById(R.id.passwordlo);
        SignInbtn = findViewById(R.id.SignInbtn);
        Signupbtn = findViewById(R.id.Signupbtn);

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        sharedPreferencess = getSharedPreferences("RoomID", MODE_PRIVATE);
        retrofit = RetrofitClient.getRetrofitInstance();

        SignInbtn.setOnClickListener(v -> {
            String email = emailid.getText().toString();
            String pwd = passwordid.getText().toString();

            if (email.isEmpty() && pwd.isEmpty()) {
                customdialog.showdialog("Lỗi", "Vui lòng nhập tài khoản và mật khẩu", "OK", this);
            } else if (email.isEmpty()) {
                customdialog.showdialog("Lỗi", "Vui lòng nhập tài khoản", "OK", this);
                emailid.requestFocus();
            } else if (pwd.isEmpty()) {
                customdialog.showdialog("Lỗi", "Vui lòng nhập mật khẩu", "OK", this);
                passwordid.requestFocus();
            } else {
                ApiAQQHome apiAQQHome = retrofit.create(ApiAQQHome.class);
                Call<usermodel2> call = apiAQQHome.dangnhap(email, pwd);
                call.enqueue(new Callback<usermodel2>() {
                    @Override
                    public void onResponse(Call<usermodel2> call, Response<usermodel2> response) {
                        if (response.isSuccessful()) {
                            usermodel2 usermodel = response.body();
                            if (usermodel != null && usermodel.isSuccess()) {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", usermodel.getEmail());
                                editor.putString("name", usermodel.getFullName());
                                editor.putString("phone", usermodel.getPhoneNumber());
                                editor.putString("Type", usermodel.getType());
                                editor.putString("Manager", usermodel.getManager());
                                editor.putString("UserID", usermodel.getUserID());
                                editor.commit();
                                String type = usermodel.getType();
                                if (type.equals("1")) {
                                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, NewCodeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                // Đăng nhập thất bại
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Lỗi kết nối hoặc lỗi API
                            Toast.makeText(LoginActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<usermodel2> call, Throwable t) {
                        // Lỗi kết nối
                        Toast.makeText(LoginActivity.this, "Lỗi kết nối"+ t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Signupbtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(i);
        });
    }
}
