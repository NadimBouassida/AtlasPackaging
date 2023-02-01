package com.nadim.atlaspackaging.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nadim.atlaspackaging.domain.AuthRepo
import com.nadim.atlaspackaging.login_feature.presentation.LoginScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class AuthRepoImp @Inject constructor(
    @Named("auth") private val fbAuth: FirebaseAuth
) : AuthRepo {
    override val auth = fbAuth
    override val user: FirebaseUser?
        get() = super.user
    override suspend fun signIn(
        email: String,
        password: String,
        channel: Channel<LoginScreenViewModel.SignInResult>,
        scope: CoroutineScope
    ) : Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            scope.launch {
                channel.send(LoginScreenViewModel.SignInResult.Success)
            }
        }.addOnFailureListener{
            scope.launch {
                channel.send(LoginScreenViewModel.SignInResult.Failure(it.localizedMessage))
            }
        }
    }
    override fun signOut() {
        auth.signOut()
    }
}