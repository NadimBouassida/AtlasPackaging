package com.nadim.atlaspackaging.ui.general_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadim.atlaspackaging.R


@Composable
fun CustomTopAppBar(
    navController: NavController? = null,
    machine: String? = null,
    navUpDestination: String? = null,
    popUpScreen: String? = null,
    showDownloadIcon: Boolean = false,
    showNavigationAction: Boolean = false,
    onDownloadIconClicked: () -> Unit = {},
){
    val style = LocalTextStyle.provides(MaterialTheme.typography.h5)
    TopAppBar(
        title = {
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Atlas Packaging",
                    color = MaterialTheme.colors.background,
                    style = if(machine!=null) style.value else MaterialTheme.typography.h4,
                )
                if (machine != null) {
                    Text(
                        text =  "Machine : $machine",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        },
        navigationIcon = {
            if (showNavigationAction){
                IconButton(
                    onClick = {
                        navController!!.navigate(navUpDestination!!){
                            popUpTo(popUpScreen!!){
                                inclusive = true
                            }
                        }
                    }
                ){
                    Icon(
                        modifier = Modifier.fillMaxSize(.7f),
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Navigate up",
                        tint = MaterialTheme.colors.background
                    )
                }
            }
        },
        actions = {
            if (showDownloadIcon){
                IconButton(onClick = { onDownloadIconClicked()}) {
                    Icon(
                        modifier = Modifier.fillMaxSize(.7f),
                        painter = painterResource(id = R.drawable.ic_download),
                        contentDescription = "download to excel",
                        tint = MaterialTheme.colors.background
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun CustomTopAppBarPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White), contentAlignment = Alignment.Center){
        val navController = NavController(LocalContext.current)
        CustomTopAppBar(navController = navController,
            navUpDestination = "" , popUpScreen = "",
            showDownloadIcon = true,
            machine = "Sealer"
        )
    }
}

