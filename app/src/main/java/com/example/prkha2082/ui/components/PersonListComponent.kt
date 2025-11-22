package com.example.prkha2082.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prkha2082.data.Person
import com.example.prkha2082.ui.theme.cardColor
import com.example.prkha2082.ui.theme.navButton
import com.example.prkha2082.ui.theme.personListCard

@Composable
fun PersonListComponent(
    navController: NavHostController,
    persons: List<Person>,
    onPersonClick: (Person) -> Unit,
) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        persons.forEach { person ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable { onPersonClick(person) },
                colors = CardDefaults.cardColors(
                    containerColor = personListCard,
                    contentColor = Color.White
                )
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(
                        text = person.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(text = "Telefone: ${person.telephone}")
                    Text(text = "Fødselsdato: ${person.birthDate}")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button (
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = navButton
            ),
            onClick = { navController.navigate("personListScreen")}
        ) {
            Text("Vis alle personer")
        }
    }
}