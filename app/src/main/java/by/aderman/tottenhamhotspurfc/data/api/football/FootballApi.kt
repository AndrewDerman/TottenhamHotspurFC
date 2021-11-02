package by.aderman.tottenhamhotspurfc.data.api.football

import by.aderman.tottenhamhotspurfc.data.dto.fixtureinfo.FixtureInfoResponse
import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixturesResponse
import by.aderman.tottenhamhotspurfc.data.dto.player.PlayerResponse
import by.aderman.tottenhamhotspurfc.data.dto.standings.StandingsResponse
import by.aderman.tottenhamhotspurfc.data.dto.team.TeamResponse
import by.aderman.tottenhamhotspurfc.data.dto.topassists.TopAssistsResponse
import by.aderman.tottenhamhotspurfc.data.dto.topscorers.TopScorersResponse
import by.aderman.tottenhamhotspurfc.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("players/squads")
    suspend fun getTeamSquad(@Query("team") teamId: Int = Constants.FOOTBALL_TEAM_ID): Response<TeamResponse>

    @GET("players")
    suspend fun getPlayerStatistic(
        @Query("id") playerId: Int,
        @Query("season") season: Int = Constants.FOOTBALL_CURRENT_SEASON
    ): Response<PlayerResponse>

    @GET("standings")
    suspend fun getLeagueTable(
        @Query("league") leagueId: Int = Constants.FOOTBALL_LEAGUE_ID,
        @Query("season") season: Int = Constants.FOOTBALL_CURRENT_SEASON
    ): Response<StandingsResponse>

    @GET("players/topscorers")
    suspend fun getTopScorers(
        @Query("league") leagueId: Int = Constants.FOOTBALL_LEAGUE_ID,
        @Query("season") season: Int = Constants.FOOTBALL_CURRENT_SEASON
    ): Response<TopScorersResponse>

    @GET("players/topassists")
    suspend fun getTopAssists(
        @Query("league") leagueId: Int = Constants.FOOTBALL_LEAGUE_ID,
        @Query("season") season: Int = Constants.FOOTBALL_CURRENT_SEASON
    ): Response<TopAssistsResponse>

    @GET("fixtures")
    suspend fun getFixtures(
        @Query("league") leagueId: Int = Constants.FOOTBALL_LEAGUE_ID,
        @Query("season") season: Int = Constants.FOOTBALL_CURRENT_SEASON,
        @Query("team") teamId: Int = Constants.FOOTBALL_TEAM_ID,
        @Query("from") fromDate: String,
        @Query("to") toDate: String = Constants.FIXTURES_END_SEASON_DATE
    ): Response<FixturesResponse>

    @GET("fixtures")
    suspend fun getResults(
        @Query("league") leagueId: Int = Constants.FOOTBALL_LEAGUE_ID,
        @Query("season") season: Int = Constants.FOOTBALL_CURRENT_SEASON,
        @Query("team") teamId: Int = Constants.FOOTBALL_TEAM_ID,
        @Query("from") fromDate: String = Constants.FIXTURES_START_SEASON_DATE,
        @Query("to") toDate: String
    ): Response<FixturesResponse>

    @GET("fixtures")
    suspend fun getFixtureInfo(
        @Query("id") fixtureId: Int
    ): Response<FixtureInfoResponse>
}