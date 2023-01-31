package com.nadim.atlaspackaging.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


interface RemoteDataRepo {
    val auth : FirebaseAuth
    val user: FirebaseUser? get() = auth.currentUser
    fun signOut()

    suspend  fun signIn(email: String, password: String)
}