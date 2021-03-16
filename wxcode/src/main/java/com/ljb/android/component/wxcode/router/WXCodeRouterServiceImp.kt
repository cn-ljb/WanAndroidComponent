package com.ljb.android.component.wxcode.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.IWXCodeRouterService
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.wxcode.WXCodeInit

@Route(path = RouterConfig.Service.WECHAT_CODE)
class WXCodeRouterServiceImp : IWXCodeRouterService {

    override fun init(context: Context) {
        //初始化工作，服务注入时会调用
        val start = System.currentTimeMillis()
        WXCodeInit.init(context)
        val end = System.currentTimeMillis()
        XLog.d("=== WeChatCodeRouterServiceImp  init:${end - start}ms ===")
    }

}