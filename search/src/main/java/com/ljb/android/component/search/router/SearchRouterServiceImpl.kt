package com.ljb.android.component.search.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.ISearchRouterService
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.search.SearchInit

@Route(path = RouterConfig.Service.SEARCH)
class SearchRouterServiceImpl : ISearchRouterService {

    override fun init(context: Context) {
        //初始化工作，服务注入时会调用
        val start = System.currentTimeMillis()
        SearchInit.init(context)
        val end = System.currentTimeMillis()
        XLog.d("=== ProjectRouterServiceImpl  init:${end - start}ms ===")
    }

}