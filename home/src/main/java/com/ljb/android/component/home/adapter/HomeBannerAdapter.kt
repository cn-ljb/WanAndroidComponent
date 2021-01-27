package com.ljb.android.component.home.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ljb.android.comm.img.ImageLoader
import com.ljb.android.component.home.bean.BannerBean
import com.youth.banner.adapter.BannerAdapter

/**
 * Author:Ljb
 * Time:2021/1/28
 * There is a lot of misery in life
 **/
class HomeBannerAdapter(list: MutableList<BannerBean.DataBean>) :
    BannerAdapter<BannerBean.DataBean, HomeBannerAdapter.ViewHolder>(list) {

    class ViewHolder(itemView: ImageView) : RecyclerView.ViewHolder(itemView)

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return ViewHolder(imageView)
    }

    override fun onBindView(
        holder: ViewHolder,
        data: BannerBean.DataBean,
        position: Int,
        size: Int
    ) {
        ImageLoader.Builder(data.imagePath, holder.itemView as ImageView)
            .build(holder.itemView.context)
            .load()
    }
}