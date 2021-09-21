package by.aderman.tottenhamhotspurfc.app

import android.app.Application
import androidx.room.Room
import by.aderman.tottenhamhotspurfc.database.Database

class App : Application() {

    lateinit var database: Database

    override fun onCreate() {
        super.onCreate()
        database =
            Room.databaseBuilder(applicationContext, Database::class.java, "Database")
                .build()
    }
}