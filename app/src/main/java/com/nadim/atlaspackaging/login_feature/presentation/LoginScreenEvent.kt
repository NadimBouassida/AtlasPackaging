package com.nadim.atlaspackaging.login_feature.presentation

sealed class LoginScreenEvent  {
    data class OnEmailValueChange(val email: String) : LoginScreenEvent()
    data class OnPasswordValueChange(val password: String) : LoginScreenEvent()
    object OnVisibilityToggle: LoginScreenEvent()
    object OnSignIn : LoginScreenEvent()
}


