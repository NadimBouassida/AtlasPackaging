package com.nadim.atlaspackaging.login_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.nadim.atlaspackaging.login_feature.domain.use_cases.ValidateEmail
import com.nadim.atlaspackaging.login_feature.domain.use_cases.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    @Named("auth") private val auth: FirebaseAuth
) : ViewModel(){

    var state by mutableStateOf(LoginScreenState())

    private val signInResultChannel = Channel<SignInEventResponse>()
    val signInResult = signInResultChannel.receiveAsFlow()

    private val verifyIfUserAlreadySignInChannel = Channel<String?>()
    val verifyIfUserAlreadySignIn = verifyIfUserAlreadySignInChannel.receiveAsFlow()


    init {
        viewModelScope.launch {
            verifyIfUserAlreadySignInChannel.send(auth.currentUser?.email)
            delay(500)
        }
    }

    fun onEvent(event: LoginScreenEvent){
        when (event){
            is LoginScreenEvent.OnEmailValueChange -> {
               state = state.copy(email = event.email)
            }
            is LoginScreenEvent.OnPasswordValueChange -> {
                state = state.copy(password = event.password)
            }
            is LoginScreenEvent.OnVisibilityToggle -> {
                state = state.copy(isVisible = !state.isVisible)
            }
            is LoginScreenEvent.OnSignIn -> {
                signInWithEmailAndPassword(state.email,state.password)
            }
        }
    }

    private fun signInWithEmailAndPassword(email:String,password:String) {
        val emailResult = validateEmail.execute(email = state.email)
        val passwordResult = validatePassword.execute(password = state.password)

        val hasError = listOf(emailResult, passwordResult).any{
            !it.successful
        }
        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
        }
        else {
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                viewModelScope.launch {
                    delay(1000L)
                    signInResultChannel
                        .send(SignInEventResponse.Success(user = auth.currentUser?.email.toString()))
                }
            }.addOnFailureListener{
                viewModelScope.launch {
                    delay(1000L)
                    signInResultChannel.send(SignInEventResponse
                        .Failure(it.localizedMessage!!))
                }
            }
        }
    }

    sealed class SignInEventResponse{
        data class Success(val user: String = ""): SignInEventResponse()
        data class Failure(val errorMessage: String? = ""):SignInEventResponse()
    }
}

