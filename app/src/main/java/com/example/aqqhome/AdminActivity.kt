package com.example.aqqhome

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.aqqhome.fragment.CaidatFragment
import com.example.aqqhome.model.roommodel
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AdminActivity : AppCompatActivity() {

    private val qlcanho by lazy { findViewById<ConstraintLayout>(R.id.qlcanho) }
    private val money by lazy { findViewById<ConstraintLayout>(R.id.money) }
    private val quychungcu by lazy { findViewById<ConstraintLayout>(R.id.quychungcu) }
    private val nameinf by lazy { findViewById<TextView>(R.id.nameinf) }
    private val phoneinf by lazy { findViewById<TextView>(R.id.phoneinf) }
    private val nameapa by lazy { findViewById<TextView>(R.id.nameapa) }
    private val bottomNavigationView by lazy { findViewById<BottomNavigationView>(R.id.bottomNavigationView) }

    private val sharedPreferences by lazy { getSharedPreferences("UserInfo", MODE_PRIVATE) }
    private val sharedPreferencess by lazy { getSharedPreferences("RoomID", MODE_PRIVATE) }

    private val retrofit: Retrofit = RetrofitClient.getRetrofitInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        setupListeners()
        fetchApartmentName()
        populateUserInfo()
    }

    private fun setupListeners() {
        money.setOnClickListener { navigateTo(MoneyActivity::class.java) }
        quychungcu.setOnClickListener { navigateTo(QuyChungCuActivity::class.java) }
        qlcanho.setOnClickListener { navigateTo(QuanLyActivity::class.java) }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navigateTo(AdminActivity::class.java)
                    true
                }
                R.id.caidat -> loadFragment(CaidatFragment())
                // Uncomment these when you have these fragments
                // R.id.bangtin -> loadFragment(BangtinFragment())
                // R.id.thongbao -> loadFragment(ThongbaoFragment())
                else -> false
            }
        }
    }

    private fun navigateTo(activityClass: Class<*>) {
        startActivity(Intent(this, activityClass))
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
        return true
    }

    private fun fetchApartmentName() {
        val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
        val manager = sharedPreferences.getString("Manager", "") ?: ""
        apiAQQHome.getthongtin(manager).enqueue(object : Callback<roommodel> {
            override fun onResponse(call: Call<roommodel>, response: Response<roommodel>) {
                if (response.isSuccessful) {
                    nameapa.text = response.body()?.nameApartment
                }
            }

            override fun onFailure(call: Call<roommodel>, t: Throwable) {
                // Handle the error, maybe show a toast or a log
            }
        })
    }

    private fun populateUserInfo() {
        val name = sharedPreferences.getString("name", "")
        val phone = sharedPreferences.getString("phone", "")
        nameinf.text = name
        phoneinf.text = phone
    }
}
