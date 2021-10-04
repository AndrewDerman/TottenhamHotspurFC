package by.aderman.tottenhamhotspurfc.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.aderman.tottenhamhotspurfc.models.news.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao
}