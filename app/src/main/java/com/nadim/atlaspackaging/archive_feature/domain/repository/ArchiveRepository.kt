package com.nadim.atlaspackaging.archive_feature.domain.repository

import com.nadim.atlaspackaging.archive_feature.data.local.ArchiveEntity
import com.nadim.atlaspackaging.archive_feature.domain.model.ArchiveModel

interface ArchiveRepository {

    suspend fun getAll(): List<ArchiveEntity>
    suspend fun insertArchiveUnit(archiveModel: ArchiveModel)
    suspend fun deleteArchiveUnit()

}