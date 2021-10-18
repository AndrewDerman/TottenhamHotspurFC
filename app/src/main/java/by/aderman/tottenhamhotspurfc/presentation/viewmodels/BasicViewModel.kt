package by.aderman.tottenhamhotspurfc.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.aderman.tottenhamhotspurfc.app.App

abstract class BasicViewModel(application: Application) : AndroidViewModel(application) {

    private var responseReceived = false

    private val _responseReceivedLiveData = MutableLiveData<Boolean>()
    val responseReceivedLiveData: LiveData<Boolean>
        get() = _responseReceivedLiveData

    fun changeResponseReceivedStatus(value: Boolean) {
        responseReceived = value
        _responseReceivedLiveData.postValue(responseReceived)
    }

    // проверка подключения к интернету начиная с 23 апи

    protected fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<App>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}