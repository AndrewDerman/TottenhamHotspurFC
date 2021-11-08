package by.aderman.tottenhamhotspurfc.app

import android.app.Application
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.di.*
import by.aderman.tottenhamhotspurfc.notifications.NotificationHelper
import by.aderman.tottenhamhotspurfc.utils.Constants
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

        NotificationHelper.createNotificationChannel(
            this,
            Constants.NOTIFICATIONS_CHANNEL_ID,
            Constants.NOTIFICATIONS_CHANNEL_NAME,
            NotificationManagerCompat.IMPORTANCE_DEFAULT
        )
    }
}