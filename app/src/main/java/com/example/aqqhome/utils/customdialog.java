package com.example.aqqhome.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.aqqhome.R;

public class customdialog {
    public static void showdialog(String title, String message, String button, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.RoundedDialog);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(view);

        // Lấy các view từ custom layout
        TextView titleView = view.findViewById(R.id.title);
        TextView messageView = view.findViewById(R.id.message);
        Button okButton = view.findViewById(R.id.ok_button);

        // Đặt nội dung cho các view
        titleView.setText(title);
        messageView.setText(message);
        okButton.setText(button);

        // Tạo dialog
        AlertDialog alertDialog = builder.create();

        // Đặt listener cho nút OK
        okButton.setOnClickListener(v -> alertDialog.dismiss());

        // Hiển thị dialog
        alertDialog.show();
    }
}
