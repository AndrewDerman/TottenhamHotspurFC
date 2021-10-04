package by.aderman.tottenhamhotspurfc.viewmodel.news

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aderman.tottenhamhotspurfc.app.App
import by.aderman.tottenhamhotspurfc.models.news.Article
import by.aderman.tottenhamhotspurfc.models.news.NewsResponse
import by.aderman.tottenhamhotspurfc.repository.Repository
import by.aderman.tottenhamhotspurfc.util.Constants
import by.aderman.tottenhamhotspurfc.util.Resource
import by.aderman.tottenhamhotspurfc.viewmodel.BasicViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(application: Application) : BasicViewModel(application) {

    private val repository: Repository
    private var newsResponse: NewsResponse? = null
    var newsPage = 1

    private val _newsLiveData = MutableLiveData<Resource<NewsResponse>>()
    val newsLiveData: LiveData<Resource<NewsResponse>>
        get() = _newsLiveData

    init {
        val articleDao = getApplication<App>().database.getArticleDao()
        repository = Repository().also {
            it.articleDao = articleDao
        }
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
}