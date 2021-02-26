package com.ljb.android.component.project.adapter

import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ljb.android.comm.img.ImageLoader
import com.ljb.android.comm.img.format.ImgScaleType
import com.ljb.android.component.project.R
import com.ljb.android.component.project.bean.ProjectListBean

class ProjectListAdapter : BaseQuickAdapter<ProjectListBean.ListBean, BaseViewHolder>(
    R.layout.project_item_project_list,
    mutableListOf()
), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: ProjectListBean.ListBean) {
        val ivImg = holder.getView<ImageView>(R.id.iv_img)
        ImageLoader.Builder(item.envelopePic, ivImg)
            .scaleType(ImgScaleType.FitCenter)
            .build(context)
            .load()
        holder.setText(R.id.tv_title, item.title)
        holder.setText(R.id.tv_desc, item.desc)
        val tvTime = holder.getView<TextView>(R.id.tv_time)
        if (item.niceShareDate.isNotEmpty()) {
            tvTime.text = item.niceShareDate
        } else {
            tvTime.text = item.niceDate
        }
        holder.setText(R.id.tv_name, context.resources.getString(R.string.project_author , item.author))
    }

}