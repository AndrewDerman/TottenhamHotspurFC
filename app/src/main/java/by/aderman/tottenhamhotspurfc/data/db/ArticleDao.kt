package by.aderman.tottenhamhotspurfc.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import by.aderman.tottenhamhotspurfc.data.dto.news.ArticleLocal

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): LiveData<List<ArticleLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: ArticleLocal)

    @Delete
    suspend fun deleteArticle(article: ArticleLocal)
}