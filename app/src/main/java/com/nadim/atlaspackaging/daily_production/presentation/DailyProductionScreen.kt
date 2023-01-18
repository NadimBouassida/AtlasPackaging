package com.nadim.atlaspackaging.daily_production.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nadim.atlaspackaging.R
import com.nadim.atlaspackaging.daily_production.presentation.components.CustomList
import com.nadim.atlaspackaging.daily_production.presentation.components.ProdItem
import com.nadim.atlaspackaging.navigation.Screen
import com.nadim.atlaspackaging.utils.general_components.CustomTopAppBar
import com.nadim.atlaspackaging.utils.general_components.LogOutFloatingAction

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DailyProductionScreen(
    viewModel: DailyProductionViewModel = hiltViewModel(),
    navController: NavController,
    machine: String?,
){
    val context = LocalContext.current
    val datePickerDialog by remember {
        mutableStateOf(viewModel.getDatePicker(context = context))
    }

    val state = rememberScaffoldState()

    var showConductorsList by remember {
        mutableStateOf(false)
    }

    var showPostList by remember {
        mutableStateOf(false)
    }

    var showClientsList by remember {
        mutableStateOf(false)
    }
    var showArticlesList by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = context){
        viewModel.createConductorsList(machine = machine)
        viewModel.setMachineName(machine.toString())
    }


    Scaffold(
        scaffoldState = state,
    ) {
        Box {
            Column {
                CustomTopAppBar(
                    navController = navController,
                    textOne = machine,
                    textTwo = "Daily Production",
                    navUpDestination = "machine_screen?machine=$machine",
                    popUpScreen = Screen.MachineScreen.route
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProdItem(
                        text = viewModel.date.value,
                        order = 1,
                        label = "Date",
                        showTrailingIcon = true,
                        enabled = false,
                        onClick = {
                            datePickerDialog.show()
                        },
                    )
                    ProdItem(
                        text = viewModel.dataState.conductor,
                        order = 2,
                        label = "Conductor",
                        showTrailingIcon = true,
                        enabled = false,
                        onClick = {
                            showConductorsList = true
                        }
                    )
                    ProdItem(
                        text = viewModel.dataState.post,
                        order = 3,
                        label = "Post N°",
                        showTrailingIcon = true,
                        enabled = false,
                        onClick = { showPostList = true }
                    )
                    ProdItem(
                        text = viewModel.dataState.client,
                        order = 4,
                        label = "Client",
                        showTrailingIcon = true,
                        enabled = false,
                        onClick = {
                            showClientsList = true
                            viewModel.getClientsList()
                        }
                    )
                    ProdItem(
                        text = viewModel.dataState.article,
                        order = 4,
                        label = "Article",
                        showTrailingIcon = true,
                        enabled = false,
                        onClick = {
                            if (viewModel.dataState.client.isNotBlank()){
                                viewModel.getArticlesList(viewModel.dataState.client)
                                showArticlesList = true
                            }
                            if(viewModel.dataState.client.isBlank()){
                                Toast.makeText(context,"Client field is empty!",Toast.LENGTH_SHORT).show()
                            }
                        }
                    )

                    ProdItem(order = 5, label = "Lot", text = viewModel.dataState.lot,
                        showTrailingIcon = false, numbersOnly = true, maxLines = 1,
                        onValueChange = {
                            if (it.length < 6){
                                viewModel.onEvent(Events.LotChange, it)
                            }
                            if(it.length >= 6) {
                                val quantity = it.dropLast(it.length-6)
                                viewModel.onEvent(Events.LotChange,quantity)
                        }
                    })

                    ProdItem(
                        text = viewModel.dataState.production,
                        order = 6,
                        label = "Production",
                        numbersOnly = true,
                    ) {
                        if (it.length < 6){
                            viewModel.onEvent(Events.ProductionChange,it)
                        }
                        if(it.length >= 6) {
                            val quantity = it.dropLast(it.length-6)
                            viewModel.onEvent(Events.ProductionChange,quantity)
                        }
                    }
                    ProdItem(
                        text = viewModel.dataState.waste,
                        order = 7,
                        label = "Waste",
                        numbersOnly = true
                    ) {
                        if (it.length < 6){
                            viewModel.onEvent(Events.WasteChange,it)
                        }
                        if(it.length >= 6) {
                            val quantity = it.dropLast(it.length-6)
                            viewModel.onEvent(Events.WasteChange, quantity)
                        }
                    }
                    ProdItem(
                        text = viewModel.dataState.commentary,
                        order = 8,
                        label = "Commentary",
                        maxLines = 10,
                    ) {
                        viewModel.onEvent(Events.CommentaryChange,it)
                    }
                    Button(
                        modifier = Modifier.padding(bottom = 20.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colors.background
                        ),
                        onClick = {viewModel.onEvent(Events.Submit,"")}
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Submit",
                                style = MaterialTheme.typography.h6,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = "Validate"
                            )
                        }
                    }
                }
            }
        }

        LogOutFloatingAction(navController = navController)

        if (showConductorsList){
            CustomList(
                onIconClick = {
                    showConductorsList = false
                },
                onItemClick = {
                    viewModel.onEvent(Events.ConductorChange,it)
                    showConductorsList = false
                },
                itemsList = viewModel.conductorsList,
            )
        }
        if (showPostList){
            CustomList(
                onIconClick = { showPostList = false },
                onItemClick = {
                    viewModel.onEvent(Events.PostChange, it)
                    showPostList = false
                },
                itemsList = listOf("1","2","3"),
            )
        }
        if (showClientsList){
            CustomList(
                onIconClick = { showClientsList = false },
                onItemClick = {
                    viewModel.onEvent(Events.ClientChange,it)
                    showClientsList = false
                },
                itemsList = viewModel.clientList,
            )
        }
        if (viewModel.articlesList.isNotEmpty() && showArticlesList){
            CustomList(
                onIconClick = { showArticlesList= false },
                onItemClick = {
                    viewModel.onEvent(Events.ArticleChange,it)
                    showArticlesList = false
                },
                itemsList = viewModel.articlesList,
            )
        }

    }
}