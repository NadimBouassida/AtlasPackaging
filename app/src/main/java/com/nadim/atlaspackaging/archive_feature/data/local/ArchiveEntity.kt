package com.nadim.atlaspackaging.archive_feature.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_table")
data class ArchiveEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val machine: String = "",
    val date: String = "",
    val conductor: String = "",
    val article: String = "",
    val post: String = "",
    val client: String = "",
    val lot: String = "",
    val secondaryLot: String = "",
    val production: String = "",
    val waste: String = "",
    val commentary: String = "",
    val time: String = "",
)
