package com.ljb.android.component.nav.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.IKnowRouterService
import com.ljb.android.comm.router.service.INavRouterService
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.nav.NavInit

@Route(path = RouterConfig.Service.NAV)
class NavRouterServiceImpl : INavRouterService {

    override fun init(context: Context) {
        //初始化工作，服务注入时会调用
        val start = System.currentTimeMillis()
        NavInit.init(context)
        val end = System.currentTimeMillis()
        XLog.d("=== NavRouterServiceImpl  init:${end - start}ms ===")
    }

}