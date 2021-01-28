package com.ljb.android.comm.common

import android.app.Activity
import android.content.Context
import com.ljb.android.comm.bean.UserBean
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.comm.utils.XLog
import net.ljb.kt.utils.JsonParser
import java.lang.Exception

object LocUser {

    /**
     * 获取登录User
     * */
    fun getUser(): UserBean? {
        val service = RouterManager.getUserService() ?: return null
        val userInfo = service.getUserInfo()
        if (userInfo.isEmpty()) return null
        try {
            XLog.i("用户信息：$userInfo")
            return JsonParser.fromJsonObj(userInfo, UserBean::class.java)
        } catch (e: Exception) {
            XLog.e(e)
        }
        return null
    }

    /**
     * 是否登录状态
     * 未登录打开登录
     * @param needLogin 是否弹出登陆页面
     */
    fun isLogIn(act: Activity, needLogin: Boolean = false): Boolean {
        return RouterManager.getUserService()?.isLogin(act, needLogin) ?: false
    }

}