package com.nadim.atlaspackaging.main_feature.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainScreenViewModel @Inject constructor (
    @Named("auth") private val auth: FirebaseAuth
        ) : ViewModel() {
    val machineList = listOf(
        "Flexo",
        "Découpe1",
        "Découpe2",
        "Découpe3",
        "Sealer"
    )

    // this variable is used to display the email address in the top app bar
    private val _userEmail = mutableStateOf(auth.currentUser?.email.toString())
    val userEmail : State <String> = _userEmail


    // this variable is used to give the appropriate machine name based on the user email
    private val _user = mutableStateOf("")
    val user : State <String> = _user

    init {
        val email = auth.currentUser?.email
//        _user.value = when (auth.currentUser?.email.toString()){
//            "sealer@atlaspackaging.com" -> "Hot Sealer"
//            "flexo@atlaspackaging.com"  -> "Flexo"
//            "decoupeone@atlaspackaging.com" -> "Découpe 1"
//            "decoupetwo@atlaspackaging.com" -> "Découpe 2"
//            "decoupethree@atlaspackaging.com" -> "Découpe 3"
//            "admin@atlaspackaging.com" -> "Admin"
//            else -> "null"
        if(!email.isNullOrEmpty()) _user.value = email.removeSuffix("@atlaspackaging.com")
            .capitalize(Locale.current)
    }

    fun logOut(){
        auth.signOut()
    }
}