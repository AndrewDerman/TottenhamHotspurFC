package by.aderman.tottenhamhotspurfc.domain.repositories

import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopAssistant
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopScorer
import by.aderman.tottenhamhotspurfc.domain.models.season.Standing

interface SeasonRepository {

    suspend fun getLeagueTable(): Result<List<Standing>>
    suspend fun getTopScorers(): Result<List<PlayerTopScorer>>
    suspend fun getTopAssists(): Result<List<PlayerTopAssistant>>
}