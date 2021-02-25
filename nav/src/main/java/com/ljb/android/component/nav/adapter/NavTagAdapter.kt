package com.ljb.android.component.nav.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import com.blankj.utilcode.util.SizeUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ljb.android.component.nav.R
import com.ljb.android.component.nav.bean.NavBean
import kotlin.random.Random

class NavTagAdapter(list: MutableList<NavBean.ArticlesBean>) :
    BaseQuickAdapter<NavBean.ArticlesBean, BaseViewHolder>(
        R.layout.nav_layout_tag,
        list
    ) {

    override fun convert(holder: BaseViewHolder, item: NavBean.ArticlesBean) {
        val tvTag = holder.getView<TextView>(R.id.tv_tag)
        tvTag.text = item.title
        tvTag.setBackgroundColor(context.resources.getColor(R.color.color_333))

        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = SizeUtils.dp2px(8f).toFloat()
        val alpha = 255
        val red: Int = 55 + Random.nextInt(100)
        val green: Int = 55 + Random.nextInt(100)
        val blue: Int = 55 + Random.nextInt(100)
        shape.setColor(Color.argb(alpha, red, green, blue))
        tvTag.setBackgroundDrawable(shape)
    }

}