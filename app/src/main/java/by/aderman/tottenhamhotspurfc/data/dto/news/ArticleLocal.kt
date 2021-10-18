package by.aderman.tottenhamhotspurfc.data.dto.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.aderman.tottenhamhotspurfc.utils.Constants

@Entity(tableName = Constants.ARTICLE_ENTITY_TABLE_NAME)
data class ArticleLocal(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceId: String,
    val sourceName: String,
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String
)