package com.example.aqqhome.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.view.View
import android.widget.ProgressBar
import com.example.aqqhome.NewActivity
import com.example.aqqhome.R
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.example.aqqhome.utils.MyPref
import com.example.aqqhome.model.newfeedmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewmesActivity : AppCompatActivity() {

    private lateinit var back: ImageView
    private lateinit var postEditText: EditText
    private lateinit var postButton: Button
    private lateinit var ApartmentID: String
    private lateinit var RoomName: String
    private lateinit var UserID: String
    private lateinit var Name: String
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newmes)

        back = findViewById(R.id.back)
        postEditText = findViewById(R.id.postEditText)
        postButton = findViewById(R.id.postButton)

        back.setOnClickListener { finish() }

        retrofit = RetrofitClient.getRetrofitInstance()

        postButton.setOnClickListener {
            val text = postEditText.text.toString()
            ApartmentID = MyPref.get(this, "RoomID", "ApartmentID").toString()
            RoomName = MyPref.get(this, "RoomID", "RoomName").toString()
            UserID = MyPref.get(this, "UserInfo", "UserID").toString()
            Name = MyPref.get(this, "UserInfo", "name").toString()

            if (text.isNotEmpty()) {
                val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
                val call = apiAQQHome.postbangtin(ApartmentID, RoomName, UserID, text,Name)

                call.enqueue(object : Callback<newfeedmodel> {
                    override fun onResponse(
                        call: Call<newfeedmodel>,
                        response: Response<newfeedmodel>
                    ) {
                        val apiNewFeed = response.body()

                        if (response.isSuccessful && apiNewFeed?.isSuccess() == true) {
                            Toast.makeText(this@NewmesActivity, "Đăng thành công", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(
                                Intent(
                                    this@NewmesActivity,
                                    NewActivity::class.java

                                )
                            )
                            finish()
                        } else {
                            Toast.makeText(this@NewmesActivity, "Lỗi khi đăng", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<newfeedmodel>, t: Throwable) {
                        Toast.makeText(this@NewmesActivity, "Lỗi mạng hoặc máy chủ", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            } else {
                Toast.makeText(this@NewmesActivity, "Bạn chưa nhập nội dung", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
