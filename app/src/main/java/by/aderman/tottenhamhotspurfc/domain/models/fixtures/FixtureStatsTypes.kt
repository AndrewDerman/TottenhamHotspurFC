package by.aderman.tottenhamhotspurfc.domain.models.fixtures

enum class FixtureStatsTypes(val value: String, val position: Int) {
    SONG("Shots on Goal", 0),
    SOFFG("Shots off Goal", 1),
    SI("Shots insidebox", 2),
    SO("Shots outsidebox", 3),
    TS("Total Shots", 4),
    BS("Blocked Shots", 5),
    F("Fouls", 6),
    CK("Corner Kicks", 7),
    O("Offsides", 8),
    BP("Ball Possession", 9),
    YC("Yellow Cards", 10),
    RC("Red Cards", 11),
    GS("Goalkeeper Saves", 12),
    TP("Total passes", 13),
    PA("Passes accurate", 14),
    P("Passes %", 15)
}