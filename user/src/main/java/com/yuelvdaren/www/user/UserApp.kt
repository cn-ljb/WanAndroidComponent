package com.yuelvdaren.www.user

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class UserApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }
}