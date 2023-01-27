package com.nadim.atlaspackaging.ui.general_components


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadim.atlaspackaging.R
import com.nadim.atlaspackaging.navigation.Screen


@Composable
fun LogOutFloatingAction(
    logout: () -> Unit,
    navController: NavController,
){
    var logoutButtonClicked by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { logoutButtonClicked = true },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_log_out),
                contentDescription = "Sign out"
            )
        }
    }

    if (logoutButtonClicked){
        Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Snackbar(
                modifier = Modifier.padding(12.dp),
                action = {
                    Row {
                        Button(onClick = {
                            logout()
                            navController
                                .navigate(Screen.LoginScreen.route){
                                    popUpTo(Screen.MainScreen.route){
                                        inclusive = true
                                    }
                                }
                        }) {
                            Text(text = "Confirm", color = MaterialTheme.colors.background)
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Button(onClick = { logoutButtonClicked = false }) {
                            Text(text = "Cancel", color = MaterialTheme.colors.background)
                        }
                    }
                },
                actionOnNewLine = true,
                elevation = 10.dp,
            ) {
                Text(text = "Are you sure you want to logout ?")
            }
        }
    }
}

@Preview
@Composable
fun LogOutFloatingActionPreview(){
    val navController = NavController(LocalContext.current)
    LogOutFloatingAction({},navController)
}
