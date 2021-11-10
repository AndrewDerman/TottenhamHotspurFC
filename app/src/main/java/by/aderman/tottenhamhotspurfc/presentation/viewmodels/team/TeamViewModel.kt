package by.aderman.tottenhamhotspurfc.presentation.viewmodels.team

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.app.App
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.team.Player
import by.aderman.tottenhamhotspurfc.domain.models.team.PlayerWithStats
import by.aderman.tottenhamhotspurfc.domain.usecases.team.GetPlayerStatisticUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.team.GetSavedTeamSquadUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.team.GetTeamSquadUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.team.SavePlayerUseCase
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.BasicViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(
    private val getTeamSquadUseCase: GetTeamSquadUseCase,
    private val getPlayerStatisticUseCase: GetPlayerStatisticUseCase,
    private val getSavedTeamSquadUseCase: GetSavedTeamSquadUseCase,
    private val savePlayerUseCase: SavePlayerUseCase,
    application: Application
) :
    BasicViewModel(application) {

    private var isGoalkeeper = false

    private val _teamLiveData = MutableLiveData<Result<List<Player>>>()
    val teamLiveData: LiveData<Result<List<Player>>>
        get() = _teamLiveData

    private val _savedTeamLiveData = MutableLiveData<List<Player>>()
    val savedTeamLiveData: LiveData<List<Player>>
        get() = _savedTeamLiveData

    private val _playerLiveData = MutableLiveData<Result<PlayerWithStats>>()
    val playerLiveData: LiveData<Result<PlayerWithStats>>
        get() = _playerLiveData

    private val _isGoalkeeperLiveData = MutableLiveData<Boolean>()
    val isGoalkeeperLiveData: LiveData<Boolean>
        get() = _isGoalkeeperLiveData

    fun getRemoteTeamSquad() = viewModelScope.launch(Dispatchers.IO) {
        _teamLiveData.postValue(Result.Loading())
        if (hasInternetConnection()) {
            when (val response = getTeamSquadUseCase.invoke()) {
                is Result.Success -> _teamLiveData.postValue(response)
                is Result.Error -> _teamLiveData.postValue(response.message?.let { Result.Error(it) })
            }
        } else {
            _teamLiveData.postValue(
                Result.Error(
                    getApplication<App>()
                        .getString(R.string.error_no_internet_connection)
                )
            )
        }
    }

    fun getPlayerStatistic(playerId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _playerLiveData.postValue(Result.Loading())
        if (hasInternetConnection()) {
            when (val response = getPlayerStatisticUseCase.invoke(playerId)) {
                is Result.Success -> _playerLiveData.postValue(response)
                is Result.Error -> _playerLiveData.postValue(response.message?.let { Result.Error(it) })
            }

        } else {
            _playerLiveData.postValue(
                Result.Error(
                    getApplication<App>()
                        .getString(R.string.error_no_internet_connection)
                )
            )
        }
    }

    fun changeGoalkeeperStatus(value: Boolean) {
        isGoalkeeper = value
        _isGoalkeeperLiveData.postValue(isGoalkeeper)
    }

    fun getSavedTeamSquad() = viewModelScope.launch(Dispatchers.IO) {
        _savedTeamLiveData.postValue(getSavedTeamSquadUseCase.invoke())
    }

    fun savePlayer(player: Player) = viewModelScope.launch(Dispatchers.IO) {
        savePlayerUseCase.invoke(player)
    }
}