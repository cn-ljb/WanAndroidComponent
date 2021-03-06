package com.ljb.android.comm

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.Utils
import com.didichuxing.doraemonkit.DoraemonKit
import com.ljb.android.comm.common.Constant
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.comm.utils.XLog
import com.tencent.smtt.sdk.QbSdk
import net.ljb.kt.HttpConfig

/**
 * Author:Ljb
 * Time:2021/1/19
 * There is a lot of misery in life
 *
 * 注：非所有组件需要的公共功能，请勿在当前主Application中初始化
 * 注：各子组件在自身的Init中，初始化当前业务组件所需的功能模块即可
 * 注：例如app组件的 {@see com.ljb.android.component.AppInit}
 **/
class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initDokit()
        initUtils()
        initSP()
        initHttp()
        initWebX5()
        initARoute()
    }

    private fun initDokit() {
        DoraemonKit.install(this, mutableListOf(), BuildConfig.APP_ID);
    }

    private fun initUtils() {
        Utils.init(this)
    }

    private fun initARoute() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }

        val start = System.currentTimeMillis()
        ARouter.init(this)
        RouterManager.init()
        val end = System.currentTimeMillis()
        XLog.i("ARouter.init: ${end - start}ms")
    }

    private fun initSP() {
        SPStaticUtils.setDefaultSPUtils(
            SPUtils.getInstance(Constant.SPKey.SP_NAME)
        )
    }

    private fun initHttp() {
        HttpConfig.Builder(BuildConfig.HTTP_HOST)
            .addCommCookie(object : HttpConfig.ICommCookie {
                //Cookie 持久化
                override fun saveCookie(host: String, cookie: String) {
                    SPStaticUtils.put(host, cookie)
                }

                override fun loadCookie(host: String): String? {
                    return SPStaticUtils.getString(host)
                }
            }).addCommHeader { headers ->
                //TODO 添加公共Header
            }.addCommParam { params ->
                //TODO 添加公共Param
            }.openLog(BuildConfig.DEBUG)
            .build()
    }

    private fun initWebX5() {
        QbSdk.initX5Environment(this, object : QbSdk.PreInitCallback {

            override fun onCoreInitFinished() {
                XLog.i("WebView X5内核初始化完毕")
            }

            override fun onViewInitFinished(p0: Boolean) {
                XLog.i("X5 WebView验证完毕：$p0")
            }

        })
    }
}