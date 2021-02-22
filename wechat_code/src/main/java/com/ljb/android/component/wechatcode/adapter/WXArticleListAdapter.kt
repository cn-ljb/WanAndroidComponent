package com.ljb.android.component.wechatcode.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ljb.android.component.wechatcode.R
import com.ljb.android.component.wechatcode.bean.WXArticleListBean
import kotlin.math.max

class WXArticleListAdapter: BaseQuickAdapter<WXArticleListBean.ListBean, BaseViewHolder>(
    R.layout.wechat_item_wx_article_list,
    mutableListOf()
), LoadMoreModule {


    var mCollectListener: ((position: Int) -> Unit)? = null

    override fun convert(holder: BaseViewHolder, item: WXArticleListBean.ListBean) {
        //此处建议复用Holder里的View，BaseViewHolder中对View进行了缓存，避免重新findViewById（ViewBinding会重新findViewById）
        // val bind = HomeItemHomeListBinding.bind(holder.itemView)
        val tvUser = holder.getView<TextView>(R.id.tv_user)
        if (item.author.isNotEmpty()) {
            tvUser.text = item.author
        } else {
            tvUser.text = item.shareUser
        }
        holder.setText(R.id.tv_title, item.title)

        val ivCollect = holder.getView<ImageView>(R.id.iv_collect)
        ivCollect.setOnClickListener { mCollectListener?.invoke(holder.adapterPosition) }
        if (item.collect) {
            ivCollect.setImageResource(R.mipmap.comm_icon_collect_selected)
        } else {
            ivCollect.setImageResource(R.mipmap.comm_icon_collect_normal)
        }

        holder.setText(R.id.tv_type, "${item.superChapterName}/${item.chapterName}")

        val tvTime = holder.getView<TextView>(R.id.tv_time)
        if (item.niceShareDate.isNotEmpty()) {
            tvTime.text = item.niceShareDate
        } else {
            tvTime.text = item.niceDate
        }
        val time = max(item.publishTime, item.shareDate)
        val dTime = System.currentTimeMillis() - time
        val newView = holder.getView<View>(R.id.tv_new)
        if (dTime < 24 * 60 * 60 * 1000) {
            newView.visibility = View.VISIBLE
        } else {
            newView.visibility = View.GONE
        }
    }


}