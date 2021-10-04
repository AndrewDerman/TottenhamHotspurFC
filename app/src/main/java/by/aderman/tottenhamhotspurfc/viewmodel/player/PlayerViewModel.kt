package by.aderman.tottenhamhotspurfc.viewmodel.player

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aderman.tottenhamhotspurfc.models.player.PlayerResponse
import by.aderman.tottenhamhotspurfc.repository.Repository
import by.aderman.tottenhamhotspurfc.util.Constants
import by.aderman.tottenhamhotspurfc.util.Resource
import by.aderman.tottenhamhotspurfc.viewmodel.BasicViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PlayerViewModel(application: Application) : BasicViewModel(application) {

    private val repository = Repository()
    private var isGoalkeeper = false

    private val _playerLiveData = MutableLiveData<Resource<PlayerResponse>>()
    val playerLiveData: LiveData<Resource<PlayerResponse>>
        get() = _playerLiveData

    private val _isGoalkeeperLiveData = MutableLiveData<Boolean>()
    val isGoalkeeperLiveData: LiveData<Boolean>
        get() = _isGoalkeeperLiveData


    fun getPlayerStatistics(playerId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _playerLiveData.postValue(Resource.Loading())
        if (hasInternetConnection()) {
            val response = repository.getPlayerStatistics(playerId)
            _playerLiveData.postValue(checkResponse(response))
        } else {
            _playerLiveData.postValue(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))
        }
    }

    private fun checkResponse(response: Response<PlayerResponse>): Resource<PlayerResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun changeGoalkeeperStatus(value: Boolean) {
        isGoalkeeper = value
        _isGoalkeeperLiveData.postValue(isGoalkeeper)
    }
}