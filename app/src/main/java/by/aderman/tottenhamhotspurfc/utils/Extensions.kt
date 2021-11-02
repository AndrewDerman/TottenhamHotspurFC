package by.aderman.tottenhamhotspurfc.utils

import android.view.View
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureStatus
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun showSnackbar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        .setAnchorView(R.id.bottom_nav_view)
        .show()
}

fun toLocalTime(inputTime: String?, inputFormat: String): String? {
    val inFormat =
        SimpleDateFormat(inputFormat, Locale.getDefault()).also {
            it.timeZone = TimeZone.getTimeZone(Constants.TIMEZONE_UTC)
        }
    val outFormat =
        SimpleDateFormat(Constants.OUTPUT_TIME_FORMAT, Locale.getDefault()).also {
            it.timeZone = TimeZone.getDefault()
        }

    if (inputTime != null) {
        val date = inFormat.parse(inputTime)
        date?.let {
            return outFormat.format(it)
        }
    }
    return null
}

fun getCurrentDateForApiRequest(): String {
    val dateFormat =
        SimpleDateFormat(Constants.FIXTURES_REQUEST_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(Date())
}