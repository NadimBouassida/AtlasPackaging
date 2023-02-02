package com.nadim.atlaspackaging.domain

interface ProductionDataRepo {
    suspend fun getMachinesList() : MutableList<String>
}