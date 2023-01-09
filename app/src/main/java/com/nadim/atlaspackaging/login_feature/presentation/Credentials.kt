package com.nadim.atlaspackaging.login_feature.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nadim.atlaspackaging.R
import com.nadim.atlaspackaging.navigation.Screen
import kotlinx.coroutines.flow.collect

@Composable
fun Credentials(
    viewModel: LoginScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        val state = viewModel.state

        val icon = if (state.isVisible) painterResource(id = R.drawable.ic_visibility_on)
        else painterResource(id = R.drawable.ic_visibility_off)

        // Launch the Navigation to the main screen when the authentication is successful
        // Return a toast with the error message if the authentication is not successful
        LaunchedEffect(key1 = context) {
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
        LaunchedEffect(key1 = context) {
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
        // Email Text Field
        CustomTextField(
            text = state.email, label = "Email",
            onError = state.emailError != null,
            onValueChange = { viewModel.onEvent(LoginScreenEvent.OnEmailValueChange(it)) },
            keyboardType = KeyboardType.Email,
        )
        if (state.emailError != null) {
            Text(
                modifier = Modifier.fillMaxWidth(), text = state.emailError!!,
                color = MaterialTheme.colors.error, textAlign = TextAlign.End
            )
            state.emailError = null
        }
        Spacer(modifier = Modifier.height(16.dp))
        // PasswordTextField
        CustomTextField(text = state.password,
            label = "Password",
            keyboardType = KeyboardType.Password,
            onValueChange = { viewModel.onEvent(LoginScreenEvent.OnPasswordValueChange(it)) },
            onError = state.passwordError != null,
            trailingIcon = {
                Icon(painter = icon, contentDescription = "visible",
                    Modifier.clickable { viewModel.onEvent(LoginScreenEvent.OnVisibilityToggle) })
            },
            visualTransformation = if (state.isVisible) VisualTransformation.None
            else PasswordVisualTransformation())

        if (state.passwordError != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = state.passwordError!!,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.End
            )
            state.passwordError = null
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { viewModel.onEvent(LoginScreenEvent.OnSignIn) },
            modifier = Modifier.padding(top = 40.dp, bottom = 80.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.background,
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp
            )
        ) {
            Text(text = "Sign In")
        }
    }
}


