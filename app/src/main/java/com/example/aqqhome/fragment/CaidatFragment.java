package com.example.aqqhome.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.aqqhome.auth.LoginActivity;
import com.example.aqqhome.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CaidatFragment extends Fragment {
    private TextView dangxuat, tttn;
    private ImageView back;
    Fragment fragment;

    private SharedPreferences sharedPreferences, sharedPreferencess;

    public CaidatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caidat, container, false);

        dangxuat = view.findViewById(R.id.dangxuat);
        tttn = view.findViewById(R.id.tttn);

        sharedPreferences = requireActivity().getSharedPreferences("UserInfo", MODE_PRIVATE);
        sharedPreferencess = requireActivity().getSharedPreferences("RoomID", MODE_PRIVATE);
        dangxuat.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                    .setPositiveButton("Xác nhận", (dialog, which) -> {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        SharedPreferences.Editor editor1 = sharedPreferencess.edit();
                        editor.clear();
                        editor.apply();
                        editor1.clear();
                        editor1.apply();
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                        getActivity().finish();
                    })
                    .setNegativeButton("Quay lại", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        });

        tttn.setOnClickListener(v -> {
            fragment = new HouseInfoFragment();
            replaceFragment(fragment);
        });

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null); // Add the transaction to the back stack
        fragmentTransaction.commit();
    }
}
