package com.example.aqqhome.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aqqhome.R
import com.example.aqqhome.adapter.NewAdapter
import com.example.aqqhome.model.ApiNewFeed
import com.example.aqqhome.retrofit.ApiAQQHome
import com.example.aqqhome.retrofit.RetrofitClient
import com.example.aqqhome.user.NewmesActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * A simple [Fragment] subclass.
 * Use the [BangtinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BangtinFragment : Fragment() {

    private lateinit var retrofit: Retrofit
    private lateinit var addmes: ImageView
    private lateinit var recycler_view: RecyclerView
    private val ApartmentID = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_bangtin, container, false)

        addmes = rootView.findViewById(R.id.newMessageIcon)
        recycler_view = rootView.findViewById(R.id.recyclerView)
        retrofit = RetrofitClient.getRetrofitInstance()

        val apiAQQHome = retrofit.create(ApiAQQHome::class.java)
        val call = apiAQQHome.getnewfeed(ApartmentID)
        call.enqueue(object : Callback<ApiNewFeed> {
            override fun onResponse(call: Call<ApiNewFeed>, response: Response<ApiNewFeed>) {
                if (response.isSuccessful) {
                    val apiNewFeed = response.body()
                    if (apiNewFeed?.isSuccess() == true) {
                        val newfeedList = apiNewFeed.getRecords()
                        val layoutManager = LinearLayoutManager(requireContext())
                        recycler_view.layoutManager = layoutManager
                        val newfeedAdapter = NewAdapter(newfeedList)
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

        addmes.setOnClickListener {
            val intent = Intent(requireContext(), NewmesActivity::class.java)
            startActivity(intent)
        }

        return rootView
    }
}
