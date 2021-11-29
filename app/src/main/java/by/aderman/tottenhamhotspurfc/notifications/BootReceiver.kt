package by.aderman.tottenhamhotspurfc.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import by.aderman.tottenhamhotspurfc.domain.usecases.fixtures.GetSavedFixturesUseCase
import by.aderman.tottenhamhotspurfc.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class BootReceiver : BroadcastReceiver(), KoinComponent {

    private val getSavedFixturesUseCase by inject<GetSavedFixturesUseCase> { parametersOf() }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent?.action == Constants.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.IO).launch {
                val fixturesList = getSavedFixturesUseCase.invoke()
                for (fixture in fixturesList) {
                    AlarmScheduler.scheduleAlarm(context, fixture)
                }
            }
        }
    }
}