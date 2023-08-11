package com.example.aqqhome.payment

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aqqhome.R
import com.example.aqqhome.adapter.HistoryAdapter
import com.example.aqqhome.model.ApiHistory
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LichsunapActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var UserID: String
    private lateinit var recycler_view: RecyclerView
    private lateinit var retrofit: Retrofit
    private lateinit var back: ImageView
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lichsunap)

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        UserID = sharedPreferences.getString("UserID", "").orEmpty()
        back = findViewById(R.id.back)
        retrofit = RetrofitClient.getRetrofitInstance()

        val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
        val call = apiAQQHome.getlichsunap(UserID)

        call.enqueue(object : Callback<ApiHistory> {
            override fun onResponse(call: Call<ApiHistory>, response: Response<ApiHistory>) {
                if (response.isSuccessful) {
                    val apiHistory = response.body()
                    if (apiHistory?.isSuccess() == true) {
                        val historyList = apiHistory.getRecords()

                        // Set up RecyclerView
                        recycler_view = findViewById(R.id.recyclerview)
                        val layoutManager = LinearLayoutManager(this@LichsunapActivity)
                        recycler_view.layoutManager = layoutManager
                        historyAdapter = HistoryAdapter(historyList)
                        recycler_view.adapter = historyAdapter
                    } else {
                        // Handle unsuccessful response or empty history list
                    }
                } else {
                    // Handle API call failure
                }
            }

            override fun onFailure(call: Call<ApiHistory>, t: Throwable) {
                // Handle network failure or error
            }
        })

        back.setOnClickListener { finish() }
    }
}
