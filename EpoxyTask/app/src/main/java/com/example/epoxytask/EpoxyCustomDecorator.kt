package com.example.epoxytask

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class EpoxyCustomDecorator(context: Context) : RecyclerView.ItemDecoration() {

    private val dividerPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.purple_200)
    }

    private val dividerHeight = context.resources.getDimensionPixelSize(R.dimen._1dp)
    private val dividerMargin = context.resources.getDimensionPixelSize(R.dimen._16dp)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val ty = child.translationY
            val position = parent.getChildAdapterPosition(child)
                .takeIf { it != RecyclerView.NO_POSITION } ?: continue
            c.drawRect(
                child.left.toFloat() + dividerMargin,
                child.bottom - dividerHeight + ty,
                child.right.toFloat() - dividerMargin,
                child.bottom + ty,
                dividerPaint
            )
        }
    }
}