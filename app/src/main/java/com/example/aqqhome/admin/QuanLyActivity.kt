package com.example.aqqhome.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.aqqhome.R

class QuanLyActivity : AppCompatActivity() {
    private lateinit var cardView: ImageView
    private lateinit var back: ImageView
    private lateinit var suachiphi: CardView
    private lateinit var xemthongtin: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quanly)
        cardView = findViewById(R.id.themtoanha)
        suachiphi = findViewById(R.id.suachiphi)
        xemthongtin = findViewById(R.id.xemthongtin)
        cardView.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(
                    this@QuanLyActivity,
                    AddRoomActivity::class.java
                )
            )
        })
        suachiphi.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(
                    this@QuanLyActivity,
                    Admin_SuachiphiActivity::class.java
                )
            )
        })
        xemthongtin.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(
                    this@QuanLyActivity,
                    Admin_XemthongtinActivity::class.java
                )
            )
        })
        back = findViewById(R.id.back)
        back.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(
                    this@QuanLyActivity,
                    AdminActivity::class.java
                )
            )
        })
    }
}