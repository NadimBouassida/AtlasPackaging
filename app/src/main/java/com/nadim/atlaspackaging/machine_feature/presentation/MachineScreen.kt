package com.nadim.atlaspackaging.machine_feature.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nadim.atlaspackaging.R
import com.nadim.atlaspackaging.navigation.Screen
import com.nadim.atlaspackaging.ui.general_components.CustomTopAppBar
import com.nadim.atlaspackaging.ui.general_components.LogOutFloatingAction


@Composable
fun MachineScreen(
    viewModel: MachineScreenViewModel = hiltViewModel(),
    navController: NavController,
    machine: String?
){
    Scaffold(
        topBar = {
            CustomTopAppBar(
                machine = machine,
                navController = navController,
                navUpDestination = Screen.MainScreen.route,
                popUpScreen = Screen.MainScreen.route,
                showNavigationAction = true,
            )
        },
        floatingActionButton = {
            LogOutFloatingAction(logout = {viewModel.logOut()},
            navController = navController)
        },

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MachineItem(
                text = "Daily Production",
                id = R.drawable.ic_add_production,
                onClick = {
                    navController.navigate(
                        route = "daily_production_screen?machine=$machine"
                    )
                }
            )
            MachineItem(
                text = "Daily Production Archive",
                id = R.drawable.ic_archive,
                onClick = {
                    navController.navigate(
                        route = "archive_screen?machine=$machine"
                    )
                },
            )
        }

    }
}
