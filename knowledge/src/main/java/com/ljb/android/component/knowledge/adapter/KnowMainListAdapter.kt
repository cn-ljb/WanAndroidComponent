package com.ljb.android.component.knowledge.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ljb.android.component.knowledge.R
import com.ljb.android.component.knowledge.bean.KnowMainListBean

/**
 * Author:Ljb
 * Time:2021/2/21
 * There is a lot of misery in life
 **/
class KnowMainListAdapter : BaseQuickAdapter<KnowMainListBean.ListBean, BaseViewHolder>(
    R.layout.know_item_main_list,
    mutableListOf()
) {
    override fun convert(holder: BaseViewHolder, item: KnowMainListBean.ListBean) {
        holder.setText(R.id.tv_name, item.name)
        holder.setText(R.id.tv_msg, item.msg)
    }
}
