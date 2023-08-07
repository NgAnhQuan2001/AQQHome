package com.example.aqqhome.utils

import android.content.Context
import android.content.SharedPreferences

object MyPref {
    private lateinit var sharedPreferences: SharedPreferences
    operator fun get(context: Context, key: String?, column: String?): String? {
        sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return sharedPreferences.getString(column, null)
    }
}