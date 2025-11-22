package com.example.prkha2082

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.prkha2082.data.AppDatabase
import com.example.prkha2082.repositories.PersonRepository
import com.example.prkha2082.ui.navigation.MyNavHost
import com.example.prkha2082.ui.viewmodels.MinViewModel
import com.example.prkha2082.ui.viewmodels.PersonAppViewModel
import com.example.prkha2082.ui.viewmodels.PersonViewModel
import com.example.prkha2082.ui.viewmodels.PrefViewModel
import com.example.prkha2082.ui.viewmodels.SmsViewModel


class MainActivity : ComponentActivity() {
    //Metoden som ber om tillatelse
    private val beomSmstillatelse = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {isGranted: Boolean ->
        if (isGranted) {
            Log.d("SMS","Tillatelse til å sende SMS")
        } else {
            Log.d("SMS","Ikke tillatelse til å sende SMS")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
            == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("SMS","Tillatelse til å sende SMS")
        } else
        {
            beomSmstillatelse.launch(Manifest.permission.SEND_SMS)
        }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "person_db"
        ).allowMainThreadQueries().build()
        val repository = PersonRepository(db.personDao())
        val personViewModel = PersonViewModel(repository, application)
        val minViewModel = MinViewModel(application = application)
        val personAppViewModel = PersonAppViewModel(application = application)
        val smsViewModel = SmsViewModel()
        val prefViewModel = PrefViewModel(application = application)
        minViewModel.start()

        setContent {
            val navController = rememberNavController()
            MyNavHost(navController = navController, personViewModel, minViewModel, smsViewModel, personAppViewModel, prefViewModel)
        }
    }
}