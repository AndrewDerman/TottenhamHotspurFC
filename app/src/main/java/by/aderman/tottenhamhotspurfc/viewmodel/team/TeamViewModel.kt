package by.aderman.tottenhamhotspurfc.viewmodel.team

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.app.App
import by.aderman.tottenhamhotspurfc.models.team.TeamResponse
import by.aderman.tottenhamhotspurfc.repository.Repository
import by.aderman.tottenhamhotspurfc.util.Constants
import by.aderman.tottenhamhotspurfc.util.Resource
import by.aderman.tottenhamhotspurfc.viewmodel.BasicViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class TeamViewModel(private val repository: Repository, application: Application) :
    BasicViewModel(application) {

    private val _teamLiveData = MutableLiveData<Resource<TeamResponse>>()
    val teamLiveData: LiveData<Resource<TeamResponse>>
        get() = _teamLiveData

    init {
        getTeamSquad()
    }

    private fun getTeamSquad() = viewModelScope.launch(Dispatchers.IO) {
        _teamLiveData.postValue(Resource.Loading())
        if (hasInternetConnection()) {
            val response = repository.getTeamSquad()
            _teamLiveData.postValue(checkResponse(response))
        } else {
            _teamLiveData.postValue(
                Resource.Error(
                    getApplication<App>()
                        .getString(R.string.error_no_internet_connection)
                )
            )
        }
    }

    private fun checkResponse(response: Response<TeamResponse>): Resource<TeamResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}