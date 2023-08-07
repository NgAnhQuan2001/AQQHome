package com.example.aqqhome

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aqqhome.adapter.OnRoomClickListener
import com.example.aqqhome.adapter.RoomAdapter
import com.example.aqqhome.databinding.ActivityAdminXemthongtinBinding
import com.example.aqqhome.model.ApiResponse
import com.example.aqqhome.model.roommodel2
import com.example.aqqhome.model.servicesmodel
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Admin_XemthongtinActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAdminXemthongtinBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupUi()
        loadData()
    }

    private fun setupUi() {
        binding.back.setOnClickListener { finish() }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadData() {
        val apartmentID = getSharedPreferences("UserInfo", MODE_PRIVATE).getString("Manager", "")

        RetrofitClient.getRetrofitInstance().create(ApiAQQHome::class.java).gettoanhaa(apartmentID ?: "").enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                response.body()?.records?.let { rooms ->
                    val adapter = RoomAdapter(rooms) { room ->
                        showRoomDetailBottomSheet(room)
                    }
                    binding.recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Handle error
            }
        })
    }

    private fun showRoomDetailBottomSheet(room: roommodel2) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.roomdetail_bottomsheet, null)
        bottomSheetDialog.setContentView(bottomSheetView)

        with(bottomSheetView) {
            findViewById<TextView>(R.id.sophong).text = room.roomName
            findViewById<TextView>(R.id.sotang).text = room.numberOfFloors
            findViewById<TextView>(R.id.dientich).text = room.area
            findViewById<TextView>(R.id.sothexe).text = room.parkingCardNumber
            findViewById<TextView>(R.id.codethamgia).text = room.code
            findViewById<TextView>(R.id.tienno).text = room.debt
        }

        val api = RetrofitClient.getRetrofitInstance().create(ApiAQQHome::class.java)
        api.trangthaichiphi(room.roomID).enqueue(object : Callback<servicesmodel> {
            override fun onResponse(call: Call<servicesmodel>, response: Response<servicesmodel>) {
                response.body()?.let {
                    val switchButton = bottomSheetView.findViewById<SwitchCompat>(R.id.switchButton)
                    val switchButtonn = bottomSheetView.findViewById<SwitchCompat>(R.id.switchButtonn)

                    switchButton.isChecked = it.isManagerServiceUsed == "1"
                    switchButtonn.isChecked = it.isGarbageServiceUsed == "1"
                }
            }

            override fun onFailure(call: Call<servicesmodel>, t: Throwable) {
                // Handle error
            }
        })

        bottomSheetView.findViewById<Button>(R.id.trangthai).setOnClickListener {
            val switchButton = bottomSheetView.findViewById<SwitchCompat>(R.id.switchButton)
            val switchButtonn = bottomSheetView.findViewById<SwitchCompat>(R.id.switchButtonn)

            val isManagerServiceUsed = if (switchButton.isChecked) "1" else "0"
            val isGarbageServiceUsed = if (switchButtonn.isChecked) "1" else "0"

            api.capnhaptrangthai(room.roomID, isManagerServiceUsed, isGarbageServiceUsed).enqueue(object : Callback<servicesmodel> {
                override fun onResponse(call: Call<servicesmodel>, response: Response<servicesmodel>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@Admin_XemthongtinActivity, "Cập nhập trạng thái thành công", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@Admin_XemthongtinActivity, "Máy chủ không phản hồi xin vui lòng quay lại sau", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<servicesmodel>, t: Throwable) {
                    // Handle error
                }
            })
        }

        bottomSheetDialog.show()
    }
}
