package com.nadim.atlaspackaging.machine_feature.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadim.atlaspackaging.R
import com.nadim.atlaspackaging.navigation.Screen
import com.nadim.atlaspackaging.utils.general_components.CustomTopAppBar
import com.nadim.atlaspackaging.utils.general_components.LogOutFloatingAction


@Composable
fun MachineScreen(
    navController: NavController,
    machine: String?
){
    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomTopAppBar(
                textTwo = machine,
                navController = navController,
                navUpDestination = Screen.MainScreen.route,
                popUpScreen = Screen.MainScreen.route,
            )
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
    LogOutFloatingAction(navController = navController)
}
