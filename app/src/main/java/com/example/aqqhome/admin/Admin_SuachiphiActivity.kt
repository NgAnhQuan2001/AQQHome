package com.example.aqqhome.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aqqhome.databinding.ActivityAdminSuachiphiBinding
import com.example.aqqhome.model.feemodel
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import retrofit2.Call

class Admin_SuachiphiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminSuachiphiBinding
    private lateinit var apartmentID: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminSuachiphiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            finish()
        }
        val retrofit = RetrofitClient.getRetrofitInstance()
        val sharedpreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        apartmentID = sharedpreferences.getString("Manager", "") ?: ""

        val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
        val call = apiAQQHome.admingetchiphi(apartmentID)
        call.enqueue(object : retrofit2.Callback<feemodel> {
            override fun onResponse(call: Call<feemodel>, response: retrofit2.Response<feemodel>) {
                if (response.isSuccessful) {
                    val text = response.body()
                    text?.let {
                        if (it.isSuccess) {
                            binding.phiquanly.text = it.managementFee
                            binding.phirac.text = it.garbageFee
                        }
                    }
                }
            }

            override fun onFailure(call: Call<feemodel>, t: Throwable) {
                // Handle error case
            }
        })
    }
}
