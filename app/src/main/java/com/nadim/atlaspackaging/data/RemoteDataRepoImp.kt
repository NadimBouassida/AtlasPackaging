package com.nadim.atlaspackaging.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nadim.atlaspackaging.domain.RemoteDataRepo
import com.nadim.atlaspackaging.login_feature.presentation.LoginScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class RemoteDataRepoImp @Inject constructor(
    @Named("db") private val db: FirebaseDatabase,
    @Named("auth") private val fbAuth: FirebaseAuth
) : RemoteDataRepo {
    override val auth = fbAuth
    override fun signOut() {
        auth.signOut()
    }
    override fun signInWithEmailAndPassword(
        email: String,
        password: String,
        scope: CoroutineScope,
        channel: Channel<LoginScreenViewModel.SignInResult>
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            scope.launch {
                withContext(Dispatchers.Default) {
                    channel.send(LoginScreenViewModel.SignInResult.Success)
                }
            }
        }.addOnFailureListener {
            scope.launch {
                withContext(Dispatchers.Default) {
                    channel.send(LoginScreenViewModel.SignInResult.Failure(it.localizedMessage))
                }
            }
        }
    }
}