package by.aderman.tottenhamhotspurfc.utils

import android.graphics.Canvas
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.R

class TableItemDecoration : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children
            .forEach { view ->
                val position = parent.getChildAdapterPosition(view)
                    .let { if (it == RecyclerView.NO_POSITION) return else it }
                if (position % 2 == 0) {
                    view.setBackgroundColor(view.resources.getColor(R.color.th_background, null))
                } else {
                    view.setBackgroundColor(view.resources.getColor(R.color.th_primary_white, null))
                }
            }
    }
}