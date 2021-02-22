package com.ljb.android.component.wechatcode.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.IWeChatCodeRouterService
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.wechatcode.WeChatCodeInit

@Route(path = RouterConfig.Service.WECHAT_CODE)
class WeChatCodeServiceImp : IWeChatCodeRouterService {

    override fun init(context: Context) {
        //初始化工作，服务注入时会调用
        val start = System.currentTimeMillis()
        WeChatCodeInit.init(context)
        val end = System.currentTimeMillis()
        XLog.d("=== WeChatCodeServiceImp  init:${end - start}ms ===")
    }

}