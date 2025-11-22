package com.example.prkha2082.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.prkha2082.ui.components.NavBar
import com.example.prkha2082.ui.theme.navButton
import com.example.prkha2082.ui.viewmodels.MinViewModel
import com.example.prkha2082.ui.viewmodels.PrefViewModel
import com.example.prkha2082.ui.viewmodels.SmsViewModel

@Composable
fun SettingsScreen( navController: NavController , minViewModel: MinViewModel, smsViewModel: SmsViewModel, prefViewModel: PrefViewModel) {

    var inputBox by remember { mutableStateOf(prefViewModel.hentBool()) }

    var message by rememberSaveable { mutableStateOf(prefViewModel.hentPref()) }

    Column (
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 30.dp),
    ) {
        Column {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = navButton
                ),
                onClick = { navController.navigate("personAppScreen")}
            ) {
                Text("Bak")
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Innstillinger",
                style = MaterialTheme.typography.headlineLarge)
        }
        Column (
            modifier = Modifier
                .padding(vertical = 20.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "SMS-tjenester",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(10.dp))
                Row {
                    Button(
                        modifier = Modifier
                            .width(80.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = navButton
                        ),
                        onClick = {
                            minViewModel.start()
                            inputBox = true
                            prefViewModel.settBool(true)}
                    ) {
                        Text("På")
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier
                            .width(80.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = navButton
                        ),
                        onClick = {
                            minViewModel.stop()
                            inputBox = false
                            prefViewModel.settBool(false)}
                    ) {
                        Text("Av")
                    }

                }
            }
        }
        if (inputBox) {
            Column {
                OutlinedTextField(
                    value = message,
                    onValueChange = {
                        message = it
                        smsViewModel.message = message },
                    modifier = Modifier.fillMaxWidth()
                )
                Button (
                    onClick = {
                        prefViewModel.settPref(message)
                        Log.d("SMS", "SMS messege saved in pref is ${prefViewModel.hentPref()}") //For debug
                        Log.d("SMS", "SMS message saved in viewModel is ${smsViewModel.message}")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = navButton
                    ),
                ) {
                    Text("Lagre")
                }
            }
        }
    }
}