package com.yuelvdaren.www.user.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.IUserRouterService

@Route(path = RouterConfig.Service.USER_SERVICE_GET_USER_INFO)
class UserServiceImp : IUserRouterService {

    override fun init(context: Context?) {
        //初始化工作，服务注入时会调用，可忽略
    }

    override fun getUserInfo(context: Context): String {
        return ""
    }

    override fun isLogin(context: Context, needLogin: Boolean): Boolean {
        return false
    }


}