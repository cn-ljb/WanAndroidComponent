package com.ljb.android.component

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.SPUtils
import net.ljb.kt.HttpConfig

/**
 * Author:Ljb
 * Time:2021/1/19
 * There is a lot of misery in life
 **/
class App :Application() {

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
        ARouter.init(this)
    }

    private fun initSP() {
        SPStaticUtils.setDefaultSPUtils(
            SPUtils.getInstance()
        )
    }

    private fun initHttp() {
        HttpConfig.Builder(BuildConfig.HTTP_HOST)
            .addCommHeader { headers ->

            }.openLog(true)
            .build()
    }
}