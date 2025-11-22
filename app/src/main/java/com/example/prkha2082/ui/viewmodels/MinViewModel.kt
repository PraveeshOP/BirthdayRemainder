package com.example.prkha2082.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.example.prkha2082.ui.screens.MinApp

class MinViewModel(application: Application): AndroidViewModel(application) {

    fun start(){
        Log.d("Start","Background Work Started")
        (application as MinApp).scheduleDailyWork(application.applicationContext)
    }
    fun stop(){
        Log.d("Stop","Background Work Stopped")
        (application as MinApp).cancelDailyWork(application.applicationContext)
    }
}
