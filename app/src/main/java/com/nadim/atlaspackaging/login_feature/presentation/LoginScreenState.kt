package com.nadim.atlaspackaging.login_feature.presentation

data class LoginScreenState (
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false,
    val proceedToNextScreen: Boolean = false,
    val signInErrorMessage: String? = null,
)
