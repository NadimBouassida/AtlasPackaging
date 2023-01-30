package com.nadim.atlaspackaging.daily_production.presentation.presentation

sealed class LoginScreenEvent {
    data class OnEmailValueChangeEvent(val email: String) : LoginScreenEvent()
    data class OnPasswordValueChangeEvent(val password: String) : LoginScreenEvent()
    object OnEmailErrorEvent : LoginScreenEvent()
    object OnPasswordErrorEvent : LoginScreenEvent()
    object OnVisibilityToggleEvent : LoginScreenEvent()
}


