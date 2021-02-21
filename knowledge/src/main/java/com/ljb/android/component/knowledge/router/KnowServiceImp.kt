package com.ljb.android.component.knowledge.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.IKnowRouterService
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.knowledge.KnowInit

@Route(path = RouterConfig.Service.KNOW)
class KnowServiceImp : IKnowRouterService {

    override fun init(context: Context) {
        //初始化工作，服务注入时会调用
        val start = System.currentTimeMillis()
        KnowInit.init(context)
        val end = System.currentTimeMillis()
        XLog.d("=== KnowServiceImp  init:${end - start}ms ===")
    }

}