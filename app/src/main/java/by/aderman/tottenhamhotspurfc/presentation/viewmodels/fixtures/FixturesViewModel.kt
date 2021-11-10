package by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.app.App
import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureInfo
import by.aderman.tottenhamhotspurfc.domain.usecases.fixtures.*
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.BasicViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FixturesViewModel(
    private val getFixturesUseCase: GetFixturesUseCase,
    private val getResultsUseCase: GetResultsUseCase,
    private val getFixtureInfoUseCase: GetFixtureInfoUseCase,
    private val saveFixturesUseCase: SaveFixturesUseCase,
    private val deleteOldFixturesUseCase: DeleteOldFixturesUseCase,
    private val getSavedFixturesUseCase: GetSavedFixturesUseCase,
    private val updateFixtureUseCase: UpdateFixtureUseCase,
    private val getSavedResultsUseCase: GetSavedResultsUseCase,
    private val saveResultUseCase: SaveResultUseCase,
    application: Application
) : BasicViewModel(application) {

    private val _fixturesLiveData = MutableLiveData<Result<List<Fixture>>>()
    val fixturesLiveData: LiveData<Result<List<Fixture>>>
        get() = _fixturesLiveData

    private val _savedFixturesLiveData = MutableLiveData<List<FixtureLocal>>()
    val savedFixturesLiveData: LiveData<List<FixtureLocal>>
        get() = _savedFixturesLiveData

    private val _resultsLiveData = MutableLiveData<Result<List<Fixture>>>()
    val resultsLiveData: LiveData<Result<List<Fixture>>>
        get() = _resultsLiveData

    private val _savedResultsLiveData = MutableLiveData<List<Fixture>>()
    val savedResultsLiveData: LiveData<List<Fixture>>
        get() = _savedResultsLiveData

    private val _fixtureInfoLiveData = MutableLiveData<Result<FixtureInfo>>()
    val fixtureInfoLiveData: LiveData<Result<FixtureInfo>>
        get() = _fixtureInfoLiveData

    private val _statisticAvailableLiveData = MutableLiveData(false)
    val statisticAvailableLiveData: LiveData<Boolean>
        get() = _statisticAvailableLiveData

    private val _lineupsAvailableLiveData = MutableLiveData(false)
    val lineupsAvailableLiveData: LiveData<Boolean>
        get() = _lineupsAvailableLiveData

    private val _eventsAvailableLiveData = MutableLiveData(false)
    val eventsAvailableLiveData: LiveData<Boolean>
        get() = _eventsAvailableLiveData

    fun getFixtures(fromDate: String) = viewModelScope.launch(Dispatchers.IO) {
        _fixturesLiveData.postValue(Result.Loading())
        if (hasInternetConnection()) {
            when (val response = getFixturesUseCase.invoke(fromDate)) {
                is Result.Success -> {
                    _fixturesLiveData.postValue(response)
                }
                is Result.Error -> {
                    _fixturesLiveData.postValue(response.message?.let {
                        Result.Error(it)
                    })
                }
            }
        } else {
            _fixturesLiveData.postValue(
                Result.Error(
                    getApplication<App>()
                        .getString(
                            R.string.error_no_internet_connection
                        )
                )
            )
        }
    }

    fun getResults(toDate: String) = viewModelScope.launch(Dispatchers.IO) {
        _resultsLiveData.postValue(Result.Loading())
        if (hasInternetConnection()) {
            when (val response = getResultsUseCase.invoke(toDate)) {
                is Result.Success -> {
                    _resultsLiveData.postValue(response)
                }
                is Result.Error -> {
                    _resultsLiveData.postValue(response.message?.let {
                        Result.Error(it)
                    })
                }
            }
        } else {
            _resultsLiveData.postValue(
                Result.Error(
                    getApplication<App>()
                        .getString(
                            R.string.error_no_internet_connection
                        )
                )
            )
        }
    }

    fun getFixtureInfo(fixtureId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _fixtureInfoLiveData.postValue(Result.Loading())
        if (hasInternetConnection()) {
            when (val response = getFixtureInfoUseCase.invoke(fixtureId)) {
                is Result.Success -> {
                    _fixtureInfoLiveData.postValue(response)
                }
                is Result.Error -> {
                    _fixtureInfoLiveData.postValue(response.message?.let {
                        Result.Error(it)
                    })
                }
            }
        } else {
            _fixtureInfoLiveData.postValue(
                Result.Error(
                    getApplication<App>()
                        .getString(
                            R.string.error_no_internet_connection
                        )
                )
            )
        }
    }

    fun changeStatisticStatus(value: Boolean) = _statisticAvailableLiveData.postValue(value)
    fun changeLineupsStatus(value: Boolean) = _lineupsAvailableLiveData.postValue(value)
    fun changeEventsStatus(value: Boolean) = _eventsAvailableLiveData.postValue(value)

    fun saveFixtures(fixturesList: List<Fixture>) = viewModelScope.launch(Dispatchers.IO) {
        saveFixturesUseCase.invoke(fixturesList)
    }

    fun getSavedFixtures() = viewModelScope.launch(Dispatchers.IO) {
        deleteOldFixturesFromDatabase()
        _savedFixturesLiveData.postValue(getSavedFixturesUseCase.invoke())
    }

    private suspend fun deleteOldFixturesFromDatabase() {
        val timestamp = (System.currentTimeMillis() / 1000).toInt()
        deleteOldFixturesUseCase(timestamp)
    }

    fun updateFixture(fixtureLocal: FixtureLocal) = viewModelScope.launch(Dispatchers.IO) {
        updateFixtureUseCase.invoke(fixtureLocal)
    }

    fun getSavedResults() = viewModelScope.launch(Dispatchers.IO) {
        _savedResultsLiveData.postValue(getSavedResultsUseCase.invoke())
    }

    fun saveResult(fixture: Fixture) = viewModelScope.launch(Dispatchers.IO) {
        saveResultUseCase.invoke(fixture)
    }
}