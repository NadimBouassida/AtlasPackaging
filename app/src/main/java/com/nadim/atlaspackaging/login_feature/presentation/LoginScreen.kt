package com.nadim.atlaspackaging.login_feature.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nadim.atlaspackaging.R
import com.nadim.atlaspackaging.navigation.Screen
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel(),
){
    val context = LocalContext.current
    // Launch the Navigation to the main screen when the authentication is successful
    // Return a toast with the error message if the authentication is not successful
    LaunchedEffect(key1 = Unit) {
        viewModel.signInResult.collect { event ->
            when (event) {
                is LoginScreenViewModel.SignInEventResponse.Success -> {
                    navController.navigate(route = Screen.MainScreen.route) {
                        popUpTo(Screen.LoginScreen.route) { inclusive = true }
                    }
                }
                is LoginScreenViewModel.SignInEventResponse.Failure -> {
                    Toast.makeText(context, event.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    //Launch the auto navigate to main screen event if the user is already logged in
    LaunchedEffect(key1 = Unit) {
        viewModel.verifyIfUserAlreadySignIn.collect {
            if (it != null) {
                if (it.isNotBlank()) {
                    Toast.makeText(context, "Already signed in as: $it", Toast.LENGTH_LONG).show()
                    navController.navigate(route = Screen.MainScreen.route) {
                        popUpTo(Screen.LoginScreen.route) {
                            inclusive = true }
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Atlas Packaging",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.h5
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 20.dp),
            backgroundColor = Color.LightGray,
            elevation = 10.dp,
            border = BorderStroke(width = 3.dp, color = MaterialTheme.colors.primary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.atlas),
                    contentDescription = "logo",
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .size(160.dp)
                        .border(
                            width = 5.dp,
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.secondary.copy(alpha = 0.2f)),
                )
                Credentials(
                    state = viewModel.state,
                    onEmailValueChange = {viewModel.onEvent(LoginScreenEvent.OnEmailValueChange(it))},
                    onPasswordValueChange = {viewModel.onEvent(LoginScreenEvent.OnPasswordValueChange(it))},
                    onVisibilityToggle = {viewModel.onEvent(LoginScreenEvent.OnVisibilityToggle)},
                    onSignIn = {viewModel.onEvent(LoginScreenEvent.OnSignIn)}
                )


            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    Box(modifier = Modifier.background(Color.White)){
        val navController: NavController = rememberNavController()
        LoginScreen(navController = navController)
    }
}


