package com.example.aqqhome

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.aqqhome.databinding.ActivityMainBinding
import com.example.aqqhome.fragment.CaidatFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencess: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.themcanho.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(R.layout.themcanho_bottomsheet_layout)
            val btn_doi: Button? = bottomSheetDialog.findViewById(R.id.btn_doi)
            btn_doi?.setOnClickListener {
                startActivity(Intent(this, NewCodeActivity::class.java))
            }
            bottomSheetDialog.show()
        }

        val imageList = arrayListOf(
            SlideModel("https://bdsweb.com.vn/upload_images/images/bbds/banner-bat-dong-san-01.jpg", null, null),
            SlideModel("https://megaon.vn/wp-content/uploads/2020/03/QT-web-24.03.png", null, null),
            SlideModel("https://intphcm.com/data/upload/thong-tin-poster-bat-dong-san.jpg", null, null)
        )
        binding.imageSlider.setImageList(imageList)

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        sharedPreferencess = getSharedPreferences("RoomID", MODE_PRIVATE)
        val phone = sharedPreferences.getString("phone", "Guest")
        val userName = sharedPreferences.getString("name", "Guest")
        val sophong = sharedPreferencess.getString("RoomName", "Guest")

        binding.nameinf.text = userName
        binding.phoneinf.text = phone
        binding.sophonginf.text = sophong

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.caidat -> {
                    loadFragment(CaidatFragment())
                    true
                }
                else -> false
            }
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.tienich.setOnClickListener { startActivity(Intent(this, LoadActivity::class.java)) }
        binding.shop.setOnClickListener { startActivity(Intent(this, LoadActivity::class.java)) }
        binding.gopy.setOnClickListener { startActivity(Intent(this, LoadActivity::class.java)) }
        binding.hotline.setOnClickListener { startActivity(Intent(this, LoadActivity::class.java)) }
        binding.credit.setOnClickListener { startActivity(Intent(this, MoneyActivity::class.java)) }
        binding.thongtin.setOnClickListener { startActivity(Intent(this, InfoActivity::class.java)) }
        binding.bill.setOnClickListener { startActivity(Intent(this, BillActivity::class.java)) }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
        return true
    }
}
