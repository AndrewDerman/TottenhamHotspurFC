package by.aderman.tottenhamhotspurfc.domain.models.fixtures

enum class EventDetails(val value: String) {
    NG("Normal Goal"),
    OG("Own Goal"),
    P("Penalty"),
    MP("Missed Penalty"),
    YC("Yellow Card"),
    SYC("Second Yellow card"),
    RC("Red card")
}