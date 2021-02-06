package com.ljb.android.component.user.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SPStaticUtils
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.service.IUserRouterService
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.user.UserInit
import com.ljb.android.component.user.common.UserConstant
import com.ljb.android.component.user.view.act.UserLoginActivity

@Route(path = RouterConfig.Service.USER)
class UserServiceImp : IUserRouterService {

    override fun init(context: Context) {
        //初始化工作，服务注入时会调用
        val start = System.currentTimeMillis()
        UserInit.init(context)
        val end = System.currentTimeMillis()
        XLog.d("=== UserServiceImp  init:${end - start}ms ===")
    }

    override fun getUserInfo(): String {
        return SPStaticUtils.getString(UserConstant.SPKey.KEY_LOGIN_USER, "")
    }

    override fun isLogin(activity: Activity, needLogin: Boolean): Boolean {
        val userInfo = getUserInfo()
        if (userInfo.isEmpty()) {
            if (needLogin) {
                activity.startActivity(Intent(activity, UserLoginActivity::class.java))
            }
            return false
        } else {
            return true
        }
    }


}