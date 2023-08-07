package com.example.aqqhome

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.aqqhome.model.usermodel2
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.example.aqqhome.utils.customdialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {
    private lateinit var emailid: EditText
    private lateinit var passwordid: EditText
    private lateinit var SignInbtn: Button
    private lateinit var Signupbtn: Button
    private lateinit var admin: AppCompatButton
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencess: SharedPreferences
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailid = findViewById(R.id.emaillo)
        passwordid = findViewById(R.id.passwordlo)
        SignInbtn = findViewById(R.id.SignInbtn)
        Signupbtn = findViewById(R.id.Signupbtn)

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        sharedPreferencess = getSharedPreferences("RoomID", MODE_PRIVATE)
        retrofit = RetrofitClient.getRetrofitInstance()

        SignInbtn.setOnClickListener {
            val email = emailid.text.toString()
            val pwd = passwordid.text.toString()

            when {
                email.isEmpty() && pwd.isEmpty() -> {
                    customdialog.showdialog("Lỗi", "Vui lòng nhập tài khoản và mật khẩu", "OK", this)
                }
                email.isEmpty() -> {
                    customdialog.showdialog("Lỗi", "Vui lòng nhập tài khoản", "OK", this)
                    emailid.requestFocus()
                }
                pwd.isEmpty() -> {
                    customdialog.showdialog("Lỗi", "Vui lòng nhập mật khẩu", "OK", this)
                    passwordid.requestFocus()
                }
                else -> {
                    val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
                    val call = apiAQQHome.dangnhap(email, pwd)
                    call.enqueue(object : Callback<usermodel2> {
                        override fun onResponse(call: Call<usermodel2>, response: Response<usermodel2>) {
                            val usermodel = response.body()
                            if (response.isSuccessful && usermodel?.isSuccess == true) {
                                Toast.makeText(this@LoginActivity, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                                with(sharedPreferences.edit()) {
                                    putString("email", usermodel.email)
                                    putString("name", usermodel.fullName)
                                    putString("phone", usermodel.phoneNumber)
                                    putString("Type", usermodel.type)
                                    putString("Manager", usermodel.manager)
                                    putString("UserID", usermodel.userID)
                                    apply()
                                }
                                val intent = when (usermodel.type) {
                                    "1" -> Intent(this@LoginActivity, AdminActivity::class.java)
                                    else -> Intent(this@LoginActivity, NewCodeActivity::class.java)
                                }
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@LoginActivity, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<usermodel2>, t: Throwable) {
                            Toast.makeText(this@LoginActivity, "Lỗi kết nối: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }

        Signupbtn.setOnClickListener {
            val i = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(i)
        }
    }
}
