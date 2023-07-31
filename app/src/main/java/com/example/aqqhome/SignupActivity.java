package com.example.aqqhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aqqhome.model.usermodel2;
import com.example.aqqhome.retrofit.ApiAQQHome;
import com.example.aqqhome.retrofit.RetrofitClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {
    private EditText emaillo, passwordlo, repasswordlo, namelo, phonelo;
    private Button signuplo;
    private FirebaseAuth mAuth;

    private Retrofit retrofit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emaillo = findViewById(R.id.emaillo);
        passwordlo = findViewById(R.id.passwordlo);
        repasswordlo = findViewById(R.id.repasswordlo);
        namelo = findViewById(R.id.namelo);
        phonelo = findViewById(R.id.phonelo);
        signuplo = findViewById(R.id.signuplo);

        mAuth = FirebaseAuth.getInstance();
        retrofit = RetrofitClient.getRetrofitInstance();

        signuplo.setOnClickListener(v -> {
            String email = emaillo.getText().toString();
            String pwd = passwordlo.getText().toString();
            String repwd = repasswordlo.getText().toString();
            String name = namelo.getText().toString();
            String phone = phonelo.getText().toString();

            if (email.isEmpty()) {
                emaillo.setError("Vui lòng nhập email");
                emaillo.requestFocus();
            } else if (pwd.isEmpty()) {
                passwordlo.setError("Vui lòng nhập mật khẩu");
                passwordlo.requestFocus();
            } else if (repwd.isEmpty()) {
                repasswordlo.setError("Vui lòng nhập lại mật khẩu");
                repasswordlo.requestFocus();
            } else if (name.isEmpty()) {
                namelo.setError("Vui lòng nhập tên");
                namelo.requestFocus();
            } else if (phone.isEmpty()) {
                phonelo.setError("Vui lòng nhập số điện thoại");
                phonelo.requestFocus();
            } else if (pwd.length() < 6) {
                passwordlo.setError("Mật khẩu không được dưới 6 kí tự");
                passwordlo.requestFocus();
            } else if (repwd.length() < 6) {
                repasswordlo.setError("Mật khẩu không được dưới 6 kí tự");
                repasswordlo.requestFocus();
            } else if (pwd.equals(repwd)) {
                Random random = new Random();
                String uid = String.valueOf(random.nextInt(20));
                PostMysql(email, pwd, name, phone, uid);
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            } else {
                repasswordlo.setError("Mật khẩu không hợp lệ");
                repasswordlo.requestFocus();
            }
        });
    }

    private void PostMysql(String email, String pwd, String name, String phone, String uid) {
        ApiAQQHome ApiAQQHome = retrofit.create(ApiAQQHome.class);
        Call<usermodel2> call = ApiAQQHome.dangki(email, pwd, name, phone, uid);
        call.enqueue(new Callback<usermodel2>() {
            @Override
            public void onResponse(Call<usermodel2> call, Response<usermodel2> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Máy chủ không phản hồi xin vui lòng quay lại sau", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<usermodel2> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Vui lòng bật lại mạng", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
