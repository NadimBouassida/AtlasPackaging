package com.nadim.atlaspackaging.main_feature.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadim.atlaspackaging.domain.AuthRepo
import com.nadim.atlaspackaging.domain.ProductionDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor (
    private val productionDataRepo: ProductionDataRepo,
    private val authRepo: AuthRepo
    ) : ViewModel() {
    // the list is located on the firebase realtime database so it will be easy
    // to manipulate without having to change the code here
    private val _machineList = mutableStateListOf("Flexo")
    val machineList: SnapshotStateList<String> = _machineList

    // this variable is used to give the appropriate machine name based on the user's email
    private val _user = mutableStateOf("")
    val user : State <String> = _user

    init {
        val email = authRepo.auth.currentUser?.email
        if(!email.isNullOrEmpty()) _user.value = email.removeSuffix("@atlaspackaging.com")
            .capitalize(Locale.current)
        viewModelScope.launch (Dispatchers.IO) {
            productionDataRepo.getMachinesList()
        }
    }
    fun logOut(){
        authRepo.signOut()
    }
}