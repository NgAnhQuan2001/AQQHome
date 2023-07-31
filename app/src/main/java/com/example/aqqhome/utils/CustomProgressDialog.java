package com.example.aqqhome.utils;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.example.aqqhome.R;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class CustomProgressDialog extends Dialog {
    private MaterialProgressBar progressBar;

    public CustomProgressDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progress);
        setCancelable(false);
        progressBar = findViewById(R.id.progressBar);
    }

    public void showProgress() {
        if (!isShowing()) {
            show();
        }
    }

    public void hideProgress() {
        if (isShowing()) {
            dismiss();
        }
    }
}
