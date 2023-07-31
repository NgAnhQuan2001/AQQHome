package com.example.aqqhome.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aqqhome.LoginActivity;
import com.example.aqqhome.MainActivity;
import com.example.aqqhome.NewCodeActivity;
import com.example.aqqhome.R;

public class CaidatFragment extends Fragment {
    private TextView dangxuat, tttn;
    private ImageView back;
    Fragment fragment;

    private SharedPreferences sharedPreferences,sharedPreferencess;

    public CaidatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caidat, container, false);

        dangxuat = view.findViewById(R.id.dangxuat);
        tttn = view.findViewById(R.id.tttn);

        sharedPreferences = getActivity().getSharedPreferences("UserInfo", MODE_PRIVATE);
        sharedPreferencess = getActivity().getSharedPreferences("RoomID", MODE_PRIVATE);
        dangxuat.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            SharedPreferences.Editor editor1 = sharedPreferencess.edit();
            editor.clear();
            editor.apply();
            editor1.clear();
            editor1.apply();
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            getActivity().finish();


        });
        tttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new HouseInfoFragment();
                load_Fragment(fragment);


            }
       });






        return view;
    }
    private void load_Fragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}
