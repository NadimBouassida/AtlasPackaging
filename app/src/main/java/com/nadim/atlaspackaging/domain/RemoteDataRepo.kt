package com.nadim.atlaspackaging.domain

import com.google.firebase.auth.FirebaseAuth
import com.nadim.atlaspackaging.login_feature.presentation.LoginScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel

interface RemoteDataRepo {
    val auth : FirebaseAuth
    fun signOut()

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        scope: CoroutineScope,
        channel: Channel<LoginScreenViewModel.SignInResult>,
    )
}