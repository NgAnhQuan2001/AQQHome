package com.example.aqqhome

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aqqhome.databinding.ActivityAddroomBinding
import com.example.aqqhome.model.roommodel2
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.example.aqqhome.utils.customdialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddRoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddroomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddroomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        val retrofit = RetrofitClient.getRetrofitInstance()

        binding.apply {
            back.setOnClickListener { finish() }

            themcanhobtn.setOnClickListener {
                val roomName = tencanho.text.toString()
                val area = dientich.text.toString()
                val numberOfFloors = tang.text.toString()
                val parkingCardNumber = thexe.text.toString()
                val code = code.text.toString()

                if (listOf(roomName, area, numberOfFloors, parkingCardNumber, code).any { it.isEmpty() }) {
                    customdialog.showdialog("Lỗi", "Vui lòng nhập đầy đủ thông tin", "OK", this@AddRoomActivity)
                } else {
                    val apartmentID = sharedPreferences.getString("Manager", "")
                    val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
                    val call = apiAQQHome.themcanho(roomName, area, numberOfFloors, parkingCardNumber, code, apartmentID)

                    call.enqueue(object : Callback<roommodel2> {
                        override fun onResponse(call: Call<roommodel2>, response: Response<roommodel2>) {
                            val roommodel = response.body()

                            if (response.isSuccessful && roommodel?.isSuccess() == true) {
                                Toast.makeText(this@AddRoomActivity, "Thêm căn hộ thành công", Toast.LENGTH_SHORT).show()
                            } else {
                                customdialog.showdialog("Lỗi", "Thêm căn hộ thất bại", "OK", this@AddRoomActivity)
                            }
                        }

                        override fun onFailure(call: Call<roommodel2>, t: Throwable) {
                            customdialog.showdialog("Lỗi", "Lỗi kết nối: ${t.message}", "OK", this@AddRoomActivity)
                        }
                    })
                }
            }
        }
    }
}
