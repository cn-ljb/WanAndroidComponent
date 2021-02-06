package com.ljb.android.component.home.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.IHomeRouterService
import com.ljb.android.comm.utils.XLog

@Route(path = RouterConfig.Service.HOME)
class HomeServiceImp : IHomeRouterService {

    override fun init(context: Context?) {
        //初始化工作，服务注入时会调用，可忽略
        XLog.d("=== HomeServiceImp init ===")
    }


}