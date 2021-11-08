package by.aderman.tottenhamhotspurfc.presentation.adapters

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.EventDetails
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.EventTypes
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureStatsTypes
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureStatus
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.w3c.dom.Text

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["image", "error"], requireAll = true)
    fun loadImage(view: ImageView, imageUrl: String?, error: Drawable) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.no_image_placeholder)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.setImageDrawable(error)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.setImageDrawable(resource)
                    return true
                }
            })
            .into(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["first", "second", "type"], requireAll = true)
    fun fillProgressBar(view: ProgressBar, first: String?, second: String?, type: String?) {
        when (type) {
            FixtureStatsTypes.TS.value,
            FixtureStatsTypes.SONG.value,
            FixtureStatsTypes.SOFFG.value,
            FixtureStatsTypes.SI.value,
            FixtureStatsTypes.SO.value,
            FixtureStatsTypes.BS.value,
            FixtureStatsTypes.CK.value,
            FixtureStatsTypes.O.value,
            FixtureStatsTypes.GS.value,
            FixtureStatsTypes.F.value,
            FixtureStatsTypes.YC.value,
            FixtureStatsTypes.RC.value,
            FixtureStatsTypes.TP.value,
            FixtureStatsTypes.PA.value -> {
                if (first != null && second != null) {
                    if (first == "null") {
                        view.progress = 0
                    } else if (second == "null") {
                        view.progress = 100
                    } else {
                        val firstValue = first.toDouble()
                        val secondValue = second.toDouble()
                        val result = firstValue / (firstValue + secondValue) * 100
                        view.progress = result.toInt()
                    }
                } else if (first == null && second != null) {
                    view.progress = 0
                } else if (first != null && second == null) {
                    view.progress = 100
                } else {
                    view.progress = 0
                }
            }
            FixtureStatsTypes.BP.value,
            FixtureStatsTypes.P.value -> {
                if (first != null && second != null) {
                    val firstValue = first.substring(0, 2).toDouble()
                    val secondValue = second.substring(0, 2).toDouble()
                    val result = firstValue / (firstValue + secondValue) * 100
                    view.progress = result.toInt()
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["value"])
    fun setStatsText(view: TextView, value: String?) {
        if (value != null) {
            if (value == "null") {
                view.text = "0"
            } else {
                view.text = value.substring(0, value.length - 2)
            }
        } else view.text = "0"
    }

    @JvmStatic
    @BindingAdapter(value = ["goals", "status"], requireAll = true)
    fun setFixtureGoals(view: TextView, goals: Int?, status: String?) {
        if (status == FixtureStatus.TBD.value
            || status == FixtureStatus.NS.value
            || status == FixtureStatus.SUSP.value
            || status == FixtureStatus.PST.value
            || status == FixtureStatus.CANC.value
        ) {
            view.text = ""
        } else {
            view.text = goals.toString()
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["elapsed", "extra"], requireAll = true)
    fun setEventTime(view: TextView, elapsed: Int, extra: Int?) {
        if (extra != null) {
            view.text = view.resources.getString(
                R.string.fragment_events_time_elapsed_plus_extra,
                elapsed,
                extra
            )
        } else {
            view.text = view.resources.getString(R.string.fragment_events_time_elapsed, elapsed)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["eventType", "eventDetail"], requireAll = true)
    fun setEventImage(view: ImageView, eventType: String, eventDetail: String) {
        when (eventType) {
            EventTypes.GOAL.value -> {
                if (eventDetail == EventDetails.NG.value || eventDetail == EventDetails.OG.value) {
                    view.setImageResource(R.drawable.event_goal)
                } else {
                    view.setImageResource(R.drawable.event_penalty)
                }
            }
            EventTypes.CARD.value -> {
                if (eventDetail == EventDetails.YC.value || eventDetail == EventDetails.YC.value) {
                    view.setImageResource(R.drawable.event_yellow_card)
                } else {
                    view.setImageResource(R.drawable.event_red_card)
                }
            }
            EventTypes.SUBST.value -> view.setImageResource(R.drawable.event_substitution)
            EventTypes.VAR.value -> view.setImageResource(R.drawable.event_var)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["eventType", "eventDetail"], requireAll = true)
    fun setEventTypeText(view: TextView, eventType: String, eventDetail: String) {
        when (eventType) {
            EventTypes.GOAL.value ->
                view.text = if (eventDetail == EventDetails.NG.value) eventType else eventDetail
            EventTypes.CARD.value -> view.text = eventDetail
            EventTypes.SUBST.value -> view.text = eventDetail
            EventTypes.VAR.value -> view.text = eventDetail
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["color", "eventType", "playerName"], requireAll = true)
    fun setEventPlayerText(view: TextView, color: Int, eventType: String, playerName: String) {
        view.text = playerName
        if (eventType == EventTypes.SUBST.value) {
            view.setTextColor(color)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["eventDetail"])
    fun setEventAssistVisibility(view: TextView, eventDetail: String) {
        view.visibility = if (eventDetail == EventDetails.NG.value) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter(value = ["assistant", "eventType", "color"], requireAll = true)
    fun setEventAssistText(view: TextView, assistant: String?, eventType: String, color: Int) {
        if (assistant == null) {
            view.visibility = View.GONE
        } else {
            view.text = assistant
        }
        if (eventType == EventTypes.SUBST.value) {
            view.setTextColor(color)
        }
    }
}