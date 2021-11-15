package by.aderman.tottenhamhotspurfc.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.presentation.ui.activities.MainActivity
import by.aderman.tottenhamhotspurfc.utils.Constants

object NotificationHelper {

    fun createNotificationChannel(
        context: Context,
        channelId: String,
        channelName: String,
        importance: Int
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = Constants.NOTIFICATIONS_CHANNEL_DESC
                enableLights(true)
                enableVibration(true)
                setShowBadge(false)
            }
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotificationForFixture(context: Context, fixture: FixtureLocal) {
        val builder =
            NotificationCompat.Builder(context, Constants.NOTIFICATIONS_CHANNEL_ID).apply {
                setSmallIcon(R.mipmap.ic_launcher)
                setContentTitle(context.getString(R.string.notifications_fixtures_title))
                setContentText(
                    context.getString(
                        R.string.notifications_fixtures_message,
                        fixture.homeTeamName,
                        fixture.awayTeamName,
                        fixture.venueName
                    )
                )
                setAutoCancel(false)

                val intent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                val pendingIntent =
                    PendingIntent.getActivity(
                        context,
                        Constants.NOTIFICATIONS_REQUEST_CODE,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                setContentIntent(pendingIntent)
            }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(fixture.id, builder.build())
    }
}