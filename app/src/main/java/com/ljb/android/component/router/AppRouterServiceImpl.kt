package com.ljb.android.component.router

import android.app.Activity
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.IAppRouterService
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.AppInit
import com.ljb.android.component.view.act.AppHomeActivity

@Route(path = RouterConfig.Service.APP)
class AppRouterServiceImpl : IAppRouterService {

    override fun init(context: Context) {
        //初始化工作，服务注入时会调用，可忽略
        val start = System.currentTimeMillis()
        AppInit.init(context)
        val end = System.currentTimeMillis()
        XLog.d("=== AppRouterServiceImpl   init:${end - start}ms ===")
    }


    override fun openOrCloseDrawerLeft(activity: Activity) {
        if (activity is AppHomeActivity) {
            activity.openOrCloseDrawerLeft()
        }
    }


}