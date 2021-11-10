package by.aderman.tottenhamhotspurfc.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridMarginItemDecoration : RecyclerView.ItemDecoration() {

    var margin: Int = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1) {
                top = margin
            }
            if (parent.getChildAdapterPosition(view) % 2 != 0) {
                left = margin / 2
                right = margin
            }
            if (parent.getChildAdapterPosition(view) % 2 == 0) {
                left = margin
                right = margin / 2
            }
            bottom = margin
        }
    }
}