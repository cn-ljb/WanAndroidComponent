package com.ljb.android.comm.view.act

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.ljb.android.comm.R
import com.ljb.android.comm.contract.CommWebViewContract
import com.ljb.android.comm.databinding.CommActivityWebViewBinding
import com.ljb.android.comm.mvp.CommMvpActivity
import com.ljb.android.comm.presenter.CommWebViewPresenter
import com.ljb.android.comm.utils.XLog
import com.ljb.android.comm.weiget.web.WebActionCallBack
import com.ljb.android.comm.weiget.web.WebViewProxy
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebView

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/30
 * @Description input description
 **/
class CommWebViewActivity :
    CommMvpActivity<CommWebViewContract.IPresenter, CommActivityWebViewBinding>(),
    CommWebViewContract.IView {

    companion object {

        const val KEY_TITLE = "key_title"
        const val KEY_URL = "key_url"

        fun startActivity(act: Activity, url: String, title: String = "") {
            val intent = Intent(act, CommWebViewActivity::class.java)
            intent.putExtra(KEY_TITLE, title)
            intent.putExtra(KEY_URL, url)
            act.startActivity(intent)
        }

    }

    private var mTitle: String? = null
    private var mUrl: String? = null
    private lateinit var mProxy: WebViewProxy

    override fun registerPresenter() = CommWebViewPresenter::class.java

    override fun getLayoutId() = R.layout.comm_activity_web_view

    override fun registerBinding(): CommActivityWebViewBinding {
        return CommActivityWebViewBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFormat(PixelFormat.TRANSLUCENT);
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mProxy.onWebViewResume()
    }

    override fun onPause() {
        super.onPause()
        mProxy.onWebViewPause()
    }

    override fun onDestroy() {
        mProxy.onDestroy()
        super.onDestroy()
    }

    override fun supportTitle() = true

    override fun init(savedInstanceState: Bundle?) {
        mTitle = intent.getStringExtra(KEY_TITLE)
        mUrl = intent.getStringExtra(KEY_URL)
        if (mUrl?.trim().isNullOrEmpty()) {
            XLog.e("WebView 未传入Url")
            finish()
        }
    }

    override fun initView() {
        initTitleView()
        mProxy = WebViewProxy(this, mBind.webView, object : WebActionCallBack {

            override fun onReceivedTitle(view: WebView?, title: String?) {
                if (mTitle.isNullOrEmpty()) {
                    setTitleText(title ?: "")
                }
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    return false
                }
//                if (url.startsWith("jianshu://") || url.startsWith("hap://")) {
//                    return true
//                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                showLoading()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                hideLoading()
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress >= 100) {
                    hideLoading()
                }
            }

            override fun onShowCustomView(
                view: View?,
                callback: IX5WebChromeClient.CustomViewCallback?
            ) {
                mBindTitleBar.layoutToolbar.visibility = View.GONE
                mBind.webView.visibility = View.GONE
                mBind.flVideo.visibility = View.VISIBLE
                mBind.flVideo.addView(view)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            }

            override fun onHideCustomView() {
                mBindTitleBar.layoutToolbar.visibility = View.VISIBLE
                mBind.webView.visibility = View.VISIBLE
                mBind.flVideo.visibility = View.GONE
                mBind.flVideo.removeAllViews()
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
            }
        })
    }

    private fun initTitleView() {
        setTitleText(mTitle ?: "")
        setTitleRightImage(R.mipmap.comm_icon_share, View.OnClickListener {
            goSystemShare()
        })
    }

    private fun goSystemShare() {
        val intent = Intent(Intent.ACTION_SEND);
        intent.type = "text/plain";
        val shareTxt = "来自【玩安卓（组件化）】APP的分享：\n$mUrl"
        intent.putExtra(Intent.EXTRA_TEXT, shareTxt);
        startActivity(Intent.createChooser(intent, mTitle));
    }

    override fun initData() {
        mProxy.loadUrl(mUrl!!)
    }

    override fun onBackPressed() {
        if (mProxy.canGoBack()) {
            mProxy.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
                window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
            }
            Configuration.ORIENTATION_SQUARE -> {
            }
            Configuration.ORIENTATION_UNDEFINED -> {
            }
        }
    }

}
