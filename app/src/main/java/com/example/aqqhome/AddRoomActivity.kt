package com.example.aqqhome

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aqqhome.model.roommodel2
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.example.aqqhome.utils.customdialog
import com.example.aqqhome.databinding.ActivityAddroomBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AddRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddroomBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddroomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = RetrofitClient.getRetrofitInstance()
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)

        binding.back.setOnClickListener {
            finish()
        }

        binding.themcanhobtn.setOnClickListener {
            val RoomName = binding.tencanho.text.toString()
            val Area = binding.dientich.text.toString()
            val NumberOfFloors = binding.tang.text.toString()
            val ParkingCardNumber = binding.thexe.text.toString()
            val Code = binding.code.text.toString()

            if (RoomName.isEmpty() || Area.isEmpty() || NumberOfFloors.isEmpty() || ParkingCardNumber.isEmpty() || Code.isEmpty()) {
                customdialog.showdialog("Lỗi", "Vui lòng nhập đầy đủ thông tin", "OK", this)
            } else {
                val ApartmentID = sharedPreferences.getString("Manager", "")
                val ApiAQQHome = retrofit.create(ApiAQQHome::class.java)
                val call: Call<roommodel2> = ApiAQQHome.themcanho(RoomName, Area, NumberOfFloors, ParkingCardNumber, Code,ApartmentID)
                call.enqueue(object : Callback<roommodel2> {
                    override fun onResponse(call: Call<roommodel2>, response: Response<roommodel2>) {
                        if (response.isSuccessful) {
                            val roommodel = response.body()
                            if (roommodel != null && roommodel.isSuccess()) {
                                Toast.makeText(this@AddRoomActivity, "Thêm căn hộ thành công", Toast.LENGTH_SHORT).show()
                            } else {
                                customdialog.showdialog("Lỗi", "Thêm căn hộ thất bại", "OK", this@AddRoomActivity)
                            }
                        }
                    }

                    override fun onFailure(call: Call<roommodel2>, t: Throwable) {
                        customdialog.showdialog("Lỗi", "Lỗi kết nối" + t.message, "OK", this@AddRoomActivity)
                    }
                })
            }
        }
    }
}