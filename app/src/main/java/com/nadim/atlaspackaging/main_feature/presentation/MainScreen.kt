package com.nadim.atlaspackaging.main_feature.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nadim.atlaspackaging.ui.general_components.CustomTopAppBar
import com.nadim.atlaspackaging.ui.general_components.LogOutFloatingAction


@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController,
) {
    Scaffold(
        topBar = { CustomTopAppBar() },
        floatingActionButton = {
            LogOutFloatingAction(logout = {viewModel.logOut()}, navController = navController)
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewModel.machineList.size) {
                SectionItem(machine = viewModel.machineList[it]) {
                    // onClick function for selected item
                    if(
                        viewModel.machineList[it].dropLast(1) in viewModel.user.value
                        || viewModel.user.value == "Admin"
                    ){
                        navController.navigate(
                            route = "machine_screen?machine=${viewModel.machineList[it]}"
                        )
                    }
                }
            }
        }
    }
}



