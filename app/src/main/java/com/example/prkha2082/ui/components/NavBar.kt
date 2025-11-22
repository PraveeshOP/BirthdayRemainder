package com.example.prkha2082.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prkha2082.ui.theme.navButton


@Composable
fun NavBar( navController: NavController ) {

    Column (
        modifier = Modifier
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Button(
                modifier = Modifier
                    .width(130.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = navButton
                ),
                onClick = { navController.navigate("personApp") }
            ) {
                Text("Hjem")
            }
            Button(
                modifier = Modifier
                    .width(130.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = navButton
                ),
                onClick = { navController.navigate("settingsScreen")}
            ) {
                Text("Innstillinger")
            }
        }
    }
}

