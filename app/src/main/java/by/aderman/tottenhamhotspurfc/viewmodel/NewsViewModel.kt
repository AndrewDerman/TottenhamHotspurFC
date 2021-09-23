package by.aderman.tottenhamhotspurfc.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aderman.tottenhamhotspurfc.app.App
import by.aderman.tottenhamhotspurfc.models.Article
import by.aderman.tottenhamhotspurfc.models.NewsResponse
import by.aderman.tottenhamhotspurfc.repository.NewsRepository
import by.aderman.tottenhamhotspurfc.util.Constants
import by.aderman.tottenhamhotspurfc.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository
    private var newsResponse: NewsResponse? = null
    private var responseReceived = false
    var newsPage = 1

    private val _newsLiveData = MutableLiveData<Resource<NewsResponse>>()
    val newsLiveData: LiveData<Resource<NewsResponse>>
        get() = _newsLiveData

    private val _responseReceivedLiveData = MutableLiveData<Boolean>()
    val responseReceivedLiveData: LiveData<Boolean>
        get() = _responseReceivedLiveData

    init {
        val articleDao = getApplication<App>().database.getArticleDao()
        repository = NewsRepository(articleDao)
        getAllNews()
    }

    fun getAllNews() = viewModelScope.launch(Dispatchers.IO) {
        _newsLiveData.postValue(Resource.Loading())
        if (hasInternetConnection()) {
            val response = repository.getAllNews(page = newsPage)
            _newsLiveData.postValue(checkResponse(response))
        } else {
            _newsLiveData.postValue(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))
        }
    }

    fun showSavedArticles() = repository.getAllSavedArticles()

    fun saveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        repository.addArticleToSaved(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteSavedArticle(article)
    }

    /*
        обработка запроса для правильного отображения в адаптере
        для пагинации сохраняем все загруженные данные в newsResponse
    */

    private fun checkResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                newsPage++
                if (newsResponse == null) {
                    newsResponse = resultResponse
                } else {
                    resultResponse.articles?.let { newsResponse?.articles?.addAll(it) }
                }
                return Resource.Success(newsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun changeResponseReceivedStatus(value: Boolean) {
        responseReceived = value
        _responseReceivedLiveData.postValue(responseReceived)
    }

    // проверка подключения к интернету начиная с 23 апи

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<App>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}