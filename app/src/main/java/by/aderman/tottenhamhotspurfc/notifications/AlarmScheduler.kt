package by.aderman.tottenhamhotspurfc.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.utils.Constants
import java.util.*

object AlarmScheduler {

    fun scheduleAlarm(context: Context, fixture: Fixture) {

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = createPendingIntent(context, fixture)

        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = fixture.timestamp.toLong() * 1000
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
    }

    private fun createPendingIntent(context: Context, fixture: Fixture): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            action = Constants.NOTIFICATIONS_ACTION
            type = context.getString(
                R.string.notifications_intent_type,
                fixture.id,
                fixture.teams.home.id,
                fixture.teams.away.id
            )
            putExtra(
                Constants.NOTIFICATIONS_TITLE,
                context.getString(R.string.notifications_fixtures_title)
            )
            putExtra(
                Constants.NOTIFICATIONS_MESSAGE,
                context.getString(
                    R.string.notifications_fixtures_message,
                    fixture.teams.home.name,
                    fixture.teams.away.name,
                    fixture.venue.name
                )
            )
        }
        return PendingIntent.getBroadcast(
            context,
            Constants.NOTIFICATIONS_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}