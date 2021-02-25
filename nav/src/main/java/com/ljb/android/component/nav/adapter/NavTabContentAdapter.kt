package com.ljb.android.component.nav.adapter

import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.ljb.android.component.nav.R
import com.ljb.android.component.nav.bean.NavBean

class NavTabContentAdapter : BaseQuickAdapter<NavBean.ListBean, BaseViewHolder>(
    R.layout.nav_layout_tab_menu_content,
    mutableListOf()
) {

    var onTagClickListener: ((item: NavBean.ArticlesBean) -> Unit)? = null

    override fun convert(holder: BaseViewHolder, item: NavBean.ListBean) {
        holder.setText(R.id.tv_name, "${item.name} >>")
        val rv = holder.getView<RecyclerView>(R.id.rv_tag_list)
        rv.layoutManager = object : FlexboxLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        val adapter = NavTagAdapter(item.articles)
        rv.adapter = adapter
        adapter.setOnItemClickListener { _, _, position ->
            onTagClickListener?.invoke((item.articles[position]))
        }
    }


}