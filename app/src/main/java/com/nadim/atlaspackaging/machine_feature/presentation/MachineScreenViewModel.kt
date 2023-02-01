package com.nadim.atlaspackaging.machine_feature.presentation

import androidx.lifecycle.ViewModel
import com.nadim.atlaspackaging.domain.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MachineScreenViewModel @Inject constructor (
    private val remoteDataRepo: AuthRepo) : ViewModel() {
    fun logOut(){
        remoteDataRepo.signOut()
    }
}