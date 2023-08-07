package com.example.aqqhome

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aqqhome.databinding.ActivityBillBinding
import com.example.aqqhome.model.feemodel
import com.example.aqqhome.model.roommodel2
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.NumberFormat
import java.util.*

class BillActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBillBinding
    private lateinit var sonoo: String
    private lateinit var sharedpreferences: SharedPreferences
    private lateinit var retrofit: Retrofit
    private lateinit var roomid: String
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener { finish() }
        binding.pay.setOnClickListener {
            val intent = Intent(this@BillActivity, ThanhToanActivity::class.java).apply {
                putExtra("sono", sonoo)
                putExtra("Room", roomid)
            }
            startActivity(intent)
            finish()
        }

        sharedpreferences = getSharedPreferences("RoomID", MODE_PRIVATE)
        roomid = sharedpreferences.getString("RoomID", "Guest") ?: "Guest"

        retrofit = RetrofitClient.getRetrofitInstance()
        val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
        val call = apiAQQHome.getbill(roomid)
        call.enqueue(object : Callback<roommodel2> {
            override fun onResponse(call: Call<roommodel2>, response: Response<roommodel2>) {
                if (response.isSuccessful) {
                    val roommodel = response.body()
                    if (roommodel != null && roommodel.isSuccess()) {
                        sonoo = roommodel.getDebt()
                        val format = NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(sonoo.toDouble())
                        binding.sono.text = format
                        binding.tongtien.text = format
                    }
                }
            }

            override fun onFailure(call: Call<roommodel2>, t: Throwable) {}
        })

        binding.sono.setOnClickListener {
            bottomSheetDialog = BottomSheetDialog(this@BillActivity)
            bottomSheetDialog.setContentView(R.layout.chitiet)

            val callFee = apiAQQHome.getchiphi(roomid)
            callFee.enqueue(object : Callback<feemodel> {
                override fun onResponse(call: Call<feemodel>, response: Response<feemodel>) {
                    if (response.isSuccessful) {
                        val text = response.body()
                        if (text != null && text.isSuccess()) {
                            bottomSheetDialog.findViewById<TextView>(R.id.phiquanly)?.text = text.getManagementFee()
                            bottomSheetDialog.findViewById<TextView>(R.id.phirac)?.text = text.getGarbageFee()
                        }
                    }
                }

                override fun onFailure(call: Call<feemodel>, t: Throwable) {}
            })

            bottomSheetDialog.show()
        }
    }
}
