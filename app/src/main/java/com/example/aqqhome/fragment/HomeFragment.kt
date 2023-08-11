package com.example.aqqhome.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.models.SlideModel
import com.example.aqqhome.NewActivity
import com.example.aqqhome.payment.BillActivity
import com.example.aqqhome.user.InfoActivity
import com.example.aqqhome.user.LoadActivity
import com.example.aqqhome.payment.MoneyActivity
import com.example.aqqhome.user.NewCodeActivity
import com.example.aqqhome.R
import com.example.aqqhome.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencess: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.themcanho.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(R.layout.themcanho_bottomsheet_layout)
            val btn_doi: Button? = bottomSheetDialog.findViewById(R.id.btn_doi)
            btn_doi?.setOnClickListener {
                startActivity(Intent(requireContext(), NewCodeActivity::class.java))
            }
            bottomSheetDialog.show()
        }

        val imageList = arrayListOf(
            SlideModel("https://bdsweb.com.vn/upload_images/images/bbds/banner-bat-dong-san-01.jpg", null, null),
            SlideModel("https://megaon.vn/wp-content/uploads/2020/03/QT-web-24.03.png", null, null),
            SlideModel("https://intphcm.com/data/upload/thong-tin-poster-bat-dong-san.jpg", null, null)
        )
        binding.imageSlider.setImageList(imageList)

        sharedPreferences = requireActivity().getSharedPreferences("UserInfo", AppCompatActivity.MODE_PRIVATE)
        sharedPreferencess = requireActivity().getSharedPreferences("RoomID", AppCompatActivity.MODE_PRIVATE)
        val phone = sharedPreferences.getString("phone", "Guest")
        val userName = sharedPreferences.getString("name", "Guest")
        val sophong = sharedPreferencess.getString("RoomName", "Guest")

        binding.nameinf.text = userName
        binding.phoneinf.text = phone
        binding.sophonginf.text = sophong


        setupClickListeners()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.tienich.setOnClickListener { startActivity(Intent(requireContext(), NewActivity::class.java)) }
        binding.shop.setOnClickListener { startActivity(Intent(requireContext(), LoadActivity::class.java)) }
        binding.gopy.setOnClickListener { startActivity(Intent(requireContext(), LoadActivity::class.java)) }
        binding.hotline.setOnClickListener { startActivity(Intent(requireContext(), LoadActivity::class.java)) }
        binding.credit.setOnClickListener { startActivity(Intent(requireContext(), MoneyActivity::class.java)) }
        binding.thongtin.setOnClickListener { startActivity(Intent(requireContext(), InfoActivity::class.java)) }
        binding.bill.setOnClickListener { startActivity(Intent(requireContext(), BillActivity::class.java)) }
    }


}
