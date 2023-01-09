package com.nadim.atlaspackaging.archive_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArchiveEntity::class], version = 1)
abstract class ArchiveDatabase : RoomDatabase() {
    abstract fun archiveDao(): ArchiveDao
}