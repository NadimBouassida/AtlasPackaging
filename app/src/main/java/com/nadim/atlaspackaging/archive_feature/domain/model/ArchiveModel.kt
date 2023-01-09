package com.nadim.atlaspackaging.archive_feature.domain.model


data class ArchiveModel(
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
