package com.ljb.android.comm.weiget.web

import android.graphics.Bitmap
import android.view.View
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebView

/**
 * Author:Ljb
 * Time:2019/4/3
 * There is a lot of misery in life
 **/
interface WebActionCallBack {

    /**
     * 页面Title回调
     * */
    fun onReceivedTitle(view: WebView?, title: String?)

    /**
     * 页面发生错误
     * */
    fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?)

    /**
     * 是否拦截Url
     * */
    fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean

    /**
     * 页面加载完毕
     * */
    fun onPageFinished(view: WebView?, url: String?)

    /**
     * 页面开始加载
     * */
    fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)

    /**
     * 页面加载进度
     * */
    fun onProgressChanged(view: WebView?, newProgress: Int)

    /**
     * X5 切换至全屏
     * */
    fun onShowCustomView(view: View?, callback: IX5WebChromeClient.CustomViewCallback?)

    /**
     * X5 隐藏全屏
     * */
    fun onHideCustomView()

}