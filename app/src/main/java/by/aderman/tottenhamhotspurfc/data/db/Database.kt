package by.aderman.tottenhamhotspurfc.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.aderman.tottenhamhotspurfc.data.dto.news.ArticleLocal

@Database(entities = [ArticleLocal::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao
}