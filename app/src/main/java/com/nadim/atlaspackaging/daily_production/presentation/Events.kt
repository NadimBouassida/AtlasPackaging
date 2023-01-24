package com.nadim.atlaspackaging.daily_production.presentation

sealed class Events{
    object DateChange: Events()
    object ConductorChange: Events()
    object PostChange: Events()
    object ClientChange: Events()
    object ArticleChange: Events()
    object LotChange: Events()
    object ProductionChange: Events()
    object WasteChange: Events()
    object CommentaryChange: Events()
    object Submit: Events()
}
