package com.ljb.android.comm.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Author:Ljb
 * Time:2021/2/27
 * There is a lot of misery in life
 **/

class RVItemDecorationMargin(
    var top: Int = 0,
    var bottom: Int = 0,
    var left: Int = 0,
    var right: Int = 0,
    var onlyFirstItem: Boolean = false,
    var onlyLastItem: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //仅首个Item生效
        if (onlyFirstItem) {
            parent.adapter?.run {
                val position = parent.getChildAdapterPosition(view)
                if (position == 0) {
                    changeRect(outRect)
                }
            }
            return
        }
        //仅最后Item生效
        if (onlyLastItem) {
            parent.adapter?.run {
                val position = parent.getChildAdapterPosition(view)
                if (position == itemCount - 1) {
                    changeRect(outRect)
                }
            }
            return
        }
        //所有Item都生效
        changeRect(outRect)
    }

    private fun changeRect(outRect: Rect) {
        outRect.top = top
        outRect.bottom = bottom
        outRect.left = left
        outRect.right = right
    }

}
