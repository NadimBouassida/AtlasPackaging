package com.nadim.atlaspackaging.login_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadim.atlaspackaging.domain.RemoteDataRepo
import com.nadim.atlaspackaging.login_feature.domain.use_cases.ValidateEmail
import com.nadim.atlaspackaging.login_feature.domain.use_cases.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    remoteDataRepo: RemoteDataRepo
) : ViewModel(){

    private val auth = remoteDataRepo.auth
    var state by mutableStateOf(LoginScreenState())

    private val signInResultChannel = Channel<SignInEventResponse>()
    val signInResult = signInResultChannel.consumeAsFlow()

    val user = auth.currentUser

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
                    withContext(Dispatchers.IO){
                        signInResultChannel
                            .send(SignInEventResponse.Success(user = auth.currentUser?.email.toString()))
                    }
                }
            }.addOnFailureListener{
                viewModelScope.launch {
                    withContext(Dispatchers.IO){
                        signInResultChannel.send(SignInEventResponse
                            .Failure(it.localizedMessage!!))
                    }
                }
            }
        }
    }

    sealed class SignInEventResponse{
        data class Success(val user: String = ""): SignInEventResponse()
        data class Failure(val errorMessage: String? = ""):SignInEventResponse()
    }
}

