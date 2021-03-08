package com.ljb.android.comm.img

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.ljb.android.comm.R
import com.ljb.android.comm.img.format.ImgFormatEvent
import com.ljb.android.comm.img.format.ImgScaleType
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class ImageLoader private constructor() {

    private var context: Context? = null
    private var fragment: Fragment? = null

    private var url: String? = null
    private var resId: Int = 0

    private var imageView: ImageView? = null
    private var bitmapListener: BitmapListener? = null

    private lateinit var format: ImgFormatEvent
    private lateinit var scaleType: ImgScaleType

    private var defResId = R.color.bg_page
    private var preDrawable: Drawable? = null
    private var errorDrawable: Drawable? = null


    private fun getFormatRequest(format: ImgFormatEvent): RequestOptions {

        return when (format) {
            ImgFormatEvent.Round -> {
                RequestOptions
                    .bitmapTransform(
                        RoundedCornersTransformation(
                            format.radius,
                            0,
                            RoundedCornersTransformation.CornerType.ALL
                        )
                    )
            }
            ImgFormatEvent.RoundLeft -> {
                RequestOptions
                    .bitmapTransform(
                        RoundedCornersTransformation(
                            format.radius,
                            0,
                            RoundedCornersTransformation.CornerType.LEFT
                        )
                    )
            }
            ImgFormatEvent.RoundRight -> {
                RequestOptions
                    .bitmapTransform(
                        RoundedCornersTransformation(
                            format.radius,
                            0,
                            RoundedCornersTransformation.CornerType.RIGHT
                        )
                    )
            }
            ImgFormatEvent.RoundTopLeft -> {
                RequestOptions
                    .bitmapTransform(
                        RoundedCornersTransformation(
                            format.radius,
                            0,
                            RoundedCornersTransformation.CornerType.TOP_LEFT
                        )
                    )
            }
            ImgFormatEvent.RoundTopRight -> {
                RequestOptions
                    .bitmapTransform(
                        RoundedCornersTransformation(
                            format.radius,
                            0,
                            RoundedCornersTransformation.CornerType.TOP_RIGHT
                        )
                    )
            }
            ImgFormatEvent.RoundBottomLeft -> {
                RequestOptions
                    .bitmapTransform(
                        RoundedCornersTransformation(
                            format.radius,
                            0,
                            RoundedCornersTransformation.CornerType.BOTTOM_LEFT
                        )
                    )
            }
            ImgFormatEvent.RoundBottomRight -> {
                RequestOptions
                    .bitmapTransform(
                        RoundedCornersTransformation(
                            format.radius,
                            0,
                            RoundedCornersTransformation.CornerType.BOTTOM_RIGHT
                        )
                    )
            }
            ImgFormatEvent.Circle -> {
                RequestOptions.circleCropTransform()
            }
            else -> {
                RequestOptions()
            }
        }

    }

    /**
     * 开始加载图片
     * */
    @SuppressLint("CheckResult")
    fun load() {

        val format = getFormatRequest(format)
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        if (preDrawable == null) {
            format.placeholder(defResId)
        } else {
            format.placeholder(preDrawable)
        }

        if (errorDrawable == null) {
            format.error(defResId)
        } else {
            format.error(errorDrawable)
        }


        imageView?.run {
            load2ImageView(this, format)
            return@load
        }

        bitmapListener?.run {
            load2Bitmap(this, format)
            return@load
        }

    }

    /**
     * 加载为Bitmap
     * */
    private fun load2Bitmap(listener: BitmapListener, format: RequestOptions) {

        context?.run {

            if (!TextUtils.isEmpty(url)) {
                Glide.with(this)
                    .asBitmap().load(url)
                    .apply(format)
                    .into(object : SimpleTarget<Bitmap>() {

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            listener.asBitmapError()
                        }

                        override fun onResourceReady(
                            bitmap: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            listener.asBitmapSuccess(bitmap)
                        }

                    })
            } else if (resId != 0) {
                Glide.with(this).asBitmap().load(resId)
                    .apply(format)
                    .into(object : SimpleTarget<Bitmap>() {

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            listener.asBitmapError()
                        }

                        override fun onResourceReady(
                            bitmap: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            listener.asBitmapSuccess(bitmap)
                        }

                    })
            }

            return@load2Bitmap
        }


        fragment?.run {

            if (!TextUtils.isEmpty(url)) {
                Glide.with(this).asBitmap().load(url)
                    .apply(format)
                    .into(object : SimpleTarget<Bitmap>() {

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            listener.asBitmapError()
                        }

                        override fun onResourceReady(
                            bitmap: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            listener.asBitmapSuccess(bitmap)
                        }

                    })
            } else if (resId != 0) {
                Glide.with(this).asBitmap().load(resId)
                    .apply(format)
                    .into(object : SimpleTarget<Bitmap>() {

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            listener.asBitmapError()
                        }

                        override fun onResourceReady(
                            bitmap: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            listener.asBitmapSuccess(bitmap)
                        }

                    })
            }

            return@load2Bitmap
        }

    }


    /**
     * 加载到ImageView上
     * */
    private fun load2ImageView(
        imageView: ImageView,
        format: RequestOptions
    ) {
        context?.run {
            next(Glide.with(this), format, imageView)
            return@load2ImageView
        }

        fragment?.run {
            next(Glide.with(this), format, imageView)
            return@load2ImageView
        }

    }

    private fun next(glide: RequestManager, format: RequestOptions, imageView: ImageView) {

        var builder: RequestBuilder<*> = if (!TextUtils.isEmpty(url)) {
            glide.load(url)
        } else if (resId != 0) {
            glide.load(resId)
        } else {
            throw RuntimeException("img url or resId is null")
        }

        builder = when (scaleType) {
            ImgScaleType.CenterCrop -> builder.centerCrop()
            ImgScaleType.CenterInside -> builder.centerInside()
            ImgScaleType.CircleCrop -> builder.circleCrop()
            ImgScaleType.FitCenter -> builder.fitCenter()
        }

        builder = builder.apply(format)

        builder.into(imageView)
    }


    /**
     * 转Bitmap回调
     * */
    interface BitmapListener {

        fun asBitmapSuccess(bitmap: Bitmap)
        fun asBitmapError()
    }

    /**
     * 构造器
     * */
    class Builder {

        private var scaleType: ImgScaleType = ImgScaleType.CenterCrop
        private var format: ImgFormatEvent = ImgFormatEvent.Default
        private var preDrawable: Drawable? = null
        private var errorDrawable: Drawable? = null

        private var resId: Int = 0
        private var url: String? = null

        private var imageView: ImageView? = null
        private var listener: BitmapListener? = null


        constructor(url: String, imageView: ImageView) {
            this.url = url
            this.imageView = imageView
        }

        constructor(url: String, listener: BitmapListener) {
            this.url = url
            this.listener = listener
        }

        constructor(resId: Int, imageView: ImageView) {
            this.resId = resId
            this.imageView = imageView
        }

        /**
         * 设置占位图
         * */
        fun preDrawable(drawable: Drawable): Builder {
            this.preDrawable = drawable
            return this
        }

        /**
         * 设置加载失败图
         * */
        fun errorDrawable(drawable: Drawable): Builder {
            this.errorDrawable = drawable
            return this
        }

        /**
         * 设置图片格式化
         * 详见 ImgFormatEvent
         * */
        fun format(event: ImgFormatEvent): Builder {
            this.format = event
            return this
        }

        /**
         * scale模式
         * */
        fun scaleType(type: ImgScaleType): Builder {
            this.scaleType = type
            return this
        }


        fun build(context: Context): ImageLoader {
            val loader = ImageLoader()
            loader.context = context
            build(loader)
            return loader
        }

        fun build(fragment: Fragment): ImageLoader {
            val loader = ImageLoader()
            loader.fragment = fragment
            build(loader)
            return loader
        }

        private fun build(loader: ImageLoader) {
            loader.resId = resId
            loader.url = url
            loader.imageView = imageView
            loader.preDrawable = preDrawable
            loader.errorDrawable = errorDrawable
            loader.bitmapListener = listener
            loader.scaleType = scaleType
            loader.format = format
        }
    }


}