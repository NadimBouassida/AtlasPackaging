package com.nadim.atlaspackaging.daily_production_feature.presentation

sealed class Events{
    object DateChange: Events()
    object ConductorChange: Events()
    object PostChange: Events()
    object ArticleChange: Events()
    object ClientChange: Events()
    object LotChange: Events()
    object SecondaryLotChange: Events()
    object ProductionChange: Events()
    object WasteChange: Events()
    object CommentaryChange: Events()
    object Submit: Events()
}
