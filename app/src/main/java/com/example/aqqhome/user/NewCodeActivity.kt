package com.example.aqqhome.user

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import com.example.aqqhome.R
import com.example.aqqhome.auth.LoginActivity
import com.example.aqqhome.model.roommodel2
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.example.aqqhome.utils.KeyboardUtils.hideKeyboard

import com.example.aqqhome.utils.customdialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewCodeActivity : AppCompatActivity() {

    private lateinit var newCode: EditText
    private lateinit var newCodeBtn: Button
    private lateinit var logoutBtn: Button
    private lateinit var retrofit: Retrofit
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_code)

        sharedPreferences = getSharedPreferences("RoomID", MODE_PRIVATE)

        newCode = findViewById(R.id.code)
        newCodeBtn = findViewById(R.id.codebtn)
        retrofit = RetrofitClient.getRetrofitInstance()

        newCodeBtn.setOnClickListener {
            val code = newCode.text.toString()
            if (code.isEmpty()) {
                customdialog.showdialog("Lỗi", "Vui lòng nhập mã xác nhận", "OK", this)
            } else {
                checkCode(code)
            }
        }
        logoutBtn = findViewById(R.id.logoutbtn)
        logoutBtn.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkCode(code: String) {
        val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
        val call = apiAQQHome.checkcode(code)

        hideKeyboard(this)





        call.enqueue(object : Callback<roommodel2> {
            override fun onResponse(call: Call<roommodel2>, response: Response<roommodel2>) {
                response.body()?.let {
                    if (it.isSuccess()) {
                        with(sharedPreferences.edit()) {
                            putString("RoomID", it.getRoomID())
                            putString("RoomName", it.getRoomName())
                            putString("ApartmentID", it.getApartmentID())
                            apply()
                        }
                        customdialog.showdialog("Thông báo", "Mã xác nhận hợp lệ", "OK", this@NewCodeActivity)

                        Handler().postDelayed({
                            val intent = Intent(this@NewCodeActivity, MainActivity::class.java).apply {
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                            startActivity(intent)
                            finish()
                        }, 2500)
                    } else {
                        customdialog.showdialog("Lỗi", "Mã xác nhận không hợp lệ", "OK", this@NewCodeActivity)
                    }
                }
            }

            override fun onFailure(call: Call<roommodel2>, t: Throwable) {
                customdialog.showdialog("Lỗi", "Lỗi kết nối", "OK", this@NewCodeActivity)
            }
        })
    }


}
