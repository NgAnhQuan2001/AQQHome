package com.example.aqqhome.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentView = activity.currentFocus ?: View(activity)
        imm.hideSoftInputFromWindow(currentView.windowToken, 0)
    }
}