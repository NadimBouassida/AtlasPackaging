package com.nadim.atlaspackaging.domain

import com.google.firebase.auth.FirebaseAuth

interface RemoteDataRepo {
    val auth : FirebaseAuth
    fun signOut()
}