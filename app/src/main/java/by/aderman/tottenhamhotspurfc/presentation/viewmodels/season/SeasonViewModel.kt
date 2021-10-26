package by.aderman.tottenhamhotspurfc.presentation.viewmodels.season

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.app.App
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopAssistant
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopScorer
import by.aderman.tottenhamhotspurfc.domain.models.season.Standing
import by.aderman.tottenhamhotspurfc.domain.usecases.season.GetLeagueTableUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.season.GetTopAssistsUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.season.GetTopScorersUseCase
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.BasicViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeasonViewModel(
    private val getLeagueTableUseCase: GetLeagueTableUseCase,
    private val getTopScorersUseCase: GetTopScorersUseCase,
    private val getTopAssistsUseCase: GetTopAssistsUseCase,
    application: Application
) : BasicViewModel(application) {

    private val _tableLiveData = MutableLiveData<Result<List<Standing>>>()
    val tableLiveData: LiveData<Result<List<Standing>>>
        get() = _tableLiveData

    private val _topScorersLiveData = MutableLiveData<Result<List<PlayerTopScorer>>>()
    val topScorersLiveData: LiveData<Result<List<PlayerTopScorer>>>
        get() = _topScorersLiveData

    private val _topAssistantsLiveData = MutableLiveData<Result<List<PlayerTopAssistant>>>()
    val topAssistantsLiveData: LiveData<Result<List<PlayerTopAssistant>>>
        get() = _topAssistantsLiveData

    fun getLeagueTable() = viewModelScope.launch(Dispatchers.IO) {
        _tableLiveData.postValue(Result.Loading())
        if (hasInternetConnection()) {
            when (val response = getLeagueTableUseCase.invoke()) {
                is Result.Success -> _tableLiveData.postValue(response)
                is Result.Error -> _tableLiveData.postValue(response.message?.let { Result.Error(it) })
            }
        } else {
            _tableLiveData.postValue(
                Result.Error(
                    getApplication<App>()
                        .getString(
                            R.string.error_no_internet_connection
                        )
                )
            )
        }
    }

    fun getTopScorers() = viewModelScope.launch(Dispatchers.IO) {
        _topScorersLiveData.postValue(Result.Loading())
        if (hasInternetConnection()) {
            when (val response = getTopScorersUseCase.invoke()) {
                is Result.Success -> _topScorersLiveData.postValue(response)
                is Result.Error -> _topScorersLiveData.postValue(response.message?.let {
                    Result.Error(it)
                })
            }
        } else {
            _topScorersLiveData.postValue(
                Result.Error(
                    getApplication<App>()
                        .getString(
                            R.string.error_no_internet_connection
                        )
                )
            )
        }
    }

    fun getTopAssists() = viewModelScope.launch(Dispatchers.IO) {
        _topAssistantsLiveData.postValue(Result.Loading())
        if (hasInternetConnection()) {
            when (val response = getTopAssistsUseCase.invoke()) {
                is Result.Success -> _topAssistantsLiveData.postValue(response)
                is Result.Error -> _topAssistantsLiveData.postValue(response.message?.let {
                    Result.Error(it)
                })
            }
        } else {
            _topAssistantsLiveData.postValue(
                Result.Error(
                    getApplication<App>()
                        .getString(
                            R.string.error_no_internet_connection
                        )
                )
            )
        }
    }
}