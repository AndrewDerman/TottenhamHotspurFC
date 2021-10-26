package by.aderman.tottenhamhotspurfc.domain.models.fixtures

enum class FixtureStatus(val value: String) {
    TBD("Time To Be Defined"),
    NS("Not Started"),
    FH("First Half, Kick Off"),
    HT("Halftime"),
    SH("Second Half, 2nd Half Started"),
    ET("Extra Time"),
    P("Penalty In Progress"),
    FT("Match Finished"),
    AET("Match Finished After Extra Time"),
    PEN("Match Finished After Penalty"),
    BT("Break Time (in Extra Time)"),
    SUSP("Match Suspended"),
    INT("Match Interrupted"),
    PST("Match Postponed"),
    CANC("Match Cancelled"),
    ABD("Match Abandoned"),
    AWD("Technical Loss"),
    WO("WalkOver"),
    LIVE("In Progress")
}