package com.nadim.atlaspackaging.daily_production.presentation

sealed class DailyProductionEvents{
    object DateChange: DailyProductionEvents()
    object ConductorChange: DailyProductionEvents()
    object PostChange: DailyProductionEvents()
    object ClientChange: DailyProductionEvents()
    object ArticleChange: DailyProductionEvents()
    object LotChange: DailyProductionEvents()
    object ProductionChange: DailyProductionEvents()
    object WasteChange: DailyProductionEvents()
    object CommentaryChange: DailyProductionEvents()
    object Submit: DailyProductionEvents()
}
