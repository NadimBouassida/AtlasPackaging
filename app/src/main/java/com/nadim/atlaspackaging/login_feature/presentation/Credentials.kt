package com.nadim.atlaspackaging.login_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadim.atlaspackaging.R

@Composable
fun Credentials(
    state: LoginScreenState,
    onEmailValueChange: (it: String) -> Unit,
    onPasswordValueChange: (it: String) -> Unit,
    onVisibilityToggle: () -> Unit,
    onSignIn: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val icon = if (state.isVisible) painterResource(id = R.drawable.ic_visibility_on)
        else painterResource(id = R.drawable.ic_visibility_off)
        // Email Text Field
        CustomTextField(
            text = state.email, label = "Email",
            onError = state.emailError != null,
            onValueChange = { onEmailValueChange(it)},
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
            onValueChange = { onPasswordValueChange(it) },
            onError = state.passwordError != null,
            trailingIcon = {
                Icon(painter = icon, contentDescription = "visible",
                    Modifier.clickable { onVisibilityToggle() })
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
            onClick = { onSignIn() },
            modifier = Modifier.padding(top = 40.dp, bottom = 80.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.background,
            ),
        ) {
            Text(text = "Sign In")
        }
    }
}

@Preview
@Composable
fun CredentialsPreview(){
    Box(modifier = Modifier.background(Color.White)){
        val state = LoginScreenState()
        Credentials(
            state = state,
            onEmailValueChange = {},
            onPasswordValueChange = {},
            onVisibilityToggle = {},
            onSignIn = {}
        )
    }
}