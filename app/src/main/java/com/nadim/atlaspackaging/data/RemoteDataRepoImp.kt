package com.nadim.atlaspackaging.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nadim.atlaspackaging.domain.RemoteDataRepo
import javax.inject.Inject
import javax.inject.Named

class RemoteDataRepoImp @Inject constructor(
    @Named("db") private val db: FirebaseDatabase,
    @Named("auth") private val auth: FirebaseAuth
) : RemoteDataRepo {
    override fun signOut() {
        auth.signOut()
    }
}