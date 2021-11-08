package by.aderman.tottenhamhotspurfc.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import by.aderman.tottenhamhotspurfc.utils.Constants

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null && intent.action != null) {
            if (intent.action == Constants.NOTIFICATIONS_ACTION) {
                if (intent.extras != null) {
                    val title = intent.getStringExtra(Constants.NOTIFICATIONS_TITLE)
                    val message = intent.getStringExtra(Constants.NOTIFICATIONS_MESSAGE)
                    if (title != null && message != null)
                        NotificationHelper.createNotification(context, title, message)
                }
            }
        }
    }
}