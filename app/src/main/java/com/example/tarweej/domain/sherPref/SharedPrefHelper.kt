package com.example.tarweej.domain.sherPref

import android.content.Context
import android.content.SharedPreferences


class SharedPrefHelper(context: Context) {
    private val sharedPrefName = "userSharedPref"
    private var sharedPref: SharedPreferences
    private val editor: SharedPreferences.Editor
    init {
        sharedPref = context.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }
    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }



}