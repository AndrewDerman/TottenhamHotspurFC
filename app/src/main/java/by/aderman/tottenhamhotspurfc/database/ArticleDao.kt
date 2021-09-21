package by.aderman.tottenhamhotspurfc.database

import androidx.lifecycle.LiveData
import androidx.room.*
import by.aderman.tottenhamhotspurfc.models.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllSavedArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addArticleToSaved(article: Article)

    @Delete
    suspend fun deleteSavedArticle(article: Article)
}