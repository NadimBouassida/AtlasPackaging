package com.nadim.atlaspackaging.archive_feature.data.mapper

import com.nadim.atlaspackaging.archive_feature.data.local.ArchiveEntity
import com.nadim.atlaspackaging.archive_feature.domain.model.ArchiveModel

fun ArchiveModel.toArchiveEntity(archiveModel: ArchiveModel): ArchiveEntity{
    return ArchiveEntity(
        machine = machine,
        date = date,
        conductor = conductor,
        article = article,
        post = post,
        client = client,
        lot = lot,
        secondaryLot = secondaryLot,
        production = production,
        waste = waste,
        commentary = commentary,
        time = time
    )
}


fun ArchiveEntity.toArchiveModel(archiveEntity: ArchiveEntity): ArchiveModel{
    return ArchiveModel(
        machine = machine,
        date = date,
        conductor = conductor,
        article = article,
        post = post,
        client = client,
        lot = lot,
        secondaryLot = secondaryLot,
        production = production,
        waste = waste,
        commentary = commentary,
        time = time
    )
}