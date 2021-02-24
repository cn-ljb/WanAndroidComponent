package com.ljb.android.component.nav

import android.graphics.Typeface
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ljb.android.component.nav.bean.NavBean

class NavTabAdapter : BaseQuickAdapter<NavBean.ListBean, BaseViewHolder>(
    R.layout.nav_layout_tab_menu,
    mutableListOf()
) {

    override fun convert(holder: BaseViewHolder, item: NavBean.ListBean) {
        val tv = holder.getView<TextView>(R.id.tv_name)
        tv.text = item.name

        holder.itemView.isSelected = item.checked
        if (item.checked) {
            tv.setTextColor(context.resources.getColor(R.color.color_333))
            tv.typeface = Typeface.DEFAULT_BOLD
        } else {
            tv.setTextColor(context.resources.getColor(R.color.color_white))
            tv.typeface = Typeface.DEFAULT
        }
    }
}