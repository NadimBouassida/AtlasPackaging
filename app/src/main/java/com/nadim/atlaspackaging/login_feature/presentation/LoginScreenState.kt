package com.nadim.atlaspackaging.login_feature.presentation

data class LoginScreenState (
    val email: String = "",
    var emailError: String? = null,
    val password: String = "",
    var passwordError: String? = null,
    val isVisible: Boolean = false,
)
