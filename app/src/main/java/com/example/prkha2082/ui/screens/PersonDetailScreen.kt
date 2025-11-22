package com.example.prkha2082.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prkha2082.data.Person
import com.example.prkha2082.ui.components.DialogBox
import com.example.prkha2082.ui.theme.cardColor
import com.example.prkha2082.ui.theme.navButton
import com.example.prkha2082.ui.viewmodels.PersonViewModel

@Composable
fun PersonDetailScreen( navController: NavHostController, person: Person, vm : PersonViewModel ) {

    var showDialog by remember { mutableStateOf(false) }

    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 250.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
            contentColor = Color.White
        )
    ) {
        Column (
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Button (
                colors = ButtonDefaults.buttonColors(
                    containerColor = navButton
                ),
                onClick = { navController.navigate("personListScreen")}
            ) {
                Text("Bak")
            }
        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                " Navn : ${person.name}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text("Telefone : ${person.telephone}")
            Text("Fødselsdato : ${person.birthDate}")
            Row {
                Button(
                    modifier = Modifier
                        .padding(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = navButton
                    ),
                    onClick = { navController.navigate("update/${person.id}") }
                ) {
                    Text("Endre")
                }

                Button(
                    modifier = Modifier
                        .padding(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Text("Slett")
                }
            }
        }
        if (showDialog) {
            DialogBox(
                onDismissRequest = { navController.navigate("personAppScreen") },
                onConfirmation = {
                    vm.removePerson(person.id, person.name, person.telephone, person.birthDate)
                    navController.navigate("personAppScreen") },
                dialogTitle = "Slett Venn",
                dialogText = "Er du sikker?"
            )
        }
    }
}