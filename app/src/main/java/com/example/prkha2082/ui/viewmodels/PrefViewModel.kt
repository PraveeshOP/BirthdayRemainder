package com.example.prkha2082.ui.viewmodels

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application

class PrefViewModel(application: Application): AndroidViewModel(application){

    fun settPref(tekst: String) {
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences("Mine Preferanser" ,
                android.content.Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("message", tekst)
        editor.apply()
    }
    fun hentPref(): String {
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences("Mine Preferanser" ,
                android.content.Context.MODE_PRIVATE)
        return sharedPreferences.getString("message", "") ?: ""
    }

    fun settBool(bool: Boolean) {
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences("Mine Preferanser" ,
                android.content.Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("bool", bool)
        editor.apply()
    }

    fun hentBool(): Boolean {
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences("Mine Preferanser" ,
                android.content.Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("bool", false)
    }
}
