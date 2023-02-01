package com.nadim.atlaspackaging.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nadim.atlaspackaging.login_feature.presentation.LoginScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel


interface AuthRepo {
    val auth : FirebaseAuth
    val user: FirebaseUser? get() = auth.currentUser
    fun signOut()
    suspend fun signIn(
        email: String,
        password: String,
        channel: Channel<LoginScreenViewModel.SignInResult>,
        scope: CoroutineScope
    ) : Task<AuthResult>
}