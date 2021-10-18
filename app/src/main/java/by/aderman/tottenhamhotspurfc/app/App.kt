package by.aderman.tottenhamhotspurfc.app

import android.app.Application
import by.aderman.tottenhamhotspurfc.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    databaseModules,
                    apiModules,
                    repositoryModules,
                    applicationModules,
                    viewModelsModules
                )
            )
        }
    }
}