package com.nadim.atlaspackaging.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nadim.atlaspackaging.domain.RemoteDataRepo
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
}