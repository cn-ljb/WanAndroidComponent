package com.ljb.android.comm.router

import com.alibaba.android.arouter.launcher.ARouter
import com.ljb.android.comm.router.service.IUserRouterService

object RouterManager {

    /**
     *  通过ARouter获取User模块数据
     */
    fun getUserService(): IUserRouterService {
        return (ARouter.getInstance().build(RouterConfig.Service.USER_SERVICE_GET_USER_INFO)
            .navigation() as IUserRouterService)
    }
}