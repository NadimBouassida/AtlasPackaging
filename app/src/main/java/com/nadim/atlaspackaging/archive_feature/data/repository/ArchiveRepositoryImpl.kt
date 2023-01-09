package com.nadim.atlaspackaging.archive_feature.data.repository

import com.nadim.atlaspackaging.archive_feature.data.local.ArchiveDatabase
import com.nadim.atlaspackaging.archive_feature.data.local.ArchiveEntity
import com.nadim.atlaspackaging.archive_feature.data.mapper.toArchiveEntity
import com.nadim.atlaspackaging.archive_feature.domain.model.ArchiveModel
import com.nadim.atlaspackaging.archive_feature.domain.repository.ArchiveRepository

class ArchiveRepositoryImpl(
    private val db: ArchiveDatabase
) : ArchiveRepository {

    override suspend fun getAll(): List<ArchiveEntity> {
        return db.archiveDao().getAll()
    }

    override suspend fun insertArchiveUnit(archiveModel: ArchiveModel) {
        db.archiveDao().insertArchiveUnit(archiveModel.toArchiveEntity(archiveModel))
    }

    override suspend fun deleteArchiveUnit() {
        TODO("Not yet implemented")
    }
}