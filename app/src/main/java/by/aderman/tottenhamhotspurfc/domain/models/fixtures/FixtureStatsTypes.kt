package by.aderman.tottenhamhotspurfc.domain.models.fixtures

enum class FixtureStatsTypes(val value: String) {
    SONG("Shots on Goal"),
    SOFFG("Shots off Goal"),
    SI("Shots insidebox"),
    SO("Shots outsidebox"),
    TS("Total Shots"),
    BS("Blocked Shots"),
    F("Fouls"),
    CK("Corner Kicks"),
    O("Offsides"),
    BP("Ball Possession"),
    YC("Yellow Cards"),
    RC("Red Cards"),
    GS("Goalkeeper Saves"),
    TP("Total passes"),
    PA("Passes accurate"),
    P("Passes %")
}