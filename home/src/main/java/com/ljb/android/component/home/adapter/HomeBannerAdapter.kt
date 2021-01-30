package com.ljb.android.component.home.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.blankj.utilcode.util.SizeUtils
import com.ljb.android.comm.img.ImageLoader
import com.ljb.android.comm.img.format.ImgFormatEvent
import com.ljb.android.comm.img.format.ImgScaleType
import com.ljb.android.comm.utils.DefResUtils
import com.ljb.android.component.home.bean.BannerBean
import com.youth.banner.loader.ImageLoaderInterface

/**
 * Author:Ljb
 * Time:2021/1/28
 * There is a lot of misery in life
 **/
class HomeBannerAdapter : ImageLoaderInterface<ImageView> {

    override fun createImageView(context: Context?): ImageView {
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return imageView
    }

    override fun displayImage(context: Context, data: Any, imageView: ImageView) {
        if (data is BannerBean.DataBean) {
            ImageLoader.Builder(data.imagePath, imageView)
                .preDrawable(DefResUtils.getDefImg(context))
                .errorDrawable(DefResUtils.getDefImg(context))
                .build(context)
                .load()
        }
    }
}