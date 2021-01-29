package com.ljb.android.component.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ljb.android.comm.img.ImageLoader
import com.ljb.android.component.home.R
import com.ljb.android.component.home.bean.BannerBean
import com.ljb.android.component.home.databinding.HomeLayoutItemHomeBannerBinding
import com.youth.banner.adapter.BannerAdapter

/**
 * Author:Ljb
 * Time:2021/1/28
 * There is a lot of misery in life
 **/
class HomeBannerAdapter2(list: MutableList<BannerBean.DataBean>) :
    BannerAdapter<BannerBean.DataBean, HomeBannerAdapter2.ViewHolder>(list) {

    class ViewHolder(var bind: HomeLayoutItemHomeBannerBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeLayoutItemHomeBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindView(
        holder: ViewHolder,
        data: BannerBean.DataBean,
        position: Int,
        size: Int
    ) {
        ImageLoader.Builder(data.imagePath, holder.bind.ivImg as ImageView)
            .build(holder.itemView.context)
            .load()
        holder.bind.tvBannerDesc.text = data.title
        holder.bind.tvBannerPage.text = "${position + 1}/${realCount}"
    }
}