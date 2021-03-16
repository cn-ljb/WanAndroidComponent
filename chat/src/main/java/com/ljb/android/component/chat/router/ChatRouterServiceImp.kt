package com.ljb.android.component.chat.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.IChatRouterService
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.chat.ChatInit

@Route(path = RouterConfig.Service.CHAT)
class ChatRouterServiceImp : IChatRouterService {

    override fun init(context: Context) {
        //初始化工作，服务注入时会调用
        val start = System.currentTimeMillis()
        ChatInit.init(context)
        val end = System.currentTimeMillis()
        XLog.d("=== ChatRouterServiceImp  init:${end - start}ms ===")
    }

}