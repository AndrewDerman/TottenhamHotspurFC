package by.aderman.tottenhamhotspurfc.app

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import by.aderman.tottenhamhotspurfc.di.*
import by.aderman.tottenhamhotspurfc.notifications.NotificationHelper
import by.aderman.tottenhamhotspurfc.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        createNotificationsChannel()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            fragmentFactory()
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

    private fun createNotificationsChannel() {
        NotificationHelper.createNotificationChannel(
            this,
            Constants.NOTIFICATIONS_CHANNEL_ID,
            Constants.NOTIFICATIONS_CHANNEL_NAME,
            NotificationManagerCompat.IMPORTANCE_DEFAULT
        )
    }
}