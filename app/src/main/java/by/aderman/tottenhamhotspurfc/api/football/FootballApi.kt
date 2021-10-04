package by.aderman.tottenhamhotspurfc.api.football

import by.aderman.tottenhamhotspurfc.models.player.PlayerResponse
import by.aderman.tottenhamhotspurfc.models.team.TeamResponse
import by.aderman.tottenhamhotspurfc.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("players/squads")
    suspend fun getTeamSquad(@Query("team") teamId: Int = Constants.FOOTBALL_TEAM_ID): Response<TeamResponse>

    @GET("players")
    suspend fun getPlayerStatistics(
        @Query("id") playerId: Int,
        @Query("season") season: Int = Constants.FOOTBALL_CURRENT_SEASON
    ): Response<PlayerResponse>
}