package com.lealpy.simbirsoft_training.presentation.screens.news.news_preview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NewsPreviewItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
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