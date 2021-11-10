package by.aderman.tottenhamhotspurfc.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import by.aderman.tottenhamhotspurfc.domain.usecases.fixtures.GetFixtureForAlarmUseCase
import by.aderman.tottenhamhotspurfc.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class AlarmReceiver : BroadcastReceiver(), KoinComponent {

    private val getFixtureForAlarmUseCase by inject<GetFixtureForAlarmUseCase> { parametersOf() }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null && intent.action != null) {
            if (intent.action == Constants.NOTIFICATIONS_ACTION) {
                if (intent.extras != null) {
                    val fixtureId = intent.extras!!.getInt(Constants.FIXTURES_ID_KEY)
                    CoroutineScope(Dispatchers.IO).launch {
                        val fixtureForAlarm = getFixtureForAlarmUseCase.invoke(fixtureId)
                        NotificationHelper.createNotificationForFixture(context, fixtureForAlarm)
                    }
                }
            }
        }
    }
}