package com.nadim.atlaspackaging.login_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nadim.atlaspackaging.domain.RemoteDataRepo
import com.nadim.atlaspackaging.login_feature.domain.use_cases.ValidateCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val validateCredentials: ValidateCredentials,
    repo: RemoteDataRepo
) : ViewModel() {
    var state by mutableStateOf(LoginScreenState())

    val user = repo.user

    private val signInResultChannel = Channel<SignInResult>()
    val signInResult = signInResultChannel.consumeAsFlow()

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.OnEmailValueChangeEvent -> {
                state = state.copy(email = event.email)
            }
            is LoginScreenEvent.OnPasswordValueChangeEvent -> {
                state = state.copy(password = event.password)
            }
            is LoginScreenEvent.OnVisibilityToggleEvent -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }

            is LoginScreenEvent.OnEmailErrorEvent -> {
                state = state.copy(emailError = null)
            }
            is LoginScreenEvent.OnPasswordErrorEvent -> {
                state = state.copy(passwordError = null)
            }
        }
    }

    fun signIn(email: String, password: String) {
        val validateCredentials = validateCredentials.execute(email = email, password = password)
        state = state.copy(
            emailError = validateCredentials.emailError,
            passwordError = validateCredentials.passwordError
        )
        val proceedToSignIn =
            validateCredentials.emailError == null && validateCredentials.passwordError == null
        if (proceedToSignIn) {

        }

    }

    sealed class SignInResult {
        object Success : SignInResult()
        data class Failure(val errorMessage: String? = null) : SignInResult()
    }
}

