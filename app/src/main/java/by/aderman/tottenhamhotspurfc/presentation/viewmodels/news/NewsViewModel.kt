package by.aderman.tottenhamhotspurfc.presentation.viewmodels.news

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.app.App
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.news.Article
import by.aderman.tottenhamhotspurfc.domain.usecases.news.DeleteArticleUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.news.GetBookmarksUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.news.GetNewsUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.news.SaveArticleUseCase
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.BasicViewModel
import by.aderman.tottenhamhotspurfc.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val saveArticleUseCase: SaveArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    application: Application
) :
    BasicViewModel(application) {

    private var articlesList: MutableList<Article> = mutableListOf()
    private var newsPage = Constants.NEWS_API_QUERY_PAGE

    private val _newsLiveData = MutableLiveData<Result<List<Article>>>()
    val newsLiveData: LiveData<Result<List<Article>>>
        get() = _newsLiveData

    fun getNews() = viewModelScope.launch(Dispatchers.IO) {
        _newsLiveData.postValue(Result.Loading())
        if (hasInternetConnection()) {
            when (val response = getNewsUseCase.invoke(page = newsPage)) {
                is Result.Success -> {
                    newsPage++
                    response.data?.let { articlesList.addAll(it) }
                    _newsLiveData.postValue(Result.Success(articlesList.toList()))
                }
                is Result.Error -> {
                    _newsLiveData.postValue(response.message?.let { Result.Error(it) })
                }
            }
        } else {
            _newsLiveData.postValue(
                Result.Error(
                    getApplication<App>()
                        .getString(R.string.error_no_internet_connection)
                )
            )
        }
    }

    fun getBookmarks() = getBookmarksUseCase.invoke()

    fun saveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        saveArticleUseCase.invoke(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        deleteArticleUseCase.invoke(article)
    }

    fun resetNewsList(){
        newsPage = Constants.NEWS_API_QUERY_PAGE
        articlesList.clear()
    }
}