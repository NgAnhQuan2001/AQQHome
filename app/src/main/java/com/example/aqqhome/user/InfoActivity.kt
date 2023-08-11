package com.example.aqqhome.user

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.aqqhome.R
import com.example.aqqhome.utils.KeyboardUtils.hideKeyboard
import com.example.aqqhome.utils.MyPref

class InfoActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextApartment: EditText
    private lateinit var back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        initializeViews()
        loadUserData()
        hideKeyboard(this)
    }

    private fun initializeViews() {
        editTextName = findViewById(R.id.editTextName)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextApartment = findViewById(R.id.editTextApartment)
        back = findViewById(R.id.back)

        back.setOnClickListener { finish() }
    }

    private fun loadUserData() {

        editTextName.setText(MyPref.get(this, "UserInfo", "name"))
        editTextPhone.setText(MyPref.get(this, "UserInfo", "phone"))
        editTextEmail.setText(MyPref.get(this, "UserInfo", "email"))
        editTextApartment.setText(MyPref.get(this, "RoomID", "RoomName"))
    }
}
