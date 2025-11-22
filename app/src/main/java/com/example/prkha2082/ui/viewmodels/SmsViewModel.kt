package com.example.prkha2082.ui.viewmodels

import android.os.Message
import android.telephony.SmsManager
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SmsViewModel: ViewModel() {

    var message by mutableStateOf("Gratulerer med dagen, ")
    fun sendSms(telephone: Long, message: String) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(telephone.toString(), null,
            message,null,null
        )
        Log.d("SMS","SMS sendt til $telephone")
    }

    fun updateMessage(updatedMessage: String) {
        message = updatedMessage
    }
}