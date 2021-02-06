package com.ljb.android.comm.weiget.web

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.ljb.android.comm.BuildConfig
import com.ljb.android.comm.utils.XLog
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


/**
 * Author:Ljb
 * Time:2019/4/3
 * There is a lot of misery in life
 **/
class WebViewProxy(
    var mContext: Context,
    private val mWebView: WebView,
    private val mWebAction: WebActionCallBack?
) {

    private var mUrl: String = ""
    private var mJsName = "androidJs"

    init {
        initSetting()
        initJsCall()
        initDownListener()
        initWebChromeClient()
        initWebViewClient()
    }

    private fun initSetting() {
        XLog.i("webview initSetting()")
        mWebView.isHorizontalScrollBarEnabled = false
        mWebView.isVerticalFadingEdgeEnabled = false
        val setting = mWebView.settings
        setting.javaScriptEnabled = true
        setting.javaScriptCanOpenWindowsAutomatically = true
        setting.loadsImagesAutomatically = true
        setting.pluginState = WebSettings.PluginState.ON
        setting.cacheMode = WebSettings.LOAD_NO_CACHE
        // 启用DOM存储API
        setting.domStorageEnabled = true // 允许html localStorage
        setting.databaseEnabled = true
        setting.setGeolocationEnabled(true) // 设置定位的数据库路径
        setting.useWideViewPort = true
        setting.setSupportMultipleWindows(true)
        setting.loadWithOverviewMode = true
        setting.setSupportZoom(false)
        setting.setRenderPriority(WebSettings.RenderPriority.HIGH)
        setting.setAppCacheEnabled(false)//缓存
        setting.saveFormData = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.mixedContentMode = 0
        }

        try {
            if (mWebView.x5WebViewExtension != null) {
                val data = Bundle()
                data.putBoolean("standardFullScreen", false);
                //true表示标准全屏，false表示X5全屏；不设置默认false，
                data.putBoolean("supportLiteWnd", false);
                //false：关闭小窗；true：开启小窗；不设置默认true，
                data.putInt("DefaultVideoScreen", 2);
                //1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
                mWebView.x5WebViewExtension.invokeMiscMethod("setVideoParams", data);
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }


    }

    @SuppressLint("AddJavascriptInterface")
    private fun initJsCall() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        }
        mWebView.addJavascriptInterface(JsApi(mContext), mJsName)
    }

    private fun initDownListener() {
        mWebView.setDownloadListener { url, _, _, _, _ ->
            goOutWeb(url)
        }
    }

    private fun initWebChromeClient() {
        mWebView.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                XLog.i("webView progress -> $newProgress")
                mWebAction?.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                mWebAction?.onReceivedTitle(view, title)
            }

            override fun onShowCustomView(
                view: View?,
                callback: IX5WebChromeClient.CustomViewCallback?
            ) {
                mWebAction?.onShowCustomView(view, callback)
            }

            override fun onHideCustomView() {
                mWebAction?.onHideCustomView()
            }

        }
    }

    private fun initWebViewClient() {
        mWebView.webViewClient = object : WebViewClient() {

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                mWebAction?.onReceivedError(view, request, error)
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                XLog.d("shouldOverrideUrlLoading: $url")
                if (mWebAction != null && mWebAction.shouldOverrideUrlLoading(view, url)) {
                    return true
                } else if (url.startsWith("http")) {
                    val hit = view.hitTestResult
                    if (hit != null) {
                        return false
                    }
                    loadUrl(url)
                    return true
                } else if (url.startsWith("tel:")) {
                    openCallTel(mContext, url)
                    return true
                }
                goOutWeb(url)
                return false
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view.settings.blockNetworkImage = true
                mWebAction?.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                view.settings.blockNetworkImage = false
                mWebAction?.onPageFinished(view, url)
            }

        }
    }

    fun loadUrl(url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        mUrl = url
        XLog.i("webview load:$mUrl")
        mWebView.loadUrl(mUrl)
    }

    fun reLoad() {
        XLog.i("webview reload:" + mWebView.url)
        mWebView.reload()
    }


    fun callJs(js: String) {
        mWebView.loadUrl(js)
    }


    fun onWebViewPause() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.onPause()
        }
        mWebView.pauseTimers()
    }

    fun onWebViewResume() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.onResume()
        }
        mWebView.resumeTimers()
    }

    fun canGoBack(): Boolean {
        return mWebView.canGoBack()
    }

    fun goBack() {
        mWebView.goBack()
    }

    fun onDestroy() {
        try {
            mWebView.stopLoading()
            val group =  mWebView.parent
            if(group!=null){
                (group as ViewGroup).removeView(mWebView)
            }
            mWebView.webChromeClient = null
            mWebView.webViewClient = null
            mWebView.settings.javaScriptEnabled = false
            mWebView.removeJavascriptInterface(mJsName)
            // 清除webview绑定的一切缓存,4.4版本以上会崩溃
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                mWebView.clearCache(true)
            }
            mWebView.clearHistory()
            mWebView.clearView()
            mWebView.removeAllViewsInLayout()
            mWebView.removeAllViews()
            mWebView.destroy()
        } catch (t: Throwable) {
            XLog.e(t)
        }
    }

    private fun openCallTel(context: Context?, phone: String) {
        var url = phone
        if (!url.startsWith("tel:")) {
            url = "tel:$url"
        }
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(intent)
    }

    private fun goOutWeb(url: String?) {
        if (TextUtils.isEmpty(url)) return
        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            if (intent.resolveActivity(mContext.packageManager) != null) {
                mContext.startActivity(intent)
            }
        } catch (e: Exception) {
            XLog.e(e)
        }
    }

}
