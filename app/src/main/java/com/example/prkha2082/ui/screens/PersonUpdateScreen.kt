package com.example.prkha2082.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prkha2082.data.Person
import com.example.prkha2082.ui.theme.navButton
import com.example.prkha2082.ui.viewmodels.PersonAppViewModel
import com.example.prkha2082.ui.viewmodels.PersonUpdateScreenViewModel
import com.example.prkha2082.ui.viewmodels.PersonViewModel

@Composable
fun PersonUpdateScreen(
    navController: NavHostController,
    personViewModel: PersonViewModel,
    person: Person
) {
    val viewModel: PersonUpdateScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    if (viewModel.name.isEmpty() && viewModel.telephone.isEmpty() && viewModel.birthDate.isEmpty()) {
        viewModel.name = person.name
        viewModel.telephone = person.telephone.toString()
        viewModel.birthDate = person.birthDate
    }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp)
    ) {
        Column {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = navButton
                ),
                onClick = { navController.navigate("personAppScreen") }
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
                text = "Endre Venn",
                style = MaterialTheme.typography.headlineLarge)
        }
        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("Navn") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.telephone,
            onValueChange = { viewModel.telephone = it },
            label = { Text("Telefon") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.birthDate,
            onValueChange = { viewModel.birthDate = it },
            label = { Text("Fødselsdato") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = navButton
            ),
            onClick = {
                if (viewModel.name.isNotBlank() && viewModel.telephone.isNotBlank() && viewModel.birthDate.isNotBlank()) {
                    personViewModel.updatePerson(
                        person.id,
                        viewModel.name,
                        viewModel.telephone.toLong(),
                        viewModel.birthDate
                    )
                    navController.navigate("personAppScreen")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Endre")
        }
    }
}