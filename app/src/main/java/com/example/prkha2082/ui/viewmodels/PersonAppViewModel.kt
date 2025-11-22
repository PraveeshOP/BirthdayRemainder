package com.example.prkha2082.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class PersonAppViewModel(application: Application) : AndroidViewModel(application) {
    var name by mutableStateOf("")
    var telephone by mutableStateOf("")
    var birthDate by mutableStateOf("")
}