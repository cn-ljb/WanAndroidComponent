package com.ljb.android.comm.common

import android.content.Context
import com.ljb.android.comm.bean.UserBean
import com.ljb.android.comm.router.RouterManager

object LocUser {


    fun getUser(context: Context): UserBean {
        return RouterManager.getUserService().getUserInfo(context)
    }


    /**
     * 是否登录状态
     * 未登录打开登录
     * @param needLogin 是否弹出登陆页面
     */
    fun isLogIn(context: Context, needLogin: Boolean = false): Boolean {
        return false
    }

}