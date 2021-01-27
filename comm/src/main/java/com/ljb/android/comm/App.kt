package com.ljb.android.comm

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.SPUtils
import com.ljb.android.comm.utils.XLog
import net.ljb.kt.HttpConfig

/**
 * Author:Ljb
 * Time:2021/1/19
 * There is a lot of misery in life
 **/
class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initARoute()
        initSP()
        initHttp()
    }

    private fun initARoute() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        XLog.i("ARouter.init before")
        ARouter.init(this)
        XLog.i("ARouter.init after")
    }

    private fun initSP() {
        SPStaticUtils.setDefaultSPUtils(
            SPUtils.getInstance()
        )
    }

    private fun initHttp() {
        HttpConfig.Builder(BuildConfig.HTTP_HOST)
            .addCommCookie(object : HttpConfig.ICommCookie {
                //TODO Cookie 持久化
                override fun saveCookie(host: String, cookie: String) {
                    SPStaticUtils.put(host, cookie)
                }

                override fun loadCookie(host: String): String? {
                    return SPStaticUtils.getString(host)
                }
            }).addCommHeader { headers ->
                //TODO 公共Header
            }.addCommParam { params ->
                //TODO 公共Param
            }.openLog(BuildConfig.DEBUG)
            .build()
    }
}