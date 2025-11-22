package com.example.prkha2082.ui.screens

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.prkha2082.data.AppDatabase
import com.example.prkha2082.repositories.PersonRepository
import com.example.prkha2082.ui.viewmodels.PrefViewModel
import com.example.prkha2082.ui.viewmodels.SmsViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class MinApp: Application() {

    private lateinit var personRepository: PersonRepository

    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "person_db"
        ).allowMainThreadQueries().build()
        personRepository = PersonRepository(database.personDao())
    }

    fun getPersonRepository(): PersonRepository {
        return personRepository
    }
    fun cancelDailyWork(context: Context){
        WorkManager.getInstance(context).cancelUniqueWork("Daglig arbeid")
    }
    fun scheduleDailyWork(context: Context) {
        Log.d("WorkManager","WorkManager Started")
        /*val testWork = OneTimeWorkRequestBuilder<MinArbeider>().build()
        WorkManager.getInstance(context).enqueue(testWork)*/


        val workRequest = PeriodicWorkRequestBuilder<MinArbeider>(
            1440, TimeUnit.MINUTES
        ) .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED) // only run with internet
                .build()
        )
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "Daglig arbeid",
                ExistingPeriodicWorkPolicy.KEEP, // keep previous if already scheduled
                workRequest
            )
    }
}


class MinArbeider(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {

        return try {
            val prefViewModel = PrefViewModel(application = applicationContext as MinApp)
            val smsViewModel = SmsViewModel()

            val today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            val todaySub = today.substring(0,5)

            val app = applicationContext as MinApp
            val personRepository = app.getPersonRepository()

            val persons = personRepository.getAllPersonsOnce()

            for (person in persons){
                val personDOB = person.birthDate
                val personDOBSub = personDOB.substring(0,5)
                if (checkBirthday(todaySub, personDOBSub)){
                    val message = "${prefViewModel.hentPref()} ${person.name}."
                    smsViewModel.sendSms(person.telephone, message)
                    Log.d("WorkManager", "${person.name} has birthday today and Sms sent to ${person.telephone} sms: $message")
                }
            }

            Result.success()
        } catch (e: Exception) {
            Log.d("WorkManager", "Failed with : $e")
            Result.failure()
        }
    }

    fun checkBirthday(today: String, personDOB: String) : Boolean {
        return today == personDOB
    }
}

