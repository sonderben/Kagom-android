package com.sonderben.kagom.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class SpaceDecoration(spacing: Int) : ItemDecoration() {

    private var space:Int = 0

    init {
        space = spacing
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        outRect.top = space

    }
}
