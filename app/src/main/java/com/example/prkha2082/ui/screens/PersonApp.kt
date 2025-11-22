package com.example.prkha2082.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prkha2082.ui.components.NavBar
import com.example.prkha2082.ui.components.PersonListComponent
import com.example.prkha2082.ui.theme.navButton
import com.example.prkha2082.ui.viewmodels.PersonAppViewModel
import com.example.prkha2082.ui.viewmodels.PersonViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonAppScreen(
    navController: NavHostController,
    personViewModel: PersonViewModel
) {
    val personAppViewModel: PersonAppViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    PersonApp(navController, personViewModel, personAppViewModel)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonApp(navController: NavHostController, vm: PersonViewModel, personAppViewModel: PersonAppViewModel ) {
    val persons by vm.persons.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 30.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        )
        {
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = navButton
                ),
                onClick = { navController.navigate("settingsScreen") }
            ) {
                Text(
                    text ="⚙\uFE0F",
                    fontSize = 20.sp,
                    )
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Register Bursdag",
                style = MaterialTheme.typography.headlineLarge)
        }

        Column {
            OutlinedTextField(
                value = personAppViewModel.name,
                onValueChange = { personAppViewModel.name = it },
                label = { Text("Navn") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = personAppViewModel.telephone,
                onValueChange = { personAppViewModel.telephone = it },
                label = { Text("Telefon") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = personAppViewModel.birthDate,
                onValueChange = { personAppViewModel.birthDate = it },
                label = { Text("Fødselsdato : dd.mm.åååå") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (personAppViewModel.name.isNotBlank() && personAppViewModel.telephone.isNotBlank() && personAppViewModel.birthDate.isNotBlank()) {
                        vm.addPerson(personAppViewModel.name, personAppViewModel.telephone.toLong(), personAppViewModel.birthDate)
                        personAppViewModel.name = ""
                        personAppViewModel.telephone = ""
                        personAppViewModel.birthDate = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = navButton
                )
            ) {
                Text("Legg til person")
            }
            Spacer(modifier = Modifier.height(16.dp))
            PersonListComponent(navController = navController, persons, onPersonClick = { person ->
                Log.d("Clicked", "Person clicked, id: ${person.id} , name: ${person.name}")
                navController.navigate("detail/${person.id}")
            })
        }
    }
}


