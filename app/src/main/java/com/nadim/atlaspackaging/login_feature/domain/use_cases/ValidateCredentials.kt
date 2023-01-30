package com.nadim.atlaspackaging.login_feature.domain.use_cases

import com.nadim.atlaspackaging.daily_production.presentation.presentation.LoginScreenState
import javax.inject.Inject

class ValidateCredentials @Inject constructor (
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    ){
        fun execute (email: String, password: String): LoginScreenState {
            val emailResult = validateEmail.execute(email = email)
            val passwordResult = validatePassword.execute(password = password)

            val hasError = listOf(emailResult, passwordResult).any {
                !it.successful
            }

            if(hasError) {
                return LoginScreenState(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                )
            }
            return LoginScreenState()
        }
    }
