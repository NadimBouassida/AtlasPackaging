package com.nadim.atlaspackaging.daily_production_feature.domain.model

data class DataModel(
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
