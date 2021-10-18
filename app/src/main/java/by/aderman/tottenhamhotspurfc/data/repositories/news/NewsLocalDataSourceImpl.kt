package by.aderman.tottenhamhotspurfc.data.repositories.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import by.aderman.tottenhamhotspurfc.data.db.ArticleDao
import by.aderman.tottenhamhotspurfc.data.mappers.news.ArticleLocalMapper
import by.aderman.tottenhamhotspurfc.domain.models.news.Article

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao,
    private val mapper: ArticleLocalMapper
) : NewsLocalDataSource {

    override fun getArticles(): LiveData<List<Article>> {
        val savedArticles = articleDao.getSavedArticles()
        return Transformations.map(savedArticles) { list ->
            list.map {
                mapper.toArticle(it)
            }
        }
    }


    override suspend fun saveArticle(article: Article) =
        articleDao.saveArticle(mapper.toArticleLocal(article))

    override suspend fun deleteArticle(article: Article) =
        articleDao.deleteArticle(mapper.toArticleLocal(article))
}