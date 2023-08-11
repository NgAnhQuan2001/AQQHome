package com.example.aqqhome.user

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.aqqhome.R
import com.example.aqqhome.admin.AdminActivity
import com.example.aqqhome.auth.LoginActivity

class IntroActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencess: SharedPreferences


    companion object {
        private const val SPLASH_TIME_OUT = 5000L // Thời gian chờ là 5 giây
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        var type:String? = sharedPreferences.getString("Type", "")
        sharedPreferencess = getSharedPreferences("RoomID", MODE_PRIVATE)

        val progressDialog = ProgressDialog(this).apply {
            setMessage("Xin vui lòng chờ...")
            show()
        }
        Handler().postDelayed({
            progressDialog.dismiss()
            val keys = sharedPreferences.all
            val keyss = sharedPreferencess.all

            when {
                keys.isEmpty() -> {
                    startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
                    finish()
                }
                keyss.isEmpty() && type != "1" -> {
                    startActivity(Intent(this@IntroActivity, NewCodeActivity::class.java))
                    finish()
                }
                keys.isNotEmpty() && type == "1" -> {
                    startActivity(Intent(this@IntroActivity, AdminActivity::class.java))
                    finish()
                }
                else -> {
                    startActivity(Intent(this@IntroActivity, MainActivity::class.java))
                    finish()
                }
            }
        }, SPLASH_TIME_OUT)
    }
}
