package com.example.aqqhome

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aqqhome.model.usermodel2
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SignupActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var repasswordInput: EditText
    private lateinit var nameInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var signUpButton: Button
    private val retrofit by lazy { RetrofitClient.getRetrofitInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        initializeViews()
        setupSignUpButton()
    }

    private fun initializeViews() {
        emailInput = findViewById(R.id.emaillo)
        passwordInput = findViewById(R.id.passwordlo)
        repasswordInput = findViewById(R.id.repasswordlo)
        nameInput = findViewById(R.id.namelo)
        phoneInput = findViewById(R.id.phonelo)
        signUpButton = findViewById(R.id.signuplo)
    }

    private fun setupSignUpButton() {
        signUpButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val repassword = repasswordInput.text.toString()
            val name = nameInput.text.toString()
            val phone = phoneInput.text.toString()

            when {
                email.isBlank() -> emailInput.setErrorAndFocus("Vui lòng nhập email")
                password.isBlank() -> passwordInput.setErrorAndFocus("Vui lòng nhập mật khẩu")
                repassword.isBlank() -> repasswordInput.setErrorAndFocus("Vui lòng nhập lại mật khẩu")
                name.isBlank() -> nameInput.setErrorAndFocus("Vui lòng nhập tên")
                phone.isBlank() -> phoneInput.setErrorAndFocus("Vui lòng nhập số điện thoại")
                password.length < 6 -> passwordInput.setErrorAndFocus("Mật khẩu không được dưới 6 kí tự")
                repassword.length < 6 -> repasswordInput.setErrorAndFocus("Mật khẩu không được dưới 6 kí tự")
                password != repassword -> repasswordInput.setErrorAndFocus("Mật khẩu không hợp lệ")
                else -> {
                    val uid = UUID.randomUUID().toString()
                    postToMysql(email, password, name, phone, uid)
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }

    private fun EditText.setErrorAndFocus(errorMsg: String) {
        this.error = errorMsg
        this.requestFocus()
    }

    private fun postToMysql(email: String, password: String, name: String, phone: String, uid: String) {
        val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
        val call = apiAQQHome.dangki(email, password, name, phone, uid)

        call.enqueue(object : Callback<usermodel2> {
            override fun onResponse(call: Call<usermodel2>, response: Response<usermodel2>) {
                val message = if (response.isSuccessful) {
                    "Đăng ký thành công"
                } else {
                    "Máy chủ không phản hồi xin vui lòng quay lại sau"
                }
                Toast.makeText(this@SignupActivity, message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<usermodel2>, t: Throwable) {
                Toast.makeText(this@SignupActivity, "Vui lòng bật lại mạng", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
