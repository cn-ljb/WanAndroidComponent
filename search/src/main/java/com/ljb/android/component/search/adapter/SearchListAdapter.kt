package com.ljb.android.component.search.adapter

import android.text.Html
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ljb.android.component.search.R
import com.ljb.android.component.search.bean.SearchBean

/**
 * Author:Ljb
 * Time:2021/1/30
 * There is a lot of misery in life
 **/
class SearchListAdapter : BaseQuickAdapter<SearchBean.ListBean, BaseViewHolder>(
    R.layout.search_item_list,
    mutableListOf()
), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: SearchBean.ListBean) {
        //此处建议复用Holder里的View，BaseViewHolder中对View进行了缓存，避免重新findViewById（ViewBinding会重新findViewById）
        // val bind = HomeItemHomeListBinding.bind(holder.itemView)
        val tvUser = holder.getView<TextView>(R.id.tv_user)
        if (item.author.isNotEmpty()) {
            tvUser.text = item.author
        } else {
            tvUser.text = item.shareUser
        }
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        tvTitle.text = Html.fromHtml(item.title)

        holder.setText(R.id.tv_type, "${item.superChapterName}/${item.chapterName}")

        val tvTime = holder.getView<TextView>(R.id.tv_time)
        if (item.niceShareDate.isNotEmpty()) {
            tvTime.text = item.niceShareDate
        } else {
            tvTime.text = item.niceDate
        }
    }

}
