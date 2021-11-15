package by.aderman.tottenhamhotspurfc.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.data.dto.fixtures.ResultLocal
import by.aderman.tottenhamhotspurfc.data.dto.news.ArticleLocal
import by.aderman.tottenhamhotspurfc.data.dto.team.PlayerLocal

@Database(
    entities = [ArticleLocal::class, FixtureLocal::class, ResultLocal::class, PlayerLocal::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
    abstract fun getFixtureDao(): FixtureDao
    abstract fun getResultDao(): ResultDao
    abstract fun getPlayerDao(): PlayerDao
}