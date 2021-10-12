package by.aderman.tottenhamhotspurfc.ui.fragments.news

import androidx.recyclerview.widget.RecyclerView

abstract class OnBottomScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (recyclerView.canScrollVertically(-1)    // может скроллить вверх
            && !recyclerView.canScrollVertically(1) // не может скроллить вниз
            && newState == RecyclerView.SCROLL_STATE_IDLE   // не скроллится
        ) {
            onBottomReached()
        }
    }

    // переопределяется во фрагменте
    abstract fun onBottomReached()
}
