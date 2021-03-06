package com.ljb.android.component.nav.adapter

import android.graphics.Typeface
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ljb.android.component.nav.R
import com.ljb.android.component.nav.bean.NavBean

class NavTabAdapter : BaseQuickAdapter<NavBean.ListBean, BaseViewHolder>(
    R.layout.nav_layout_tab_menu,
    mutableListOf()
) {

    private var mSelectedPosition = -1

    override fun convert(holder: BaseViewHolder, item: NavBean.ListBean) {
        val tv = holder.getView<TextView>(R.id.tv_name)
        tv.text = item.name

        val isSelected = mSelectedPosition == holder.adapterPosition

        holder.itemView.isSelected = isSelected
        if (isSelected) {
            tv.typeface = Typeface.DEFAULT_BOLD
        } else {
            tv.typeface = Typeface.DEFAULT
        }
    }

    fun setSelectedItem(position: Int) {
        if (mSelectedPosition == position) {
            return
        }

        var preSelectedPosition = -1
        if (mSelectedPosition != -1) {
            preSelectedPosition = mSelectedPosition
        }

        mSelectedPosition = position
        if (preSelectedPosition != -1) {
            notifyItemChanged(preSelectedPosition)
        }
        notifyItemChanged(position)
    }
}