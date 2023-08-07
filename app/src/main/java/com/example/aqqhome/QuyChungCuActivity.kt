package com.example.aqqhome

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aqqhome.model.roommodel
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Locale

class QuyChungCuActivity : AppCompatActivity() {

    private lateinit var sotien: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private val retrofit by lazy { RetrofitClient.getRetrofitInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quy_chung_cu)

        initViews()
        fetchFundData()
    }

    private fun initViews() {
        sotien = findViewById(R.id.sotien)

        val back: ImageView = findViewById(R.id.back)
        back.setOnClickListener { finish() }

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
    }

    private fun fetchFundData() {
        val apartmentID = sharedPreferences.getString("Manager", "") ?: return
        val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
        val call = apiAQQHome.getfund(apartmentID)

        call.enqueue(object : Callback<roommodel> {
            override fun onResponse(call: Call<roommodel>, response: Response<roommodel>) {
                if (response.isSuccessful) {
                    response.body()?.let { roomModel ->
                        val money = roomModel.fund
                        val formattedMoney = formatMoney(money)
                        sotien.text = formattedMoney
                    }
                }
            }

            override fun onFailure(call: Call<roommodel>, t: Throwable) {
                // Handle failure
            }
        })
    }
    private fun formatMoney(money: String): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
        return formatter.format(money.toDoubleOrNull() ?: 0.0)
    }
}
