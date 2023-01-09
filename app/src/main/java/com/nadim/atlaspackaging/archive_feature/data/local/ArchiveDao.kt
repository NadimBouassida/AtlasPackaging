package com.nadim.atlaspackaging.archive_feature.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArchiveDao {
    @Query("SELECT * FROM production_table")
    fun getAll(): List<ArchiveEntity>

    @Insert
    fun insertArchiveUnit(archiveEntity: ArchiveEntity)

    @Delete
    fun deleteArchiveUnit(archiveEntity: ArchiveEntity)
}