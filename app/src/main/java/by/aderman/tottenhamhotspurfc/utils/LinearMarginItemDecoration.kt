package by.aderman.tottenhamhotspurfc.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearMarginItemDecoration : RecyclerView.ItemDecoration() {

    var margin: Int = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = margin
            }
            left = margin
            right = margin
            bottom = margin
        }
    }
}