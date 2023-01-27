package com.nadim.atlaspackaging.main_feature.presentation


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nadim.atlaspackaging.domain.RemoteDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainScreenViewModel @Inject constructor (
    @Named("db") private val db: FirebaseDatabase,
    @Named("auth") private val auth: FirebaseAuth,
    private val remoteDataRepo: RemoteDataRepo
        ) : ViewModel() {

    // the list is located on the firebase realtime database so it will be easy
    // to manipulate without having to change the code here
    private val _machineList = mutableStateListOf("Flexo")
    val machineList: SnapshotStateList<String> = _machineList

    // this variable is used to give the appropriate machine name based on the user's email
    private val _user = mutableStateOf("")
    val user : State <String> = _user

    init {
        val email = auth.currentUser?.email

        if(!email.isNullOrEmpty()) _user.value = email.removeSuffix("@atlaspackaging.com")
            .capitalize(Locale.current)

        viewModelScope.launch (Dispatchers.IO){
            db.getReference("machine").keepSynced(true)
            db.getReference("machine")
            .addValueEventListener( object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { child ->
                        if(child.value.toString() == "Flexo") return@forEach
                        _machineList.add(child.value.toString())
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.i("DatabaseError", error.message)
                }
            })
        }
    }
    fun logOut(){
        remoteDataRepo.signOut()
    }
}