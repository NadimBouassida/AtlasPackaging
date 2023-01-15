package com.nadim.atlaspackaging.main_feature.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nadim.atlaspackaging.utils.general_components.LogOutFloatingAction


@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopAppBar(
            title = {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                    Text(
                        text = "Atlas Packaging",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.h5,
                    )
                    Text(
                        text = "user: ${viewModel.userEmail.value}",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.body2
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primary,
        )
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
    LogOutFloatingAction(navController = navController)
}



