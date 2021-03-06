package by.aderman.tottenhamhotspurfc.domain.models.fixtures

data class Event(
    val time: EventTime,
    val type: String,
    val detail: String,
    val comments: String?,
    val team: EventTeam,
    val player: EventPlayer,
    val assist: EventAssistant,
)