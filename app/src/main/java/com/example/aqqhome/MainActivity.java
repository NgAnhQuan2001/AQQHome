package com.example.aqqhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.aqqhome.fragment.CaidatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView nameinf,phoneinf,sophonginf;
    private ConstraintLayout hotline, gopy, shop, bill, tienich;
    private LinearLayout themcanho;

    private ImageView thongtin,credit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencess;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hotline = findViewById(R.id.hotline);
        gopy = findViewById(R.id.gopy);
        shop = findViewById(R.id.shop);
        tienich = findViewById(R.id.tienich);
        nameinf = findViewById(R.id.nameinf);
        phoneinf = findViewById(R.id.phoneinf);
        sophonginf = findViewById(R.id.sophonginf);
        bill = findViewById(R.id.bill);
        thongtin = findViewById(R.id.thongtin);
        credit = findViewById(R.id.credit);
        themcanho = findViewById(R.id.themcanho);


        //Get activity
        itentbill();
        thongtin();
        credit();
        ihotline();
        igopy();
        ishop();
        itienich();


        themcanho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Bottom Sheet Dialog
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);

                // Sử dụng layout đã tạo
                bottomSheetDialog.setContentView(R.layout.themcanho_bottomsheet_layout);

                // Hiển thị Bottom Sheet Dialog
                bottomSheetDialog.show();

                // Ánh xạ các view trong layout
                Button btn_doi = bottomSheetDialog.findViewById(R.id.btn_doi);
                btn_doi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, NewCodeActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });


        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

        imageList.add(new SlideModel("https://bdsweb.com.vn/upload_images/images/bbds/banner-bat-dong-san-01.jpg", null,null));
        imageList.add(new SlideModel("https://megaon.vn/wp-content/uploads/2020/03/QT-web-24.03.png", null,null));
        imageList.add(new SlideModel("https://intphcm.com/data/upload/thong-tin-poster-bat-dong-san.jpg", null,null));
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);

        //get info
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        sharedPreferencess = getSharedPreferences("RoomID", MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone", "Guest"); // "Guest" là giá trị mặc định nếu không tìm thấy "name"
        //String modifiedPhone = "0" + phone.substring(0, 4) + "***" + phone.substring(7);
        String userName = sharedPreferences.getString("name", "Guest"); // "Guest" là giá trị mặc định nếu không tìm thấy "name"
        String Sophong = sharedPreferencess.getString("RoomName", "Guest"); // "Guest" là giá trị mặc định nếu không tìm thấy "name"

        //set text info
        nameinf.setText(userName);
        phoneinf.setText(phone);
        sophonginf.setText(Sophong);

        // Bottom Navigation and fragment
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(i);
                } else if (id == R.id.caidat) {
                    fragment = new CaidatFragment();
                } else if (id == R.id.bangtin) {
                    //fragment = new BangtinFragment();
                } else if (id == R.id.thongbao) {
                    //fragment = new ThongbaoFragment();
                } else {
                    return false;
                }
                return loadFragment(fragment);
            }
        });

    }

    private void itienich() {
        tienich.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoadActivity.class);
            startActivity(intent);
        });
    }

    private void ishop() {
        shop.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRoomActivity.class);
            startActivity(intent);
        });
    }

    private void igopy() {
        gopy.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoadActivity.class);
            startActivity(intent);
        });
    }

    private void ihotline() {
        hotline.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoadActivity.class);
            startActivity(intent);
        });
    }

    private void credit() {
        credit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MoneyActivity.class);
            startActivity(intent);
        });
    }

    private void thongtin() {
        thongtin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        });
    }

    private void itentbill() {
        bill.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BillActivity.class);
            startActivity(intent);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
