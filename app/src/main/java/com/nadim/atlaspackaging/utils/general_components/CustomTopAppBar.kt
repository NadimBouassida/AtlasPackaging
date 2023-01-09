package com.nadim.atlaspackaging.utils.general_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadim.atlaspackaging.R


@Composable
fun CustomTopAppBar(
    navController: NavController,
    textOne: String? = "AtlasPackaging",
    textTwo: String?,
    navUpDestination: String,
    popUpScreen: String,
    showDownloadIcon: Boolean = false,
    onDownloadIconClicked: () -> Unit = {},
){
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                if (textTwo != null) {
                    Text(
                        modifier = Modifier
                            .weight(2f),
                        text = "$textOne : $textTwo",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.h6,
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.size(30.dp),
                onClick = {
                    navController.navigate(navUpDestination){
                        popUpTo(popUpScreen){
                            inclusive = true
                        }
                    }
                }
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Navigate up",
                    tint = MaterialTheme.colors.background
                )
            }
        },
        actions = {
            if (showDownloadIcon){
                IconButton(onClick = { onDownloadIconClicked()}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_download),
                        contentDescription = "download to excel",
                        tint = MaterialTheme.colors.background
                    )
                }
            }
        }
    )
}

