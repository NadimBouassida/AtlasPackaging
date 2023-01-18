package com.nadim.atlaspackaging.archive.presentation


import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nadim.atlaspackaging.R
import com.nadim.atlaspackaging.archive.presentation.components.ArchiveListItem
import com.nadim.atlaspackaging.archive.presentation.components.ProductionTable
import com.nadim.atlaspackaging.navigation.Screen
import com.nadim.atlaspackaging.utils.general_components.CustomTopAppBar
import com.nadim.atlaspackaging.utils.general_components.LogOutFloatingAction


@Composable
fun ArchiveScreen(
    viewModel: ArchiveScreenViewModel = hiltViewModel(),
    navController: NavController,
    machine: String?,
    appContext: Context
){
    var showProductionTable by remember {
        mutableStateOf(false)
    }
    var showDeleteSnackBar by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val state = rememberScaffoldState()

    LaunchedEffect(key1 = context){
        viewModel.downloadData(machine.toString())
    }

    if (viewModel.openExcelFile.value){
        val intent = Intent(DownloadManager.ACTION_VIEW_DOWNLOADS)
        appContext.startActivity(intent)
        viewModel.openExcelFile.value = false
    }

    Scaffold(
        scaffoldState = state
    ) {
       Box {
           Column {
               CustomTopAppBar(
                   navController = navController,
                   textOne = machine,
                   textTwo = "Archive",
                   navUpDestination = "machine_screen?machine=$machine",
                   popUpScreen = Screen.MachineScreen.route,
                   showDownloadIcon = true,
                   onDownloadIconClicked = {viewModel.onDownloadIconClicked()}
               )

               Box(
                   modifier = Modifier
                       .fillMaxWidth(),
                   contentAlignment = Alignment.Center,
               ) {
                   Column(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       var isOpen by remember {
                           mutableStateOf(false)
                       }
                       OutlinedTextField(
                           modifier = Modifier
                               .padding(top = 30.dp),
                           value = viewModel.search.value,
                           placeholder = { Text(text = "Search by ${viewModel.searchType.value}...") },
                           trailingIcon = {
                               Icon(
                                   painter = painterResource(id = R.drawable.ic_search),
                                   contentDescription = "search"
                               )
                           },
                           onValueChange = {
                               viewModel.setSearchValue(it)
                               viewModel.onSearchValueChange()
                               if (viewModel.search.value == ""){
                                   viewModel.downloadData(machine.toString())
                               }
                           },
                           leadingIcon = {
                               IconButton(onClick = { isOpen = !isOpen }) {
                                   Icon(
                                       painter = painterResource(id = R.drawable.ic_down_arrow),
                                       contentDescription = "drop down menu show"
                                   )
                               }
                           },
                       )
                       if (isOpen) {
                           Column(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(horizontal = 60.dp)
                               ,
                               horizontalAlignment = Alignment.CenterHorizontally
                           ) {
                               for (i in 1..5) {
                                   Card(
                                       modifier = Modifier
                                           .fillMaxWidth()
                                           .padding(vertical = 5.dp),
                                       backgroundColor = Color.LightGray,
                                       elevation = 5.dp
                                   )
                                   {
                                       var text by remember { mutableStateOf("") }
                                       when (i) {
                                           1 -> text = "client"
                                           2 -> text = "article"
                                           3 -> text = "date"
                                           4 -> text = "lot"
                                           5 -> text = "conductor"
                                       }
                                       Text(
                                           text = "Search by $text..",
                                           modifier = Modifier
                                               .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                                               .clickable {
                                                   viewModel.setSearchType(text)
                                                   isOpen = false
                                               }
                                       )
                                   }
                               }
                           }
                       }

                       LazyColumn(
                           modifier = Modifier
                               .fillMaxSize()
                               .padding(top = 20.dp),
                           verticalArrangement = Arrangement.Top,
                           horizontalAlignment = Alignment.CenterHorizontally,
                       ) {
                           val dataList = viewModel.dataList
                           items(dataList.size){
                               ArchiveListItem(
                                   date = dataList[it].date,
                                   article = dataList[it].article,
                                   client = dataList[it].client,
                                   onClick = {
                                       viewModel.setSelectedDataValue(dataList[it])
                                       showProductionTable = true
                                   },
                                   onIconClick = {
                                       showDeleteSnackBar = true
                                       viewModel.setItemToBeDeleted(dataList[it])
                                   }
                               )
                           }
                       }
                   }
                   if (showProductionTable) {
                       Box(modifier = Modifier.fillMaxSize()){
                           ProductionTable(
                               date = viewModel.selectedData.date,
                               client = viewModel.selectedData.client ,
                               article = viewModel.selectedData.article,
                               post = viewModel.selectedData.post,
                               conductor = viewModel.selectedData.conductor,
                               commentary = viewModel.selectedData.commentary,
                               lot = viewModel.selectedData.lot,
                               production = viewModel.selectedData.production,
                               waste = viewModel.selectedData.waste,
                               time = viewModel.selectedData.time,
                               onIconClick = {showProductionTable = false}
                           )
                       }
                   }
                   if (showDeleteSnackBar) {
                       Box(
                           modifier = Modifier.fillMaxSize(),
                           contentAlignment = Alignment.Center
                       ) {
                           Snackbar(
                               modifier = Modifier.padding(12.dp),
                               action = {
                                   Row {
                                       Button(onClick = {
                                           viewModel.deleteData(machine.toString())
                                           showDeleteSnackBar = false
                                       }) {
                                           Text(
                                               text = "Confirm",
                                               color = MaterialTheme.colors.background
                                           )
                                       }
                                       Spacer(modifier = Modifier.padding(16.dp))
                                       Button(onClick = { showDeleteSnackBar = false }) {
                                           Text(
                                               text = "Cancel",
                                               color = MaterialTheme.colors.background
                                           )
                                       }
                                   }
                               },
                               actionOnNewLine = true,
                               elevation = 10.dp,
                           ) {
                               Text(text = "Sure your want to delete?")
                           }
                       }
                   }
               }
               LogOutFloatingAction(navController = navController)
           }
       }
    }
}