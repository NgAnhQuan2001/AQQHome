package com.example.aqqhome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aqqhome.adapter.NewAdapter
import com.example.aqqhome.model.ApiNewFeed
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.example.aqqhome.user.NewmesActivity
import com.example.aqqhome.utils.MyPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var addmes: ImageView
    private lateinit var back: ImageView
    private lateinit var recycler_view: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        addmes = findViewById(R.id.newMessageIcon)
        recycler_view = findViewById(R.id.recyclerView)
        retrofit = RetrofitClient.getRetrofitInstance()
        val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
        val ApartmentID = MyPref.get(this, "RoomID", "ApartmentID").toString()
        val call = apiAQQHome.getnewfeed(ApartmentID)
        call.enqueue(object : Callback<ApiNewFeed> {
            override fun onResponse(call: Call<ApiNewFeed>, response: Response<ApiNewFeed>) {
                if (response.isSuccessful) {
                    val apiNewFeed = response.body()
                    if (apiNewFeed?.isSuccess() == true) {
                        val newfeedList = apiNewFeed.getRecords()
                        val layoutManager = LinearLayoutManager(this@NewActivity)
                        recycler_view.layoutManager = layoutManager
                        var newfeedAdapter = NewAdapter(newfeedList)
                        recycler_view.adapter = newfeedAdapter
                    } else {
                        // Handle unsuccessful response or empty newfeed list
                    }
                } else {
                    // Handle API call failure
                }
            }

            override fun onFailure(call: Call<ApiNewFeed>, t: Throwable) {
                // Handle API call failure
            }
        })
        addmes.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(
                    this@NewActivity,
                    NewmesActivity::class.java

                )
            )
            finish()
        })
        back = findViewById(R.id.back)
        back.setOnClickListener(View.OnClickListener { v: View? -> finish() })


    }


}

