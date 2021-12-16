package com.lealpy.simbirsoft_training.ui.search.search_by_nko

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.lealpy.simbirsoft_training.R
import kotlin.math.roundToInt

class NkoItemDivider(private val mDivider : Drawable, private val context: Context) : ItemDecoration() {

    private val paddingLeft = context.resources.getDimension(R.dimen.dimen_20_dp).toInt()

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + paddingLeft
        val right = parent.width - parent.paddingRight
        for (i in 0 until parent.childCount) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)
        }
    }

    private fun dpToPx(dp : Int) : Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics).roundToInt()
    }

}