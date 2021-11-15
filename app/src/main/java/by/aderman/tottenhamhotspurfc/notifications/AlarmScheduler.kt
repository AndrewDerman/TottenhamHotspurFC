package by.aderman.tottenhamhotspurfc.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.utils.Constants
import java.util.*

object AlarmScheduler {

    fun scheduleAlarm(context: Context, fixture: FixtureLocal) {

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = createPendingIntent(context, fixture)

        val calendar = Calendar.getInstance(Locale.getDefault()).apply {
            timeInMillis = fixture.timestamp.toLong() * 1000
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
    }

    private fun createPendingIntent(context: Context, fixture: FixtureLocal): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            action = Constants.NOTIFICATIONS_ACTION
            type = context.getString(
                R.string.notifications_intent_type,
                fixture.id,
                fixture.homeTeamName,
                fixture.awayTeamName
            )
            putExtra(Constants.NOTIFICATIONS_KEY_ID, fixture.id)
        }
        return PendingIntent.getBroadcast(
            context,
            Constants.NOTIFICATIONS_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}