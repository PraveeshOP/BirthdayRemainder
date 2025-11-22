package com.example.prkha2082.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.prkha2082.ui.screens.PersonListScreen
import com.example.prkha2082.ui.screens.PersonApp
import com.example.prkha2082.ui.screens.PersonAppScreen
import com.example.prkha2082.ui.screens.PersonDetailScreen
import com.example.prkha2082.ui.screens.PersonUpdateScreen
import com.example.prkha2082.ui.screens.SettingsScreen
import com.example.prkha2082.ui.viewmodels.MinViewModel
import com.example.prkha2082.ui.viewmodels.PersonAppViewModel
import com.example.prkha2082.ui.viewmodels.PersonViewModel
import com.example.prkha2082.ui.viewmodels.PrefViewModel
import com.example.prkha2082.ui.viewmodels.SmsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyNavHost(
    navController: NavHostController,
    personViewModel: PersonViewModel = viewModel(),
    minViewModel: MinViewModel = viewModel (),
    smsViewModel: SmsViewModel = viewModel(),
    personAppViewModel: PersonAppViewModel,
    prefViewModel: PrefViewModel,
    )
{
    NavHost(navController = navController, startDestination = "personAppScreen", builder = {
        composable("personAppScreen"){
            PersonAppScreen(navController = navController, personViewModel)
        }
        composable("settingsScreen"){
            SettingsScreen( navController , minViewModel, smsViewModel, prefViewModel)
        }
        composable("personListScreen") {
            PersonListScreen(
                navController = navController,
                persons = personViewModel.persons.collectAsState().value,
                onPersonClick = { person ->
                    navController.navigate("detail/${person.id}")
                },
            )
        }
        composable(
            route = "detail/{personId}",
            arguments = listOf(navArgument("personId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val personId = backStackEntry.arguments?.getInt("personId")
            val person = personViewModel.persons.collectAsState().value
                .find { it.id == personId }
            person?.let {
                PersonDetailScreen(navController = navController, it, personViewModel)
            }
        }
        composable(
            route = "update/{personId}",
            arguments = listOf(navArgument("personId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val personId = backStackEntry.arguments?.getInt("personId")
            val person = personViewModel.persons.collectAsState().value
                .find { it.id == personId }
            person?.let {
                PersonUpdateScreen(navController, personViewModel, it)
            }
        }
    })
}
