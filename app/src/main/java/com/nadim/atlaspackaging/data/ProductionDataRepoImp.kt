package com.nadim.atlaspackaging.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nadim.atlaspackaging.domain.ProductionDataRepo
import javax.inject.Inject
import javax.inject.Named

class ProductionDataRepoImp @Inject constructor(
    @Named("db") private val db: FirebaseDatabase
) : ProductionDataRepo {
    override suspend fun getMachinesList(): MutableList<String> {
        val machineList = mutableListOf<String>()
        db.getReference("machine").keepSynced(true)
        db.getReference("machine")
            .addValueEventListener( object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { child ->
                        if(child.value.toString() == "Flexo") return@forEach
                        machineList.add(child.value.toString())

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.i("DatabaseError", error.message)
                }
            })
        return machineList
    }
}