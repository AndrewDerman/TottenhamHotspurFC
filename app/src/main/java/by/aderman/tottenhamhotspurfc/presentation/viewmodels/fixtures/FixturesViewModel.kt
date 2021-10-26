package by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.app.App
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureInfo
import by.aderman.tottenhamhotspurfc.domain.usecases.fixtures.GetFixtureInfoUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.fixtures.GetFixturesUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.fixtures.GetResultsUseCase
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.BasicViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FixturesViewModel(
    private val getFixturesUseCase: GetFixturesUseCase,
    private val getResultsUseCase: GetResultsUseCase,
    private val getFixtureInfoUseCase: GetFixtureInfoUseCase,
    application: Application
) : BasicViewModel(application) {

    private val _fixturesLiveData = MutableLiveData<Result<List<Fixture>>>()
    val fixturesLiveData: LiveData<Result<List<Fixture>>>
        get() = _fixturesLiveData

    private val _resultsLiveData = MutableLiveData<Result<List<Fixture>>>()
    val resultsLiveData: LiveData<Result<List<Fixture>>>
        get() = _resultsLiveData

    private val _fixtureInfoLiveData = MutableLiveData<Result<FixtureInfo>>()
    val fixtureInfoLiveData: LiveData<Result<FixtureInfo>>
        get() = _fixtureInfoLiveData

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
}