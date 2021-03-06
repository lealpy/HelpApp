package com.lealpy.help_app.presentation.screens.history

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HistoryItemDecoration (
    private val spacing: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        outRect.left = spacing
        outRect.right = spacing
        if (position == 0) {
            outRect.top = spacing
        }
        outRect.bottom = spacing
    }
}