package by.aderman.tottenhamhotspurfc.data.api.football

import by.aderman.tottenhamhotspurfc.data.dto.player.PlayerResponse
import by.aderman.tottenhamhotspurfc.data.dto.season.StandingsResponse
import by.aderman.tottenhamhotspurfc.data.dto.team.TeamResponse
import by.aderman.tottenhamhotspurfc.data.dto.topassists.TopAssistsResponse
import by.aderman.tottenhamhotspurfc.data.dto.topscorer.TopScorersResponse
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
    ) : Response<TopAssistsResponse>
}